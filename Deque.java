/* *****************************************************************************
 *  Name:    Lucas Irwin
 *
 *  Description: Creates a Deque data type (using a doubly-linked list) which is
 *               a generalization of a stack and a queue. The deque supports
 *               adding and removing items from both the front and the back of
 *               the data structure.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    // instance variable for reference to first node in the deque
    private Node first;
    // instance variable for reference to last node in the deque
    private Node last;
    // instance variable for the number of nodes in the deque
    private int n;

    private class Node {
        // instance variable for data held by node
        private Item item;
        // instance variable for reference to next node
        private Node next;
        // instance variable for reference to previous node
        private Node prev;
    }

    // construct an empty deque
    public Deque() {
        this.n = 0;
        this.first = null;
        this.last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("null argument");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (isEmpty()) {
            last = first;
        }
        else oldFirst.prev = first;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("null argument");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        if (isEmpty()) {
            first = last;
        }
        else oldLast.next = last;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (n == 0) throw new NoSuchElementException("the deck is empty");
        Item current = first.item;
        first = first.next;
        n--;
        if (!isEmpty()) first.prev = null;
        return current;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (n == 0) throw new NoSuchElementException("the deck is empty");
        Item current = last.item;
        last = last.prev;
        n--;
        if (!isEmpty()) last.next = null;
        return current;
    }

    // return an iterator over the items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        // instance variable for the current node which is being returned
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("no more items");
            }
            if (n != 0) {
                Item item = current.item;
                current = current.next;
                return item;
            }
            else return null;

        }
    }


    // main method for unit testing
    public static void main(String[] args) {
        Deque<Integer> deck = new Deque<Integer>();
        for (int i = 0; i < 10; i++) {
            deck.addLast(i);
        }
        for (int i = 0; i < 10; i++) {
            StdOut.println(deck.removeLast());
        }
        StdOut.println();
        StdOut.println("Test for empty deck: \n Is the deque empty? " +
                               deck.isEmpty());
        for (int i = 0; i < 10; i++) {
            deck.addFirst(i);
        }
        for (int i = 0; i < 5; i++) {
            StdOut.println(deck.removeFirst());
            StdOut.println(deck.removeLast());
        }
        StdOut.println();

        for (int i = 0; i < 10; i += 2) {
            deck.addFirst(i);
            deck.addLast(i + 1);
        }
        for (int a : deck) {
            for (int b : deck) {
                StdOut.println(a + "-" + b + " ");
            }
        }

        StdOut.println();

        Iterator<Integer> iterator = deck.iterator();

        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }

        StdOut.println();
        StdOut.println("size = " + deck.size());
        StdOut.println();

        StdOut.println("Is the deque empty?  " + deck.isEmpty());

        Deque<Integer> deck2 = new Deque<Integer>();

        Stopwatch stop = new Stopwatch();
        for (int i = 0; i < 100000; i += 2) {
            deck2.addLast(i);
            deck2.addFirst(i + 1);
        }
        for (int i = 0; i < 50000; i++) {
            deck2.removeFirst();
            deck2.removeLast();
        }
        StdOut.println("elapsed time for n = 100000  = " + stop.elapsedTime());

        stop = new Stopwatch();
        for (int i = 0; i < 200000; i += 2) {
            deck2.addLast(i);
            deck2.addFirst(i + 1);
        }
        for (int i = 0; i < 100000; i++) {
            deck2.removeFirst();
            deck2.removeLast();
        }
        StdOut.println("elapsed time for n = 200000  = " + stop.elapsedTime());


    }
}
