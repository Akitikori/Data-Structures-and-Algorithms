/* *****************************************************************************
 *  Name:    Akitikori
 *  NetID:   rakitikori
 *  Precept: P04
 *
 *  Description:  Please write me
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // reference to the first item in list
    private Node first;
    // reference to the last item in the list
    private Node last;
    // store the number of items in the list
    // might not need this
    private int n;

    // create a private node class to start linked list
    private class Node {
        // generic item type parameter and reference
        private Item item;
        // reference to the next node in the list
        private Node next;
        // reference to the previous node in the list
        private Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = last;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (first == null);
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        // add a new node in front and be careful to link existing nodes to
        // new ones carefully
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.prev = oldFirst;
        if (n > 0) {
            oldFirst.next = first;
        }
        // increment the number of items in the list
        n += 1;
        if (n == 1) {
            last = first;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        throw new IllegalArgumentException();
        // add a new node behind and be careful to link existing nodes to
        // new ones carefully
        Node oldLast = last;
        Node last = new Node();
        last.item = item;
        last.next = oldLast;
        oldLast.prev = last;
        // increment the number of items in the list by 1
        n += 1;
        if (n == 1) {
            first = last;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (n == 0)
            throw new NoSuchElementException();
        // save the item that is referenced by first
        Item item = first.item;
        if (n == 1) {
            // set first and last to null
            // loitering is still a problem right now
            // will solve later because I'm too excited coding this
            first = null;
            last = first;
        }
        else {
            first = first.prev;
            first.next = null;
        }
        // decrement size in list
        n -= 1;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (n == 0)
            throw new NoSuchElementException();
        // save the item that is referenced by last
        Item item = last.item;
        if (n == 1) {
            // set first and last to null
            // loitering is still a problem right now
            // will solve later because I'm too excited coding this
            last = null;
            first = last;
        }
        else {
            last = last.next;
            last.prev = null;
        }
        // decrement size in list
        n -= 1;
        return item;

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new iteratorObject();
    }

    // create private iterator class to use for next() and hasNext() methods
    private class iteratorObject implements Iterator<Item> {
        // indicating what we start iterating from
        private Node current = first;

        public boolean hasNext() {
            return (current != null);
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.prev;
            return item;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque1 = new Deque<>();
        deque1.addFirst("My");
        deque1.addFirst("Name");
        deque1.addFirst("Is");
        deque1.addFirst("Ropo");
        StdOut.println(deque1.removeFirst());
        StdOut.println(deque1.removeFirst());
        StdOut.println(deque1.removeLast());
        StdOut.println(deque1.removeFirst());
        deque1.addFirst("My");
        deque1.addFirst("Name");
        deque1.addFirst("Is");
        deque1.addFirst("Ropo");
        for (String s : deque1) {
            StdOut.print(s + " ");
        }
    }
}



