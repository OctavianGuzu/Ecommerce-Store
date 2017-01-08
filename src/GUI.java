package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by octavian.guzu on 1/7/2017.
 */
public class GUI {
    private JButton button1;
    private JButton button2;
    private JButton getCustomersButton;

    public GUI() {
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        getCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String args[]) {
        GUI obj = new GUI();
    }
}
