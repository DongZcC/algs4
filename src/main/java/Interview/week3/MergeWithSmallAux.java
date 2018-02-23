package Interview.week3;

import static ch02.SortUtil.*;

/**
 * 功能说明: 合并两个数组 用一个小的辅助数组<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-02-23<br>
 */
public class MergeWithSmallAux {

    private static final int SIZE = 2;

    private static Comparable[] aux;

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        // lo 到 mid  已经有序, mid + 1 到hi  已经有序
        // hint , 只拷贝左半部分
        System.arraycopy(a, lo, aux, lo, SIZE);
        int i = lo;
        int j = mid + 1;
        // 如果i, 比j 小
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = a[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[i], a[j])) {
                a[k] = aux[i++];
            } else {
                a[k] = a[j++];
            }
        }

    }

    public static void main(String[] args) {
        // 初始化辅助数组
        aux = new Comparable[SIZE];
        Comparable[] a = new Comparable[]{1, 5, 2, 3};
        int lo = 0;
        int mid = SIZE - 1;
        int hi = 2 * SIZE - 1;
        merge(a, lo, mid, hi);

        for (Comparable c : a) {
            System.out.println(c);
        }
    }
}
