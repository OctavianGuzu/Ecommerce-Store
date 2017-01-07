package src;

import java.util.Vector;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public class VideoDepartment extends Department {
    @Override
    public void accept(ShoppingCart s) throws CloneNotSupportedException {
        s.visit(this);
    }

    public VideoDepartment(String name, Vector<Item> items, int ID) {
        super(name, items, ID);
    }
}
