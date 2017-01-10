package src;

import javax.swing.*;
import java.util.Vector;

/**
 * Created by octavian.guzu on 1/10/2017.
 */
public class StoreProducts {
    private JList list1;
    public JPanel panelMain;

    public StoreProducts() {
        Store instance = Store.getInstance(null,null,null);
        Vector items = new Vector();
        for(int i=0;i<instance.getDepartments().size();i++) {
            items.add("// "+instance.getDepartments().get(i).name+" //");
            items.add(" ");
            for(int j=0;j<instance.getDepartments().get(i).getItems().size();j++) {
                items.add(instance.getDepartments().get(i).getItems().get(j));
            }
            items.add(" ");
        }
        list1.setListData(items);
    }
}
