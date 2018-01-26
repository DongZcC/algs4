package ch01;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-01-26<br>
 */
public class FixedCapacityStackOfStrings {
    private String[] a;
    private int N; // size

    public FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        a[N++] = item;
    }

    public String pop() {
        return a[N--];
    }
}
