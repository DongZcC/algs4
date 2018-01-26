package ch01;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-01-26<br>
 */
public class Queue<T> {

    private int N; // count

    private class Node {
        T item;
        Node next;
    }

    private Node first;
    private Node last;

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void enqueue(T t) {
        //first time

        Node oldLast = last;
        last = new Node();
        last.item = t;
        if (N == 0)
            first = last;
        else
            oldLast.next = last;
        N++;
    }

    public T dequeue() {
        Node item = first;
        first = first.next;
        N--;
        //queue is empty
        if (N == 0) {
            last = first;
        }
        return item.item;
    }

    public static void main(String[] args) {
        Queue<String> q = new Queue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                q.enqueue(item);
            else if (!q.isEmpty())
                StdOut.print(q.dequeue() + " ");
        }
        StdOut.println("(" + q.size() + ")left on queue");
    }
}
