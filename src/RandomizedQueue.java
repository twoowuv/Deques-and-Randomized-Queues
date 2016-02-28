import java.lang.NullPointerException;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

/**
 *
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int size;

    /**
     * Constructs an empty randomized queue
     */
    public RandomizedQueue() {
        // TODO: should this be higher than one?
        queue = (Item[]) new Object[1];
    }

    /**
     * Returns true if the queue is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the queue
     */
    public int size() {
        return size;
    }

    /**
     * Add an item to the queue
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot add null item to queue");
        }

        queue[size] = item;
        size++;

        // If the array has no more space then double its size
        if (size == queue.length) {
            resize(size * 2);
        }
    }

    /**
     * Remove and return a random item
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("There are no items in the queue.");
        }

        int idx = StdRandom.uniform(size);

        // Gets the item we've randomly selected to remove
        Item dequeuedItem = queue[idx];

        // Sets the item at the index we selected and sets its value to the value
        // at the end of values in the array
        queue[idx] = queue[size];

        // Then null the item at the end because it has been moved to where we
        // removed the item. The order of values does not matter.
        queue[size] = null;

        size--;

        return dequeuedItem;
    }

    /**
     * Return (but do not remove) a random item
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("There are no items in the queue.");
        }

        int idx = StdRandom.uniform(size);

        return queue[idx];
    }

    private void resize(int newSize) {
        Item[] resizedQueue = (Item[]) new Object[newSize];

        // Copy values from the old array to the new one
        for (int i = 0; i < size; i++) {
            resizedQueue[i] = queue[i];
        }

        queue = resizedQueue;
    }

    /**
     * Iterates over the array in reverse order from the item at
     * index size to the beginning
     */
    private class QueueIterator implements Iterator<Item> {

        private int iteratorIdx = 0;

        @Override
        public boolean hasNext() {
            return iteratorIdx < size;
        }

        @Override
        public Item next() {
            return queue[iteratorIdx++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove method not supported.");
        }
    }

    /**
     * Return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    public static void main(String[] args) {

    }
}

