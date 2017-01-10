package src;

import javax.swing.*;
import java.util.Vector;

/**
 * Created by octavian.guzu on 1/10/2017.
 */
public class NotifForm {
    private JList list1;
    public JPanel mainPanel;
    public Customer currentClient;
    Vector<Notification> notifs;

    public NotifForm(Customer currentClient) {
        this.currentClient = currentClient;
        notifs = currentClient.notifications;
        list1.setListData(notifs);
    }

}
