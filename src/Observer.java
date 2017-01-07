package src;

/**
 * Created by octavian.guzu on 1/5/2017.
 */
public interface Observer {
    public abstract void update(Notification notification) throws CloneNotSupportedException;
}
