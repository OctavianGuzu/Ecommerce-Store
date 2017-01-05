package src;

import java.util.Vector;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public class Customer implements Observer {
    String name = new String();
    ShoppingCart shoppingCart;
    WishList wishList;
    Vector<Notification> notifications;

    public Customer(String name, double budget) {
        this.name = name;
        shoppingCart = new ShoppingCart(budget);
        wishList = new WishList();
        notifications = new Vector<>();
    }

    @Override
    public void update(Notification notification) {

    }
}
