package src;

/**
 * Created by octavian.guzu on 1/5/2017.
 */
public class Test {
    public static void main(String args[]) {
        ShoppingCart cart = new ShoppingCart(10);
        cart.add(new Item("Octav",10,2.5));
        cart.add(new Item("Radu",11,3.88));
        cart.add(new Item("Mihai",12,2.87));
        cart.add(new Item("Ana",13,2.87));
        System.out.println(cart);
        System.out.println(cart.getTotalPrice());
    }
}
