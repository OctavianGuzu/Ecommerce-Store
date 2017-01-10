package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by octavian.guzu on 1/10/2017.
 */
public class WishPanel {
    public JPanel panelMain;
    private JList list1;
    private JButton addProductButton;
    private JButton removeProductButton;
    private JButton getProductButton;
    private JTextField textField1;
    public Customer currentClient;
    Store instance;
    Vector<Item> items;

    public WishPanel(Customer currentClient) {
        this.currentClient = currentClient;
        items = getItems();
        list1.setListData(items);
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instance = Store.getInstance(null, null, null);
                int ID = -1;
                if(!textField1.getText().equals(""))
                    ID = Integer.parseInt(textField1.getText());
                Department whatDepart=null;
                Item toAdd=null;
                for(int i=0;i<instance.getDepartments().size();i++) {
                    whatDepart = instance.getDepartments().get(i);
                    for(int j=0;j<whatDepart.items.size();j++) {
                        if(whatDepart.items.get(j).getID() == ID) {
                            toAdd = whatDepart.items.get(j);
                            break;
                        }
                    }
                }
                if(toAdd != null) {
                    if(!currentClient.wishList.contains(toAdd)) {
                        currentClient.wishList.add(toAdd);
                        items = getItems();
                        list1.setListData(items);
                        panelMain.updateUI();
                    }
                    if(!whatDepart.customersWhoWish.contains(toAdd))
                        whatDepart.addObserver(currentClient);
                }
            }
        });
        removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item toDelete =(Item) list1.getSelectedValue();
                if(toDelete != null)
                    currentClient.wishList.remove(toDelete);
                items = getItems();
                list1.setListData(items);
                panelMain.updateUI();
            }
        });
        getProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!items.isEmpty()) {
                    currentClient.execute();
                    items = getItems();
                    list1.setListData(items);
                    panelMain.updateUI();
                    if(CustomerPanel.shopCart != null)
                        CustomerPanel.shopCart.setVisible(false);
                }
            }
        });
    }

    public Vector<Item> getItems() {
        WishList cart = currentClient.wishList;
        Vector<Item> items =cart.itemsThatExists();
        return items;

    }
}
