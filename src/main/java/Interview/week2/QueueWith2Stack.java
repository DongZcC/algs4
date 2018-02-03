package Interview.week2;

import ch01.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Description:
 * User: dzczyw
 * Date: 2018-01-31
 * Time: 20:55
 */
public class QueueWith2Stack<T> {

    Stack<T> s1 = new Stack<T>(), s2 = new Stack<T>();

    public void enqueue(T item) {
        // 入队简单的保存在栈中
        s1.push(item);
    }

    public T dequeue() {
        // 翻转栈
        while (s2.isEmpty()) {
            s2.push(s1.pop());
        }
        return s2.pop();
    }

    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    public int size() {
        return s1.size() + s2.size();
    }

    public static void main(String[] args) {
        QueueWith2Stack<String> q = new QueueWith2Stack<String>();
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
