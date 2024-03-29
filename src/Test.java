package src;

import sun.util.calendar.BaseCalendar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.DoubleSummaryStatistics;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Created by octavian.guzu on 1/5/2017.
 */
public class Test {
    public Department createDepartment(BufferedReader store) throws IOException {
        StringTokenizer departmentData = new StringTokenizer(store.readLine(), ";");
        String departmentName = departmentData.nextToken();
        int deparmentID = Integer.parseInt(departmentData.nextToken());
        int noProducts = Integer.parseInt(store.readLine());
        Vector<Item> items = new Vector<>();
        for (int i = 0; i < noProducts; i++) {
            StringTokenizer product = new StringTokenizer(store.readLine(), ";");
            String itemName = product.nextToken();
            int itemID = Integer.parseInt(product.nextToken());
            double itemPrice = Double.parseDouble(product.nextToken());
            items.add(new Item(itemName, itemID, itemPrice));
        }
        if (departmentName.equals("BookDepartment"))
            return new BookDepartment(departmentName, items, deparmentID);
        if (departmentName.equals("VideoDepartment"))
            return new VideoDepartment(departmentName, items, deparmentID);
        if (departmentName.equals("MusicDepartment"))
            return new MusicDepartment(departmentName, items, deparmentID);
        if (departmentName.equals("SoftwareDepartment"))
            return new SoftwareDepartment(departmentName, items, deparmentID);
        return null;
    }

