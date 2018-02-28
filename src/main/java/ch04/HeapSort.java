package ch04;

import java.util.Arrays;

/**
 * 功能说明: 堆排序<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-02-28<br>
 */
public class HeapSort {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }
        // 交换最大位置
        while (N > 1) {
            exch(a, 1, N);
            sink(a, 1, --N);
        }
    }

    /**
     * 由于 堆排序 理解上是 从下标 1 开始使用数组，因此在交换时，都应该用当前下标 - 1 去操作
     *
     * @param a
     * @param i
     * @param j
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = tmp;
    }

    /**
     * 下沉操作
     * 得到一个堆
     *
     * @param a
     * @param k
     * @param N
     */
    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a, j, j + 1)) j++;
            if (!less(a, k, j)) break;
            exch(a, k, j);
            k = j;
        }
    }

    /**
     * 由于 堆排序 理解上是 从下标 1 开始使用数组，因此在比较时，都应该用当前下标 - 1 去操作
     *
     * @param a
     * @param i
     * @param j
     * @return
     */
    private static boolean less(Comparable[] a, int i, int j) {
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{5, 8, 9, 3, 2, 10, 1};
        HeapSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
