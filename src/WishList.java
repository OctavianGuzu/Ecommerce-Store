package src;

import java.util.Comparator;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public class WishList extends ItemList {
    public WishList() {
        super.comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Item i1 = (Item) o1;
                Item i2 = (Item) o2;
                return i1.getName().compareTo(i2.getName());
            }
        };
    }
}
