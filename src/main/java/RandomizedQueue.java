import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Description:
 * User: dzczyw
 * Date: 2018-02-03
 * Time: 11:59
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    // 随机的队列，因此不需要保存头尾指针
    private int size;
    private Object[] items = new Object[1];

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (size == items.length)
            resize(2 * items.length);
        items[size++] = item;
    }

    private void resize(int length) {
        Object[] tmp = new Object[length];
        System.arraycopy(items, 0, tmp, 0, size);
        items = tmp;
    }


    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        // 要求出队一个随机的值
        // 找到一个随机的下标
        int index = StdRandom.uniform(0, size);
        // 把找到的下标值，和数组的最后一个值交换
        Object item = items[index];
        items[index] = items[size - 1];
        items[size - 1] = null;
        size--;
        if (size != 0 && size == items.length / 4)
            resize(items.length / 2);
        return (Item) item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        return (Item) items[StdRandom.uniform(0, size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {

        private int index = size;
        private final Object[] randomItems;

        public RandomizedIterator() {
            randomItems = new Object[index];
            System.arraycopy(items, 0, randomItems, 0, index);
            StdRandom.shuffle(randomItems);
        }

        public boolean hasNext() {
            return index != 0;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("Que is empty");
            Item item = (Item) randomItems[index - 1];
            index--;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.dequeue();
    }
}
