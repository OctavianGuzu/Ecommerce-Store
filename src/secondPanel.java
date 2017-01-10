package src;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by octavian.guzu on 1/9/2017.
 */
public class secondPanel {
    JPanel panelMain;
    private JButton customersButton;
    private JButton storeAdministrationButton;
    private JPasswordField passwordField1;
    private JButton storeProductsButton;
    static JFrame adminFrame;
    static JFrame customersFrame;
    static JFrame allItems;

    public secondPanel() {
        passwordField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, passwordField1.getPassword());
            }
        });
        storeAdministrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, String.valueOf(passwordField1.getPassword()));
                if(String.valueOf(passwordField1.getPassword()).equals("octav")) {
                    // Enabled for the moment
                    GUI.secondframe.setVisible(true);
                    adminFrame = new JFrame("Administration page");
                    adminFrame.setContentPane(new StoreAdministration().panelMain);
                    //adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    adminFrame.setSize(300,400);
                    adminFrame.pack();
                    adminFrame.setVisible(true);
                }
                else
                    JOptionPane.showMessageDialog(null, "Incorrect password");
            }
        });
        customersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customersFrame = new JFrame("Store Customers");
                customersFrame.setContentPane(new Customers().panelMain);
                //customersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                customersFrame.setSize(800,800);
                customersFrame.pack();
                customersFrame.setVisible(true);
            }
        });
        storeProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allItems = new JFrame("Store Products");
                allItems.setContentPane(new StoreProducts().panelMain);
                //allItems.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                allItems.setSize(800,800);
                allItems.pack();
                allItems.setVisible(true);
            }
        });
    }
}
