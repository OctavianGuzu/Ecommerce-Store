package src;

import java.util.Vector;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public class Customer {
    String name = new String();
    ShoppingCart shoppingCart = new ShoppingCart();
    WishList wishList = new WishList();
    Vector<Notification> notifications = new Vector<Notification>();
}
