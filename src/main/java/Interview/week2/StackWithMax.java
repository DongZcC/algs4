package Interview.week2;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * Description:
 * User: dzczyw
 * Date: 2018-01-31
 * Time: 21:15
 */
public class StackWithMax {

    private int max;
    private int N;
    private Node first;
    private boolean existFlag = true;

    private class Node {
        int item;
        Node next;

        public Node(int item) {
            this.item = item;
        }
    }

    public void push(int item) {
        if (item > max)
            max = item;
        Node oldFirst = first;
        first = new Node(item);
        first.next = oldFirst;
        N++;
    }

    public int pop() {
        int item = first.item;
        first = first.next;
        N--;
        if (item == max)
            existFlag = false;
        return item;
    }

    public int max() {
        if (!existFlag) {
            // 遍历一把, 找到最大值
            Node it = first;
            if (first == null)
                return -1;
            max = it.item;
            while (it.next != null) {
                if (it.item > max)
                    max = it.item;
                it = it.next;
            }
        }
        return max;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }


    public static void main(String[] args) {
        StackWithMax stack = new StackWithMax();
        stack.push(5);
        stack.push(6);
        stack.push(8);
        stack.push(2);
        stack.push(1);

        StdOut.println(stack.max());
        stack.pop();
        stack.pop();
        stack.pop();
        StdOut.println(stack.max());
        stack.pop();
        StdOut.println(stack.max());
        stack.pop();
        StdOut.println(stack.max());
    }
}
