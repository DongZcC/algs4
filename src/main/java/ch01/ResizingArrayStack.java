package ch01;

import java.util.Iterator;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-01-26<br>
 */
public class ResizingArrayStack<T> implements Iterable<T> {
    private int N;
    private int tail;
    private int head;
    private Object[] a = new Object[1];

    private void resize(int max) {
        Object[] temp = new Object[max];
        System.arraycopy(a, 0, temp, 0, a.length);
        a = temp;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(T t) {
        if (N == a.length)
            resize(2 * a.length);
        a[N++] = t;
    }

    public T pop() {
        Object t = a[--N];
        a[N] = null;
        if (N > 0 && N < a.length / 4)
            resize(a.length / 2);
        return (T) t;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int i = N;

            public boolean hasNext() {
                return i > 0;
            }

            public T next() {
                return (T) a[--N];
            }

            public void remove() {
                throw new UnsupportedOperationException("Not support");
            }
        };
    }

}
