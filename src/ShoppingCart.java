package src;


import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public class ShoppingCart extends ItemList implements Visitor {
    double budget;

    public ShoppingCart(double budget) {
        this.budget = budget;
        super.comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Item i1 = (Item) o1;
                Item i2 = (Item) o2;
                if (i1.getPrice() - i2.getPrice() < 0)
                    return -1;
                else {
                    if (i1.getPrice() - i2.getPrice() > 0)
                        return 1;
                    else
                        return i1.getName().compareTo(i2.getName());
                }
            }
        };
    }

    public boolean add(Item item) {
        if (getTotalPrice() + item.getPrice() < budget)
            return super.add(item);
        else
            return false;
    }

    @Override
    public void visit(BookDepartment bookDepartment) throws CloneNotSupportedException {
        Node aux = first;
        Vector<Item> itemsToDelete = new Vector();
        Vector<Item> itemsToADD = new Vector<>();
        while (aux != null) {
            if (bookDepartment.containsItemByID(aux.element)) {
                Item temp = aux.element;
                itemsToDelete.add(aux.element);
                temp.setPrice(0.9 * temp.getPrice());
                Item clonaa =(Item) temp.clone();
                itemsToADD.add(clonaa);
            }
            aux = aux.next;
        }
        for(int i=0;i<itemsToDelete.size();i++) {
            removeByID(itemsToDelete.get(i).getID());
        }
        for(int i=0;i<itemsToADD.size();i++) {
            add(itemsToADD.get(i));
        }
    }

    @Override
    public void visit(MusicDepartment musicDepartment) {
        Node aux = first;
        double sum = 0;
        while (aux != null) {
            if (musicDepartment.containsItemByID(aux.element)) {
                sum += aux.element.getPrice();
            }
            aux = aux.next;
        }
        budget = budget + 0.1 * sum;
    }

    @Override
    public void visit(SoftwareDepartment softwareDepartment) throws CloneNotSupportedException {
        Node aux = first;
        int ok = 0;
        while (aux != null) {
            if (softwareDepartment.containsItemByID(aux.element)) {
                if(getTotalPrice() + aux.element.getPrice() <= budget) {
                    ok = 1;
                    break;
                }
            }
            aux = aux.next;
        }
        if (ok == 0) {
            aux = first;
            Vector<Item> itemsToDelete = new Vector();
            Vector<Item> itemsToADD = new Vector<>();
            while( aux != null) {
                if (softwareDepartment.containsItemByID(aux.element)) {
                    Item temp = aux.element;
                    itemsToDelete.add(temp);
                    temp.setPrice( 0.8 * temp.getPrice());
                    Item clonaa =(Item) temp.clone();
                    itemsToADD.add(clonaa);
                }
                aux = aux.next;
            }
            for(int i=0;i<itemsToDelete.size();i++) {
                remove(itemsToDelete.get(i));
            }
            for(int i=0;i<itemsToADD.size();i++) {
                add(itemsToADD.get(i));
            }
        }
    }

    @Override
    public void visit(VideoDepartment videoDepartment) throws CloneNotSupportedException {
        Item mostExpensive = videoDepartment.items.get(0);
        for(int i=1;i<videoDepartment.items.size();i++) {
            if(videoDepartment.items.get(i).getPrice() > mostExpensive.getPrice())
                mostExpensive = videoDepartment.items.get(i);
        }
        Node aux = first;
        double sum = 0;
        while(aux != null) {
            if (videoDepartment.containsItemByID(aux.element)) {
                sum += aux.element.getPrice();
            }
            aux = aux.next;
        }
        if(sum > mostExpensive.getPrice()) {
            aux = first;
            Vector<Item> itemsToDelete = new Vector();
            Vector<Item> itemsToAdd = new Vector<>();
            while(aux != null) {
                if (videoDepartment.containsItemByID(aux.element)) {
                    Item temp = aux.element;
                    itemsToDelete.add(temp);
                    temp.setPrice(0.85 * temp.getPrice());
                    Item Clonaa = (Item) temp.clone();
                    itemsToAdd.add(Clonaa);
                }
                aux = aux.next;
            }
            for(int i=0;i<itemsToDelete.size();i++) {
                remove(itemsToDelete.get(i));
            }
            for(int i=0;i<itemsToAdd.size();i++) {
                add(itemsToAdd.get(i));
            }
        }
        sum = 0;
        for(int i=0;i<videoDepartment.items.size();i++) {
            sum += videoDepartment.items.get(i).getPrice();
        }
        budget += 0.05 * sum;
    }
}
