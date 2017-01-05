package src;

import java.util.Vector;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public abstract class Department {
    String name;
    Vector<Item> items = new Vector<>();
    Vector<Customer> customersWhoBought = new Vector<>();
    Vector<Customer> customersWhoWish = new Vector<>();
    int ID;

    public void enter(Customer c) {
        customersWhoBought.add(c);
    }

    public void exit(Customer c) {
        customersWhoBought.remove(c);
    }

    public Vector<Customer> getCustomers() {
        return customersWhoBought;
    }

    public int getId() {
        return ID;
    }

    public void addItem(Item i) {
        items.add(i);
    }

    public Vector<Item> getItems() {
        return items;
    }

    public void addObserver(Customer c) {
        customersWhoWish.add(c);
    }

    public void removeObserver(Customer c) {
        customersWhoWish.remove(c);
    }

    public void notifyAllObservers(Notification n) {
        // TODO
    }

    public abstract void accept(ShoppingCart s);
}
