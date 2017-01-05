package src;



import java.util.Comparator;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public class ShoppingCart extends ItemList implements Visitor{
    double budget;
    public ShoppingCart(double budget) {
        super((o1, o2) -> {
            Item i1 = (Item) o1;
            Item i2 = (Item) o2;
            if(i1.getPrice() - i2.getPrice() < 0)
                return -1;
            else {
                if(i1.getPrice() - i2.getPrice() > 0)
                    return 1;
                else
                    return i1.getName().compareTo(i2.getName());
            }
        });

        this.budget = budget;
    }

    public boolean add(Item item) {
        if(getTotalPrice() + item.getPrice() < budget)
            return super.add(item);
        else
            return false;
    }

    @Override
    public void visit(BookDepartment bookDepartment) {
            Node aux = first;
            while(aux != null) {
                if(bookDepartment.getItems().contains(aux.element)) {
                    aux.element.setPrice(aux.element.getPrice() - 0.1 * aux.element.getPrice());
                    aux = aux.next;
                }
            }
    }

    @Override
    public void visit(MusicDepartment musicDepartment) {

    }

    @Override
    public void visit(SoftwareDepartment softwareDepartment) {

    }

    @Override
    public void visit(VideoDepartment videoDepartment) {

    }
}
