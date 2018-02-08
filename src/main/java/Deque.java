import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Description:
 * User: dzczyw
 * Date: 2018-02-03
 * Time: 10:17
 */
public class Deque<Item> implements Iterable<Item> {

    private int count;
    private Node first;
    private Node last;

    private class Node {
        Item item; //
        Node next; // 8bit
        Node previous;
        public Node(Item item) {
            this.item = item;
        }
    }

    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("The param is illegal");
        Node oldFirst = first;
        first = new Node(item);
        first.next = oldFirst;
        // if the que is empty last also point to the node
        if (count == 0)
            last = first;
        else
            oldFirst.previous = first;
        count++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("The param is illegal");
        Node oldLast = last;
        last = new Node(item);
        if (count == 0)
            first = last;
        else {
            oldLast.next = last;
            last.previous = oldLast;
        }
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("The queue is empty");
        Item item = first.item;
        first = first.next;
        if (first != null)
            first.previous = null;
        count--;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("The queue is empty");
        Item item = last.item;
        last = last.previous;
        if (last != null)
            last.next = null;
        else
            first = last;
        count--;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node it = first;

            public boolean hasNext() {
                return it != null && it.item != null;
            }

            public Item next() {
                if (!hasNext())
                    throw new NoSuchElementException("Que is empty");
                Item item = it.item;
                it = it.next;
                return item;
            }

            public void remove() {
                throw new UnsupportedOperationException("method not support");
            }
        };
    }

    // unit testing (optional)
    public static void main(String[] args) {
        Deque<String> stringDeque = new Deque<String>();
        stringDeque.addFirst("test");
        stringDeque.addFirst("test2");
        stringDeque.addFirst("test3");
        stringDeque.removeLast();
        stringDeque.removeLast();
        stringDeque.removeLast();

        for (String item : stringDeque) {
            StdOut.println(item);
        }
    }
}
