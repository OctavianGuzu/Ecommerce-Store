package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by octavian.guzu on 1/10/2017.
 */
public class shopPanel {
    public JPanel panelMain;
    private JList list1;
    private JButton orderProductsButton;
    private JButton addProductButton;
    private JButton removeProductButton;
    private JTextField textField1;
    private JLabel totalProducts;
    private JLabel totalBudget;
    public Customer currentClient;
    public Vector<Item> items;
    double budget;
    Store instance;


    public shopPanel(Customer currentClient) {
        this.currentClient = currentClient;
        items = getItems();
        list1.setListData(items);
        totalBudget.setText(String.valueOf(currentClient.budget));
        totalProducts.setText(String.valueOf(currentClient.shoppingCart.getTotalPrice()));
        budget = currentClient.budget;

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instance = Store.getInstance(null, null, null);
                int ID = -1;
                if(!textField1.getText().equals(""))
                    ID = Integer.parseInt(textField1.getText());
                Department whatDepart;
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
                    if(!currentClient.shoppingCart.contains(toAdd)) {
                        currentClient.shoppingCart.add(toAdd);
                        items = getItems();
                        list1.setListData(items);
                        totalProducts.setText(String.valueOf(currentClient.shoppingCart.getTotalPrice()));
                        panelMain.updateUI();
                    }
                }
            }
        });
        removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item toDelete =(Item) list1.getSelectedValue();
                if(toDelete != null)
                    currentClient.shoppingCart.remove(toDelete);
                items = getItems();
                list1.setListData(items);
                totalProducts.setText(String.valueOf(currentClient.shoppingCart.getTotalPrice()));
                panelMain.updateUI();
            }
        });
        orderProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                budget = budget - currentClient.shoppingCart.getTotalPrice();
                totalBudget.setText(String.valueOf(budget));
                currentClient.shoppingCart.removeAll();
                items = getItems();
                list1.setListData(items);
                totalProducts.setText(String.valueOf(currentClient.shoppingCart.getTotalPrice()));
                panelMain.updateUI();
            }
        });
    }
    public Vector<Item> getItems() {
        ShoppingCart cart = currentClient.shoppingCart;
        Vector<Item> items =cart.itemsThatExists();
        return items;

    }

    public  void refresh() {
        items = getItems();
        list1.setListData(items);
        totalProducts.setText(String.valueOf(currentClient.shoppingCart.getTotalPrice()));
        panelMain.updateUI();
    }
}
