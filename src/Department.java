package src;

import java.util.Vector;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public abstract class Department implements Subject {
    String name;
    Vector<Item> items = new Vector<>();
    Vector<Customer> customersWhoBought = new Vector<>();
    Vector<Customer> customersWhoWish = new Vector<>();
    int ID;

    public boolean containsItemByID(Item i) {
        for(int j=0;j<items.size();j++) {
            Item current = items.get(j);
            if(current.getID() == i.getID())
                return true;
        }
        return false;
    }

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

    public void notifyAllObservers(Notification n) throws CloneNotSupportedException {
        for(int i=0;i<customersWhoWish.size();i++)
            customersWhoWish.get(i).update(n);
    }

    public Item getItem(int ID) {
        for(int i=0;i<items.size();i++)
            if(items.get(i).getID() == ID)
                return items.get(i);
        return null;
    }

    public Department(String name, Vector<Item> items, int ID) {
        this.name = name;
        this.items = items;
        this.ID = ID;
    }

    public Department() {
        this(null, null, 0);
    }

    public abstract void accept(ShoppingCart s) throws CloneNotSupportedException;
}
