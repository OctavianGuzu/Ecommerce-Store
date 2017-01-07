package src;

import java.text.DecimalFormat;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public class Item implements Cloneable{
    private String name = new String();
    private int ID;
    private double price;

    public Item(String name, int ID, double price) {
        this.name = name;
        this.ID = ID;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        return name + ";" + ID + ";" + df.format(price);
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
