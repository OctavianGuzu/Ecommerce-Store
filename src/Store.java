package src;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public class Store {
    String name = new String();
    Vector Customers = new Vector<Customer>();
    Vector Departments = new Vector<Department>();

    public void enter(Customer c) {
        Customers.add(c);
    }

    public void exit(Customer c) {
        Customers.remove(c);
    }

    public ShoppingCart getShoppingCart(Double budget) {
        return null;
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

    public Department getDepartment(Integer i) {
        return null;
    }
}
