package src;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public class Store {
    String name = new String();
    Vector<Customer> Customers = new Vector<>();
    Vector<Department> Departments = new Vector<>();
    private static Store instance;

    public void enter(Customer c) {
        Customers.add(c);
    }

    public void exit(Customer c) {
        Customers.remove(c);
    }

    public ShoppingCart getShoppingCart(Double budget) {
        return new ShoppingCart(budget);
    }

    public Vector<Customer> getCustomers() {
        return Customers;
    }

    public Vector<Department> getDepartments() {
        return Departments;
    }

    public void addDepartment(Department d) {
        Departments.add(d);
    }

    public Department getDepartment(Integer integer) {
        for (int i = 0; i < Departments.size(); i++)
            if (Departments.get(i).getId() == integer)
                return Departments.get(i);
        return null;
    }

    private Store(String name, Vector<Customer> Customers, Vector<Department> Departments) {
        this.name = name;
        this.Customers = Customers;
        this.Departments = Departments;
    }

    public static Store getInstance(String name, Vector<Customer> Customers, Vector<Department> Departments) {
        if(instance == null)
            instance = new Store(name, Customers, Departments);
        return instance;
    }

}
