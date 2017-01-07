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
    String strategy;
    double budget;

    public Customer(String name, double budget, String strategy) {
        this.name = name;
        shoppingCart = new ShoppingCart(budget);
        wishList = new WishList();
        notifications = new Vector<>();
        this.strategy = strategy;
        this.budget = budget;
    }

    public String toString() {
        return name;
    }

    @Override
    public void update(Notification notification) throws CloneNotSupportedException {
        notifications.add(notification);

        if(notification.type == NotificationType.REMOVE) {
            shoppingCart.removeByID(notification.productID);
            wishList.removeByID(notification.productID);
        }

        if(notification.type == NotificationType.MODIFY) {
            Store shop = Store.getInstance("",null,null);
            Department department = shop.getDepartment(notification.departmentID);
            Item newItem = department.getItem(notification.productID);
            if(shoppingCart.getItemByID(notification.productID) != null) {
                shoppingCart.removeByID(notification.productID);
                Item itemClone = (Item) newItem.clone();
                shoppingCart.add(itemClone);
            }
            if(wishList.getItemByID(notification.productID) != null) {
                wishList.removeByID(notification.productID);
                Item itemClone = (Item) newItem.clone();
                wishList.add(itemClone);
            }
        }
    }
}
