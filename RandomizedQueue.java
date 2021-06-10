/* *****************************************************************************
 *  Name:    Akitikori
 *  NetID:   rakitikori
 *  Precept: P04
 *
 *  Description:  Please write me
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // array of items to be created
    private Item[] items;
    // number of items in list
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        // initialize the array with items
        // and perform ugly cast for generic array
        items = (Item[]) new Object[1];
        // initialize number of items to 0
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (n == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        items[n++] = item;
        if (n == items.length) {
            resize(items.length * 2);
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        int index = StdRandom.uniform(size());
        Item item = items[index];
        items[index] = items[--n];
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return items[StdRandom.uniform(items.length)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new randomizedQueueIterator();
    }

    // create iterator object with hasNext() and next() methods
    private class randomizedQueueIterator implements Iterator<Item> {
        // copy of array
        Item[] copy;
        // integer to keep track of where we are in iteration
        private int i = size();

        public randomizedQueueIterator() {
            copy = (Item[]) new Object[size()];
            for (int i = 0; i < size(); i++) {
                copy[i] = items[i];
            }
            // implement Knuth Shuffle
            StdRandom.shuffle(copy);
        }

        public boolean hasNext() {
            return (i > 0);
        }

        public Item next() {
            return copy[--i];
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randomQueue = new RandomizedQueue<>();
        randomQueue.enqueue("My");
        randomQueue.enqueue("Name");
        randomQueue.enqueue("Is");
        randomQueue.enqueue("Ropo");
        for (String s : randomQueue) {
            StdOut.println(s);
        }
        // StdOut.println(randomQueue.dequeue());

    }

}
