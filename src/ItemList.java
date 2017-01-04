package src;

import java.util.ListIterator;

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
            return "("+element+")";
        }
    }

    Node first=null;
    Node last=null;

    class ItemIterator implements ListIterator {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public Object previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(Object o) {

        }

        @Override
        public void add(Object o) {

        }
    }
}
