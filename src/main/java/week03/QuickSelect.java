package week03;

import edu.princeton.cs.algs4.StdRandom;

import static week02.SortUtil.*;

/**
 * 功能说明: 快速选择<br>
 * 给定一个集合， 从中选择一个 kth (第几大的)
 * EX.  MIN(k = 0) , MEDIUM(k = length / 2) , MAX(k=length -1)
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-02-26<br>
 */
public class QuickSelect {

    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0;
        int hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (k < j)
                hi = j - 1;
            else if (k > j)
                lo = j + 1;
            else
                return a[k];
        }
        return a[k];
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (less(a[i++], a[lo]))
                if (i == hi) break;
            while (less(a[lo], a[--j]))
                if (j == lo) break;
            exch(a, i, j);
            if (i >= j) // cross
                break;
        }
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{5, 8, 9, 3, 2, 10};
        System.out.println(QuickSelect.select(arr, 1));
    }
}
