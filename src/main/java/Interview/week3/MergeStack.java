package Interview.week3;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-01-26<br>
 */
public class MergeStack<T extends Comparable> implements Iterable {

    private int N; // count
    Node first; // the head item


    public void push(T item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst; // at the beginning, is null
        N++;
    }

    public T pop() {
        Node item = first;
        first = first.next;
        N--;
        return item.item;
    }

    // 支持归并排序
    public void mergeSort() {
        first = sort(first);
    }

    private Node sort(Node head) {
        if (head == null || head.next == null) return head;
        Node slow = head;
        Node fast = head;
        // 取中间节点
        // 搞两个节点一起往下跑，跑的慢的得下一个，就是 右侧的头结点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node left = head;
        Node right = slow.next;
        slow.next = null; //将左右链表分开
        left = sort(left);
        right = sort(right);
        return merge(left, right);
    }

    private Node merge(Node left, Node right) {
        Node aux = new Node(); //需要耗费logn的额外空间
        Node l = left;
        Node r = right;
        Node current = aux;
        while (l != null && r != null) {
            // 这段是排序算法
            // if (less(r.item, l.item)) {
            //     current.next = r;
            //     current = current.next;
            //     r = r.next;
            // } else {
            //     current.next = l;
            //     current = current.next;
            //     l = l.next;
            // }

            // 打乱顺序，则可以保证混乱
            int rand = StdRandom.uniform(2);
            if (rand == 0) {
                current.next = r;
                current = current.next;
                r = r.next;
            } else {
                current.next = l;
                current = current.next;
                l = l.next;
            }
        }
        if (l != null) current.next = l; // 如果左侧没遍历完，将其连接至current后
        else if (r != null) current.next = r; //如果右侧没遍历完，将其连接至current后
        return aux.next; //返回归并好的链表
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    @Override
    public Iterator iterator() {
        return new ListIterator();
    }

    private class Node {
        T item;
        Node next;
    }

    private class ListIterator implements Iterator<T> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T t = current.item;
            current = current.next;
            return t;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public String toString() {
        Iterator<T> iter = this.iterator();
        String ret = iter.next().toString();
        while (iter.hasNext()) {
            ret += ", " + iter.next().toString();
        }
        return ret;
    }

    public static void main(String[] args) {
        MergeStack<Integer> sll = new MergeStack<Integer>();
        sll.push(1);
        sll.push(2);
        sll.push(11);
        sll.push(9);
        sll.push(10);
        sll.push(4);
        sll.push(7);
        System.out.println(sll);
        sll.mergeSort();
        System.out.println(sll);
    }
}

