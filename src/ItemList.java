package src;

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

    Node first;
    Comparator<Item> comparator;

    public ItemList(Comparator comparator) {
        first = null;
        this.comparator = comparator;
    }

    public ItemList() {
        this(null);
    }

    public boolean add(Item item) {
        Node nod = new Node(item);
        if (first == null) {
            first = nod;
            return true;
        }
        Node aux = first;
        while (aux.next != null && comparator.compare(aux.element, item) > 0) {
            aux = aux.next;
        }
        if (aux.next == null) {
            aux.next = nod;
            return true;
        } else {
            nod.prev = aux;
            nod.next = aux.next;
            nod.next.prev = nod;
            aux.next = nod;
            return true;
        }
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        Node aux = first;
        while (aux != null) {
            result.append(aux.element + " ");
            aux = aux.next;
        }
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
        while(aux != null) {
            if(aux.element.getID() == ID)
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
        if(item == null)
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
        if(index == -1)
            return null;
        Node aux = first;
        int i = 0;
        while (i < index && aux != null) {
            i++;
            aux = aux.next;
        }
        if (aux == null)
            return null;
        if(aux.next == null && aux.prev == null) {
            first = null;
            return null;
        }
        if (aux.next == null) {
            aux.prev.next = null;
            aux.prev = null;
            aux.next = null;
            return aux.element;
        }
        if (aux.prev == null) {
            first = aux.next;
            aux.prev = null;
            aux.next = null;
            return aux.element;
        }
        aux.prev.next = aux.next;
        aux.next.prev = aux.prev;
        aux.prev = null;
        aux.next = null;
        return aux.element;
    }

    public boolean remove(Item item) {
        this.remove(indexOf(item));
        return true;
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
