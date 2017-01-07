package src;

import java.util.Vector;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public class SoftwareDepartment extends Department {
    @Override
    public void accept(ShoppingCart s) throws CloneNotSupportedException {
        s.visit(this);
    }

    public SoftwareDepartment(String name, Vector<Item> items, int ID) {
        super(name, items, ID);
    }
}
