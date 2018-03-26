package week01;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-01-26<br>
 */
public class FixedCapacityStack<T> {
    private Object[] a;
    private int N; // size

    public FixedCapacityStack(int cap) {
        a = new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(T item) {
        a[N++] = item;
    }

    public T pop() {
        return (T) a[N--];
    }
}
