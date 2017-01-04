package src;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public class Item {
    private String name = new String();
    private int ID;
    private float price;

    public Item(String name, int ID, float price) {
        this.name=name;
        this.ID=ID;
        this.price=price;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
