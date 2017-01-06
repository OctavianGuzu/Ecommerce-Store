package src;

import java.util.Vector;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public class MusicDepartment extends Department {
    @Override
    public void accept(ShoppingCart s) {
        s.visit(this);
    }

    public MusicDepartment(String name, Vector<Item> items, int ID) {
        super(name, items, ID);
    }
}