    public static void main(String args[]) throws IOException, CloneNotSupportedException {

        BufferedReader store = new BufferedReader(new FileReader("store.txt"));
        String storeName = store.readLine();
        Vector<Department> departments = new Vector<>();
        Test obj = new Test();

        // Reading Departments
        departments.add(obj.createDepartment(store));
        departments.add(obj.createDepartment(store));
        departments.add(obj.createDepartment(store));
        departments.add(obj.createDepartment(store));

        //Reading Customers
        Vector<Customer> customers = new Vector<>();
        BufferedReader customer = new BufferedReader(new FileReader("customers.txt"));
        int noCustomers = Integer.parseInt(customer.readLine());
        for (int i = 0; i < noCustomers; i++) {
            StringTokenizer line = new StringTokenizer(customer.readLine(), ";");
            String customerName = line.nextToken();
            double budget = Double.parseDouble(line.nextToken());
            String strategy = line.nextToken();
            customers.add(new Customer(customerName, budget, strategy));
        }

        //Creating the Store
        Store MainStore = Store.getInstance(storeName, customers, departments);

        //Generating the events
        BufferedReader events = new BufferedReader(new FileReader("events.txt"));
        int noEvents = Integer.parseInt(events.readLine());
        for (int i = 0; i < noEvents; i++) {
            StringTokenizer line = new StringTokenizer(events.readLine(), ";");
            String evenType = line.nextToken();
            if (evenType.equals("addItem")) {
                int itemID = Integer.parseInt(line.nextToken());
                int departmentIDtoADD = -1;
                Item itemToAdd = null;
                for (int j = 0; j < departments.size(); j++) {
                    itemToAdd = departments.get(j).getItem(itemID);
                    if (itemToAdd != null) {
                        departmentIDtoADD = departments.get(j).getId();
                        break;
                    }
                }
                if (itemToAdd != null) {
                    String whereTo = line.nextToken();
                    if (whereTo.equals("ShoppingCart")) {
                        String customerName = line.nextToken();
                        Customer customerToAdd = null;
                        for (int j = 0; j < customers.size(); j++) {
                            customerToAdd = MainStore.getCustomers().get(j);
                            if (customerToAdd.name.equals(customerName)) {
                                break;
                            }
                        }
                        if (customerToAdd != null) {
                            Item newItem = (Item) itemToAdd.clone();
                            customerToAdd.shoppingCart.add(newItem);
                            if (!MainStore.getDepartment(departmentIDtoADD).customersWhoBought.contains(customerToAdd))
                                MainStore.getDepartment(departmentIDtoADD).customersWhoBought.add(customerToAdd);
                        }
                    } else {
                        String customerName = line.nextToken();
                        Customer customerToAdd = null;
                        for (int j = 0; j < customers.size(); j++) {
                            customerToAdd = MainStore.getCustomers().get(j);
                            if (customerToAdd.name.equals(customerName)) {
                                break;
                            }
                        }
                        if (customerToAdd != null) {
                            Item newItem = (Item) itemToAdd.clone();
                            customerToAdd.wishList.add(newItem);
                            if (!MainStore.getDepartment(departmentIDtoADD).customersWhoWish.contains(customerToAdd)) {
                                MainStore.getDepartment(departmentIDtoADD).addObserver(customerToAdd);
                            }
                        }
                    }
                }
            }
            if (evenType.equals("delItem")) {
                int itemID = Integer.parseInt(line.nextToken());
                String WhereTo = line.nextToken();
                String customerName = line.nextToken();
                Customer customerToDel = null;
                for (int j = 0; j < MainStore.getCustomers().size(); j++) {
                    customerToDel = MainStore.getCustomers().get(j);
                    if (customerToDel.name.equals(customerName))
                        break;
                }
                if (WhereTo.equals("WishList")) {
                    customerToDel.wishList.removeByID(itemID);
                    int departmentIDfromWhere = -1;
                    for (int j = 0; j < MainStore.getDepartments().size(); j++) {
                        Department checkIn = MainStore.getDepartments().get(j);
                        if (checkIn.getItem(itemID) != null) {
                            departmentIDfromWhere = checkIn.getId();
                            break;
                        }
                    }
                    int ok = 0;
                    if (departmentIDfromWhere == -1)
                        continue;
                    ;
                    for (int j = 0; j < MainStore.getDepartment(departmentIDfromWhere).items.size(); j++) {
                        Item itemToCheck = MainStore.getDepartment(departmentIDfromWhere).items.get(j);
                        if (customerToDel.wishList.getItemByID(itemToCheck.getID()) != null) {
                            ok = 1;
                            break;
                        }
                    }
                    if (ok == 0) {
                        MainStore.getDepartment(departmentIDfromWhere).removeObserver(customerToDel);
                    }
                }
                if (WhereTo.equals("ShoppingCart")) {
                    customerToDel.shoppingCart.removeByID(itemID);
                }
            }

            if (evenType.equals("addProduct")) {
                int DepID = Integer.parseInt(line.nextToken());
                int ItemID = Integer.parseInt(line.nextToken());
                double ItemPrice = Double.parseDouble(line.nextToken());
                String name = line.nextToken();
                Item ItemToADD = new Item(name, ItemID, ItemPrice);
                MainStore.getDepartment(DepID).addItem(ItemToADD);
                Notification notif = new Notification(null, NotificationType.ADD, DepID, ItemID);
                MainStore.getDepartment(DepID).notifyAllObservers(notif);
            }
            if (evenType.equals("modifyProduct")) {
                int DepID = Integer.parseInt(line.nextToken());
                int ItemID = Integer.parseInt(line.nextToken());
                double newPrice = Double.parseDouble(line.nextToken());
                MainStore.getDepartment(DepID).getItem(ItemID).setPrice(newPrice);
                Notification notif = new Notification(null, NotificationType.MODIFY, DepID, ItemID);
                MainStore.getDepartment(DepID).notifyAllObservers(notif);
            }
            if (evenType.equals("delProduct")) {
                int productID = Integer.parseInt(line.nextToken());
                Department departament = null;
                Item toDelete = null;
                for (int j = 0; j < MainStore.getDepartments().size(); j++) {
                    departament = MainStore.getDepartments().get(j);
                    toDelete = departament.getItem(productID);
                    if (toDelete != null) {
                        departament.items.remove(toDelete);
                        break;
                    }
                }
                if (departament != null) {
                    Notification notif = new Notification(null, NotificationType.REMOVE, departament.ID, productID);
                    departament.notifyAllObservers(notif);
                }
            }
            if (evenType.equals("getItems")) {
                String whereTo = line.nextToken();
                String customerName = line.nextToken();
                if (whereTo.equals("ShoppingCart")) {
                    for (int j = 0; j < MainStore.Customers.size(); j++) {
                        Customer toPrint = MainStore.Customers.get(j);
                        if (toPrint.name.equals(customerName)) {
                            System.out.println(toPrint.shoppingCart);
                            break;
                        }
                    }
                } else {
                    for (int j = 0; j < MainStore.Customers.size(); j++) {
                        Customer toPrint = MainStore.Customers.get(j);
                        if (toPrint.name.equals(customerName)) {
                            System.out.println(toPrint.wishList);
                            break;
                        }
                    }
                }
            }
            if (evenType.equals("getTotal")) {
                String whereTo = line.nextToken();
                String customerName = line.nextToken();
                if (whereTo.equals("ShoppingCart")) {
                    for (int j = 0; j < MainStore.Customers.size(); j++) {
                        Customer toPrint = MainStore.Customers.get(j);
                        if (toPrint.name.equals(customerName)) {
                            DecimalFormat df = new DecimalFormat("#.00");
                            System.out.println(df.format(toPrint.shoppingCart.getTotalPrice()));
                            break;
                        }
                    }
                } else {
                    for (int j = 0; j < MainStore.Customers.size(); j++) {
                        Customer toPrint = MainStore.Customers.get(j);
                        if (toPrint.name.equals(customerName)) {
                            DecimalFormat df = new DecimalFormat("#.00");
                            System.out.println(df.format(toPrint.wishList.getTotalPrice()));
                            break;
                        }
                    }
                }
            }
            if (evenType.equals("getObservers")) {
                int DepID = Integer.parseInt(line.nextToken());
                System.out.println(MainStore.getDepartment(DepID).customersWhoWish);
            }
            if (evenType.equals("getNotifications")) {
                String CustName = line.nextToken();
                for (int j = 0; j < MainStore.Customers.size(); j++) {
                    Customer client = MainStore.Customers.get(j);
                    if (client.name.equals(CustName)) {
                        System.out.println(client.notifications);
                        break;
                    }
                }
            }
            if (evenType.equals("getItem")) {
                String CustName = line.nextToken();
                for (int j = 0; j < MainStore.Customers.size(); j++) {
                    Customer client = MainStore.Customers.get(j);
                    if (client.name.equals(CustName)) {
                        System.out.println(client.execute());
                        break;
                    }
                }
            }
            if (evenType.equals("accept")) {
                int DepID = Integer.parseInt(line.nextToken());
                String CustName = line.nextToken();
                Department inWhich = MainStore.getDepartment(DepID);
                for (int j = 0; j < MainStore.Customers.size(); j++) {
                    Customer toWitch = MainStore.Customers.get(j);
                    if (toWitch.name.equals(CustName)) {
                        inWhich.accept(toWitch.shoppingCart);
                        break;
                    }
                }
            }
        }


    }
}
