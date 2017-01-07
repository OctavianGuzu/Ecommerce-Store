package src;

import java.util.Vector;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public class Customer extends Strategy implements Observer {
    String name = new String();
    ShoppingCart shoppingCart;
    WishList wishList;
    Vector<Notification> notifications;
    String strategy;
    double budget;

    public Customer(String name, double budget, String strategy) {
        this.name = name;
        shoppingCart = new ShoppingCart(budget);
        wishList = new WishList();
        notifications = new Vector<>();
        this.strategy = strategy;
        this.budget = budget;
    }

    public String toString() {
        return name;
    }

    @Override
    public void update(Notification notification) throws CloneNotSupportedException {
        if (!notifications.contains(notification))
            notifications.add(notification);

        if (notification.type == NotificationType.REMOVE) {
            shoppingCart.removeByID(notification.productID);
            wishList.removeByID(notification.productID);
            Store magazin = Store.getInstance("", null, null);
            int productID = notification.productID;
            int departID = notification.departmentID;
            Department fromWhich = magazin.getDepartment(departID);
            int ok = 0;
            for (int j = 0; j < fromWhich.items.size(); j++) {
                Item toCheck = fromWhich.items.get(j);
                if (wishList.getItemByID(toCheck.getID()) != null) {
                    ok = 1;
                    break;
                }
            }
            if (ok == 0) {
                fromWhich.removeObserver(this);
            }
        }

        if (notification.type == NotificationType.MODIFY) {
            Store shop = Store.getInstance("", null, null);
            Department department = shop.getDepartment(notification.departmentID);
            Item newItem = department.getItem(notification.productID);
            if (shoppingCart.getItemByID(notification.productID) != null) {
                shoppingCart.removeByID(notification.productID);
                Item itemClone = (Item) newItem.clone();
                shoppingCart.add(itemClone);
            }
            if (wishList.getItemByID(notification.productID) != null) {
                wishList.removeByID(notification.productID);
                Item itemClone = (Item) newItem.clone();
                wishList.add(itemClone);
            }
        }
    }

    public Item execute() {
        if (strategy.equals("A")) {
            Item toReturn = wishList.getCheapest();
            if (shoppingCart.getTotalPrice() + toReturn.getPrice() < budget) {
                wishList.remove(toReturn);
                shoppingCart.add(toReturn);
                int DepartmentFrom = -1;
                Store shopping = Store.getInstance("", null, null);
                for (int j = 0; j < shopping.getDepartments().size(); j++) {
                    Department toLookIn = shopping.getDepartments().get(j);
                    if (toLookIn.getItem(toReturn.getID()) != null) {
                        DepartmentFrom = toLookIn.getId();
                        break;
                    }
                }
                int ok = 0;
                for (int j = 0; j < shopping.getDepartment(DepartmentFrom).items.size(); j++) {
                    Item itemToCheck = shopping.getDepartment(DepartmentFrom).items.get(j);
                    if (wishList.getItemByID(itemToCheck.getID()) != null) {
                        ok = 1;
                        break;
                    }
                }
                if (ok == 0) {
                    shopping.getDepartment(DepartmentFrom).removeObserver(this);
                }
            }
            return toReturn;
        }
        if (strategy.equals("B")) {
            Item toReturnn = wishList.getFirst();
            if (shoppingCart.getTotalPrice() + toReturnn.getPrice() < budget) {
                wishList.remove(wishList.first.element);
                shoppingCart.add(toReturnn);
                int DepartmentFrom = -1;
                Store shopping = Store.getInstance("", null, null);
                for (int j = 0; j < shopping.getDepartments().size(); j++) {
                    Department toLookIn = shopping.getDepartments().get(j);
                    if (toLookIn.getItem(toReturnn.getID()) != null) {
                        DepartmentFrom = toLookIn.getId();
                        break;
                    }
                }
                int ok = 1;
                for (int j = 0; j < shopping.getDepartment(DepartmentFrom).items.size(); j++) {
                    Item itemToCheck = shopping.getDepartment(DepartmentFrom).items.get(j);
                    if (wishList.getItemByID(itemToCheck.getID()) != null) {
                        ok = 0;
                        break;
                    }
                }
                if (ok == 1) {
                    shopping.getDepartment(DepartmentFrom).removeObserver(this);
                }
            }
            return toReturnn;
        }
        if (strategy.equals("C")) {
            Item toReturnn = wishList.getLastItem();
            if (shoppingCart.getTotalPrice() + toReturnn.getPrice() < budget) {
                wishList.remove(toReturnn);
                shoppingCart.add(toReturnn);
                int DepartmentFrom = -1;
                Store shopping = Store.getInstance("", null, null);
                for (int j = 0; j < shopping.getDepartments().size(); j++) {
                    Department toLookIn = shopping.getDepartments().get(j);
                    if (toLookIn.getItem(toReturnn.getID()) != null) {
                        DepartmentFrom = toLookIn.getId();
                        break;
                    }
                }
                int ok = 2;
                for (int j = 0; j < shopping.getDepartment(DepartmentFrom).items.size(); j++) {
                    Item itemToCheck = shopping.getDepartment(DepartmentFrom).items.get(j);
                    if (wishList.getItemByID(itemToCheck.getID()) != null) {
                        ok = 0;
                        break;
                    }
                }
                if (ok == 2) {
                    shopping.getDepartment(DepartmentFrom).removeObserver(this);
                }
            }
            return toReturnn;
        }
        return null;
    }
}
