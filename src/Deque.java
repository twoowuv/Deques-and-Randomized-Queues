import java.lang.NullPointerException;
import java.lang.UnsupportedOperationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double ended queue or deque data type. Items can be added and removed from either
 * the front or back of the deque.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    /**
     * Constructs an empty deque
     */
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * This inner class is what each item in the linked list is
     *
     * @param Node.item - The value stored in the node
     * @param Node.next - The next item in the linked list
     * @param Node.prev - The previous item in the linked list
     */
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    /**
     * Returns true if the deque is empty
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Return the number of items on the deque
     */
    public int size() {
        return size;
    }

    /**
     * Add an item to the front of the deque
     *
     * @param item = The item to add to the start
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot add a null item to this deque.");
        }

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;

        size++;

        if (size == 1) {
            // If there is only one item in the deque then the last node is the first node
            last = first;
        } else {
            // If the size is greater than one set the oldFirst.prev to the new first
            // Note: We don't have to worry about size of 0 because it will throw before that
            oldFirst.prev = first;
        }

    }

    /**
     *  Add an item to the end of the deque
     *
     *  @param item - The item to add to the end
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot add a null item to this deque.");
        }

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        last.next = null;

        size++;

        if (size == 1) {
            // If there is only one item in the deque then the last node is the first node
            first = last;
        } else {
            // Set the old last items .next to be the added item
            oldLast.next = last;
        }
    }

    /**
     * Remove and return the item from the front
     */
    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException("Cannot remove first item because the deque is empty.");
        }

        Item oldFirst = first.item;
        first = first.next;

        size--;

        // If first isn't null then we've still got items in the list
        if (size == 0) {
            last = null;
        } else {
            first.prev = null;
        }

        return oldFirst;
    }

    /**
     * Remove and return the item from the end
     */
    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException("Cannot remove last item because the deque is empty.");
        }

        Item oldLast = last.item;
        last = last.prev;

        size--;

        // If last isn't null then we've still got items in the list
        if (size == 0) {
            first = null;
        } else {
            last.next = null;
        }

        return oldLast;
    }

    /**
     * Return an iterator over items in order from front to end
     */
    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        };

        public void remove() {
            throw new UnsupportedOperationException("Remove method not supported.");
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There are no items left to iterate over.");
            }

            Item item = current.item;
            current = current.next;
            return item;
        };
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing
    public static void main(String[] args) {

    }

}
