package src;

import java.util.Date;

/**
 * Created by octavian.guzu on 1/4/2017.
 */

enum Type {
    ADD, REMOVE, MODIFY
}
public class Notification {
    Date data;
    Type type;
    int departmentID;
    int productID;

    public Notification(Date data, Type type, int departmentID, int productID) {
        this.data = data;
        this.type = type;
        this.departmentID = departmentID;
        this.productID = productID;
    }
}
