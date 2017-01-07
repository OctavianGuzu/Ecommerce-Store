package src;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by octavian.guzu on 1/4/2017.
 */
public abstract class ItemList {
    class Node {
        Item element;
        Node next;
        Node prev;

        public Node(Item element, Node next, Node prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public Node(Item element) {
            this(element, null, null);
        }

        public String toString() {
            return "(" + element + ")";
        }
    }

    Node first = null;
    Comparator comparator;
    LinkedList<Item> theOrder = new LinkedList<>();


    public boolean add(Item item) {
        Node nod = new Node(item);
        if (first == null) {
            first = nod;
            theOrder.addFirst(nod.element);
            return true;
        }
        Node aux = first;
        if (aux.next == null) {
            if (comparator.compare(item, aux.element) < 0) {
                nod.next = aux;
                aux.prev = nod;
                first = nod;
                theOrder.addFirst(nod.element);
                return true;
            } else {
                aux.next = nod;
                nod.prev = aux;
                theOrder.addFirst(nod.element);
                return true;
            }
        }
        if (aux.next != null && comparator.compare(item, aux.element) < 0) {
            nod.next = aux;
            aux.prev = nod;
            first = nod;
            theOrder.addFirst(nod.element);
            return true;
        } else {
            while (aux.next != null && comparator.compare(item, aux.next.element) > 0) {
                aux = aux.next;
            }
            if (aux.next != null) {
                nod.prev = aux;
                nod.next = aux.next;
                nod.next.prev = nod;
                aux.next = nod;
                theOrder.addFirst(nod.element);
                return true;
            } else {
                aux.next = nod;
                nod.prev = aux;
                theOrder.addFirst(nod.element);
                return true;
            }
        }
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        Node aux = first;
        result.append("[");
        if (aux == null) {
            result.append("]");
            return result.toString();
        }
        while (aux.next != null) {
            result.append(aux.element + ", ");
            aux = aux.next;
        }
        result.append(aux.element);
        result.append("]");
        return result.toString();
    }

    public boolean addAll(Collection<? extends Item> c) {
        List list = new ArrayList(c);
        for (int i = 0; i < list.size(); i++)
            add((Item) list.get(i));
        return true;
    }

    public Item getItem(int index) {
        int i = 0;
        Node aux = first;
        while (i < index && aux != null) {
            aux = aux.next;
            i++;
        }
        if (aux == null)
            return null;
        return aux.element;
    }

    public Item getItemByID(int ID) {
        int i = 0;
        Node aux = first;
        while (aux != null) {
            if (aux.element.getID() == ID)
                return aux.element;
            aux = aux.next;
        }
        return null;
    }

    public Node getNode(int index) {
        int i = 0;
        Node aux = first;
        while (i < index && aux != null) {
            aux = aux.next;
            i++;
        }
        if (aux == null)
            return null;
        return aux;
    }

    public int indexOf(Item item) {
        if (item == null)
            return -1;
        Node aux = first;
        int index = 0;
        while (aux != null) {
            if (aux.element.getID() == item.getID())
                return index;
            index++;
            aux = aux.next;
        }
        return -1;
    }

    public int indexOf(Node node) {
        Node aux = first;
        int index = 0;
        while (aux != null) {
            if (aux.element.getID() == node.element.getID())
                return index;
            index++;
            aux = aux.next;
        }
        return -1;
    }

    public boolean contains(Node node) {
        Node aux = first;
        while (aux != null) {
            if (aux.element.getID() == node.element.getID())
                return true;
            aux = aux.next;
        }
        return false;
    }

    public boolean contains(Item item) {
        Node aux = first;
        while (aux != null) {
            if (aux.element.getID() == item.getID())
                return true;
            aux = aux.next;
        }
        return false;
    }

    public Item remove(int index) {
        if (index == -1)
            return null;
        Node aux = first;
        int i = 0;
        while (i < index && aux != null) {
            i++;
            aux = aux.next;
        }
        if (aux == null)
            return null;
        if (aux.next == null && aux.prev == null) {
            first = null;
            theOrder.remove(aux.element);
            return null;
        }
        if (aux.next == null) {
            aux.prev.next = null;
            aux.prev = null;
            aux.next = null;
            theOrder.remove(aux.element);
            return aux.element;
        }
        if (aux.prev == null) {
            first = aux.next;
            first.prev = null;
            aux.prev = null;
            aux.next = null;
            theOrder.remove(aux.element);
            return aux.element;
        }
        aux.prev.next = aux.next;
        aux.next.prev = aux.prev;
        aux.prev = null;
        aux.next = null;
        theOrder.remove(aux.element);
        return aux.element;
    }

    public boolean remove(Item item) {
        this.remove(indexOf(item));
        return true;
    }

    public Item getLastItem() {
        return theOrder.getFirst();
    }

    public void removeByID(int ID) {
        this.remove(getItemByID(ID));
    }

    public boolean removeAll(Collection<? extends Item> c) {
        List list = new ArrayList(c);
        for (int i = 0; i < list.size(); i++)
            remove((Item) list.get(i));
        return true;
    }

    public boolean isEmpty() {
        if (first == null)
            return true;
        return false;
    }

    public Item getCheapest() {
        Node toReturn = first;
        if (toReturn == null)
            return null;
        if (toReturn.next == null)
            return toReturn.element;
        Node aux = first.next;
        while (aux != null) {
            if (aux.element.getPrice() - toReturn.element.getPrice() < 0)
                toReturn = aux;
            aux = aux.next;
        }
        return toReturn.element;
    }

    public Item getFirst() {
        if (first == null)
            return null;
        return first.element;
    }

    public ListIterator<Node> listIterator() {
        return new ItemIterator();
    }

    public ListIterator<Node> listIterator(int index) {
        ItemIterator it = new ItemIterator();
        int i = 0;
        while (i < index && it.hasNext())
            it.next();
        return it;
    }

    public Double getTotalPrice() {
        Node aux = first;
        double result = 0;
        DecimalFormat df = new DecimalFormat("#.00");
        while (aux != null) {
            result += aux.element.getPrice();
            aux = aux.next;
        }
        return result;
    }

    class ItemIterator implements ListIterator<Node> {
        Node current = first;
        int index = 0;

        @Override
        public boolean hasNext() {
            if (current != null)
                return true;
            return false;
        }

        @Override
        public Node next() {
            index++;
            Node aux = current;
            current = current.next;
            return aux;
        }

        @Override
        public boolean hasPrevious() {
            if (current.prev != null)
                return true;
            return false;
        }

        @Override
        public Node previous() {
            index--;
            current = current.prev;
            return current;
        }

        @Override
        public int nextIndex() {
            return index + 1;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(Node node) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(Node node) {
            throw new UnsupportedOperationException();
        }
    }
}
