package src;

/**
 * Created by octavian.guzu on 1/6/2017.
 */
public interface Subject {
    public void notifyAllObservers(Notification n);
    public void addObserver(Customer c);
    public void removeObserver(Customer c);
}
