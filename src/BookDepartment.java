package src;

import java.util.Vector;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public class BookDepartment extends Department {
    @Override
    public void accept(ShoppingCart s) throws CloneNotSupportedException {
        s.visit(this);
    }

    public BookDepartment(String name, Vector<Item> items, int ID) {
        super(name, items, ID);
    }
}
