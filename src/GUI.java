package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Created by octavian.guzu on 1/9/2017.
 */
public class GUI{
    private JButton enterStore;
    private JButton loadStore;
    private JButton loadCustomers;
    private JPanel panelMain;
    private String storeName;
    private Vector<Department> departments;
    private Vector<Customer> customers;
    private JPanel secondary = new JPanel();
    private JButton storeAdministration = new JButton("Store Administration");
   // JFrame frame = new JFrame("Ecommerce Store");

    static JFrame frame;
    static JFrame secondframe;
    public GUI() {
        loadStore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Showing test message");
                try {
                    departments = mergeDepartments("store.txt");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                loadCustomers.setEnabled(true);
            }
        });
        loadCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    customers = getCustomers("customers.txt");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                enterStore.setEnabled(true);
            }
        });
        enterStore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Store MainStore = Store.getInstance(storeName, customers, departments);
                //System.out.println(MainStore.name);
                //JOptionPane.showMessageDialog(null,storeName);
                GUI.frame.setVisible(false);
                secondframe = new JFrame(storeName);
                secondframe.setContentPane(new secondPanel().panelMain);
                secondframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                secondframe.setSize(300,400);
                secondframe.pack();
                secondframe.setVisible(true);

            }
        });
    }

    public void show() {
        frame.setContentPane(new GUI().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,400);
        frame.pack();
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }

    public Vector<Customer> getCustomers(String customersFile) throws IOException {
        Vector<Customer> customers = new Vector<>();
        BufferedReader customer = new BufferedReader(new FileReader(customersFile));
        int noCustomers = Integer.parseInt(customer.readLine());
        for (int i = 0; i < noCustomers; i++) {
            StringTokenizer line = new StringTokenizer(customer.readLine(), ";");
            String customerName = line.nextToken();
            double budget = Double.parseDouble(line.nextToken());
            String strategy = line.nextToken();
            int annoyingIntelij = 0;
            customers.add(new Customer(customerName, budget, strategy));
        }
        return customers;
    }

    public Department createDepartment(BufferedReader st) throws IOException {
        StringTokenizer departmentInfo = new StringTokenizer(st.readLine(), ";");
        String departmentName = departmentInfo.nextToken();
        int deparmentID = Integer.parseInt(departmentInfo.nextToken());
        int noProducts = Integer.parseInt(st.readLine());
        Vector<Item> items = new Vector<>();
        for (int j = 0; j < noProducts; j++) {
            StringTokenizer product = new StringTokenizer(st.readLine(), ";");
            String itemName = product.nextToken();
            int itemID = Integer.parseInt(product.nextToken());
            int itemID2 = itemID;
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

    public Vector<Department> mergeDepartments(String departmentFile) throws IOException {
        BufferedReader store = new BufferedReader(new FileReader(departmentFile));
        storeName = store.readLine();
        Vector<Department> departments = new Vector<>();
        Test obj = new Test();

        // Reading Departments
        departments.add(obj.createDepartment(store));
        departments.add(obj.createDepartment(store));
        departments.add(obj.createDepartment(store));
        departments.add(obj.createDepartment(store));
        return departments;
    }

    public static void main(String[] args) {
        frame = new JFrame("Ecommerce Store");
        frame.setContentPane(new GUI().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,400);
        frame.pack();
        frame.setVisible(true);
    }
}
