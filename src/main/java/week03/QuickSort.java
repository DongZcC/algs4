package week03;

import edu.princeton.cs.algs4.StdRandom;

import static week02.SortUtil.*;

/**
 * 功能说明: 快速排序 <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-02-26<br>
 */
public class QuickSort {
    // shuffle the array
    // Partition so that, from j.

    // sort each piece recursively

    public static void sort(Comparable[] a) {
        //
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        while (lo >= hi)
            return;
        // 找到一个切分的位置
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (less(a[i++], a[lo]))
                if (i == hi) break;

            while (less(a[lo], a[--j]))
                if (j == lo) break;

            // check if point cross
            if (i >= j)
                break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{5, 8, 9, 3, 2, 10};
        QuickSort.sort(arr, 0, arr.length - 1);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }
}
