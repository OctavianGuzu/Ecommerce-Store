package src;

import sun.reflect.generics.visitor.Visitor;

import java.util.Comparator;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public class ShoppingCart extends ItemList{
    public ShoppingCart() {
        super(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
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
            }
        });
    }

    public static void main(String args[]) {
        ShoppingCart cart = new ShoppingCart();
        cart.add(new Item("Octav",10,2.5));
        cart.add(new Item("Radu",11,3.88));
        cart.add(new Item("Mihai",12,2.87));
        cart.add(new Item("Ana",13,2.87));
        System.out.println(cart);
        System.out.println(cart.getTotalPrice());
    }
}
