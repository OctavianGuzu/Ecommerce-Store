package src;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public class VideoDepartment extends Department {
    @Override
    public void accept(ShoppingCart s) {
        s.visit(this);
    }
}
