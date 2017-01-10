package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

/**
 * Created by octavian.guzu on 1/9/2017.
 */
public class StoreAdministration {
    public JPanel panelMain;
    private JList list1;
    private JButton addProductButton;
    private JButton removeProductButton;
    private JTextField IDfield;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField departmentField;
    private JComboBox sortingBox;
    private JButton sortItemsButton;
    Vector<Item> items;

    public StoreAdministration() {
        items = getItems();
        list1.setListData(items);
        removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    delProduct(((Item) list1.getSelectedValue()).getID());
                } catch (CloneNotSupportedException e1) {
                    e1.printStackTrace();
                }
                //items = getItems();
                list1.setListData(items);
                panelMain.updateUI();
                if(CustomerPanel.shopCart != null)
                    CustomerPanel.shopCart.setVisible(false);
                if(CustomerPanel.wishListFrame != null)
                    CustomerPanel.wishListFrame.setVisible(false);
            }
        });
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!IDfield.getText().equals("") &&
                        !nameField.getText().equals("") &&
                        !priceField.getText().equals("") &&
                        !departmentField.getText().equals("")) {
                    int ok= 0;
                    for(int i=0;i<items.size();i++)
                        if(items.get(i).getID() == Integer.parseInt(IDfield.getText()))
                            ok = 1;
                    if(ok == 1) {
                        JOptionPane.showMessageDialog(null, "There is already " +
                                "a product with this ID in the Store!");
                    }
                    else {
                        addProduct(Integer.parseInt(IDfield.getText()), nameField.getText(),
                                Double.parseDouble(priceField.getText()), departmentField.getText());
                        items = getItems();
                        list1.setListData(items);
                        panelMain.updateUI();
                        Store inst = Store.getInstance(null,null,null);
                        for(int i=0;i<inst.getDepartments().size();i++) {
                            if(inst.getDepartments().get(i).name.equals(departmentField.getText())) {
                                try {
                                    inst.getDepartments().get(i).notifyAllObservers(
                                            new Notification(null, NotificationType.ADD,
                                                    inst.getDepartments().get(i).ID, Integer.parseInt(IDfield.getText()))
                                    );
                                } catch (CloneNotSupportedException e1) {
                                    e1.printStackTrace();
                                }
                                break;
                            }
                        }
                        if(CustomerPanel.notifFrame != null)
                            CustomerPanel.notifFrame.setVisible(false);
                    }
                }
            }
        });
        sortItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sortingBox.getSelectedItem().equals("Alphabetically")) {
                    Collections.sort(items, new Comparator<Item>() {
                        @Override
                        public int compare(Item o1, Item o2) {
                            return o1.getName().compareTo(o2.getName());
                        }
                    });
                    list1.setListData(items);
                    panelMain.updateUI();
                }
                if(sortingBox.getSelectedItem().equals("Increasing by price")) {
                    Collections.sort(items, new Comparator<Item>() {
                        @Override
                        public int compare(Item o1, Item o2) {
                            return (o1.getPrice() - o2.getPrice()) > 0 ? 1 : -1;
                        }
                    });
                    list1.setListData(items);
                    panelMain.updateUI();
                }
                if(sortingBox.getSelectedItem().equals("Decreasing by price")) {
                    Collections.sort(items, new Comparator<Item>() {
                        @Override
                        public int compare(Item o1, Item o2) {
                            return (o2.getPrice() - o1.getPrice()) > 0 ? 1 : -1;
                        }
                    });
                    list1.setListData(items);
                    panelMain.updateUI();
                }
            }
        });
    }

    public void addProduct(int ID, String name, double price, String Deparment) {
        Store obj = Store.getInstance("",null,null);
        Item itemToAdd = new Item(name, ID, price);
        for(int i=0;i<obj.getDepartments().size();i++) {
            if(obj.getDepartments().get(i).name.equals(Deparment)) {
                obj.getDepartments().get(i).addItem(itemToAdd);
                break;
            }
        }
    }

    public Vector<Item> getItems() {
        Vector items = new Vector();
        Store obj = Store.getInstance("",null,null);
        for(int i=0;i<obj.getDepartments().size();i++) {
            for(int j=0;j<obj.getDepartments().get(i).items.size();j++) {
                items.add(obj.getDepartments().get(i).items.get(j));
            }
        }
        return items;
    }

    public void delProduct(int ID) throws CloneNotSupportedException {
        int productID = ID;
        Department departament = null;
        Item toDelete = null;
        Store obj = Store.getInstance("",null,null);
        for (int j = 0; j < obj.getDepartments().size(); j++) {
            departament = obj.getDepartments().get(j);
            toDelete = departament.getItem(productID);
            if (toDelete != null) {
                departament.items.remove(toDelete);
                break;
            }
        }
        for(int i=0;i<this.items.size();i++) {
            if(this.items.get(i).getID() == ID) {
                this.items.remove(i);
                break;
            }
        }
        if (departament != null) {
            //Notification notif = new Notification(null, NotificationType.REMOVE, departament.ID, productID);
            //departament.notifyAllObservers(notif);
        }
    }
}
