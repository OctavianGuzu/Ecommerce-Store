package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by octavian.guzu on 1/9/2017.
 */
public class CustomerPanel {
    private JButton goToYourShoppingButton;
    private JButton seeYourWishListButton;
    public JPanel panelMain;
    private JButton seeYourNotificationsButton;
    static JFrame shopCart;
    static JFrame wishListFrame;
    static JFrame notifFrame;
    public Customer currentClient;

    public CustomerPanel(Customer currentClient) {
        this.currentClient = currentClient;
        goToYourShoppingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shopCart = new JFrame("Shopping Cart");
                shopCart.setContentPane(new shopPanel(currentClient).panelMain);
                //shopCart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                shopCart.setSize(300,400);
                shopCart.pack();
                shopCart.setVisible(true);
            }
        });
        seeYourWishListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wishListFrame = new JFrame("Wish List");
                wishListFrame.setContentPane(new WishPanel(currentClient).panelMain);
                //wishListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                wishListFrame.setSize(300,400);
                wishListFrame.pack();
                wishListFrame.setVisible(true);
            }
        });
        seeYourNotificationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifFrame = new JFrame("Wish List");
                notifFrame.setContentPane(new NotifForm(currentClient).mainPanel);
                //notifFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                notifFrame.setSize(300,400);
                notifFrame.pack();
                notifFrame.setVisible(true);
            }
        });
    }
}
