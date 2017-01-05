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
        super((o1, o2) -> {
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
        });

        this.budget = budget;
    }

    public boolean add(Item item) {
        if (getTotalPrice() + item.getPrice() < budget)
            return super.add(item);
        else
            return false;
    }

    @Override
    public void visit(BookDepartment bookDepartment) {
        Node aux = first;
        Vector<Item> itemsToDelete = new Vector();
        while (aux != null) {
            if (bookDepartment.getItems().contains(aux.element)) {
                Item temp = aux.element;
                itemsToDelete.add(aux.element);
                temp.setPrice(temp.getPrice() - 0.1 * temp.getPrice());
                add(temp);
            }
            aux = aux.next;
        }
        for(int i=0;i<itemsToDelete.size();i++) {
            remove(itemsToDelete.get(i));
        }
    }

    @Override
    public void visit(MusicDepartment musicDepartment) {
        Node aux = first;
        double sum = 0;
        while (aux != null) {
            if (musicDepartment.getItems().contains(aux.element)) {
                sum += aux.element.getPrice();
            }
            aux = aux.next;
        }
        budget = budget + 0.1 * sum;
    }

    @Override
    public void visit(SoftwareDepartment softwareDepartment) {
        Node aux = first;
        int ok = 0;
        while (aux != null) {
            if (softwareDepartment.getItems().contains(aux.element)) {
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
            while( aux != null) {
                if (softwareDepartment.getItems().contains(aux.element)) {
                    Item temp = aux.element;
                    itemsToDelete.add(temp);
                    temp.setPrice( 0.8 * temp.getPrice());
                    add(temp);
                }
                aux = aux.next;
            }
            for(int i=0;i<itemsToDelete.size();i++) {
                remove(itemsToDelete.get(i));
            }
        }
    }

    @Override
    public void visit(VideoDepartment videoDepartment) {
        Item mostExpensive = videoDepartment.items.get(0);
        for(int i=1;i<videoDepartment.items.size();i++) {
            if(videoDepartment.items.get(i).getPrice() > mostExpensive.getPrice())
                mostExpensive = videoDepartment.items.get(i);
        }
        Node aux = first;
        double sum = 0;
        while(aux != null) {
            if (videoDepartment.getItems().contains(aux.element)) {
                sum += aux.element.getPrice();
            }
            aux = aux.next;
        }
        if(sum > mostExpensive.getPrice()) {
            aux = first;
            Vector<Item> itemsToDelete = new Vector();
            while(aux != null) {
                if (videoDepartment.getItems().contains(aux.element)) {
                    Item temp = aux.element;
                    itemsToDelete.add(temp);
                    temp.setPrice(0.85 * temp.getPrice());
                    add(temp);
                }
                aux = aux.next;
            }
            for(int i=0;i<itemsToDelete.size();i++) {
                remove(itemsToDelete.get(i));
            }
        }
        sum = 0;
        for(int i=0;i<videoDepartment.items.size();i++) {
            sum += videoDepartment.items.get(i).getPrice();
        }
        budget += 0.05 * sum;
    }
}
