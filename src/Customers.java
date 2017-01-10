package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by octavian.guzu on 1/9/2017.
 */
public class Customers {
    private JList clientsList;
    private JButton proceedButton;
    public JPanel panelMain;
    Vector<Customer> clienti;
    static JFrame selectedClient;

    public Customers() {
        Store obj = Store.getInstance(null,null,null);
        clienti = obj.getCustomers();
        clientsList.setListData(clienti);
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Disabled for the moment
                if(clientsList.getSelectedValue() != null) {
                    secondPanel.customersFrame.setVisible(false);
                    selectedClient = new JFrame(clientsList.getSelectedValue().toString());
                    selectedClient.setContentPane(new CustomerPanel((Customer) clientsList.getSelectedValue()).panelMain);
                    selectedClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    selectedClient.setSize(300, 400);
                    selectedClient.pack();
                    selectedClient.setVisible(true);
                }
            }
        });
    }
}
