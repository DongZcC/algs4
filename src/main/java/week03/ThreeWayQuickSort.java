package week03;

import static week02.SortUtil.*;

/**
 * 功能说明: 三路循环 解决重复键问题<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-02-26<br>
 */
public class ThreeWayQuickSort {

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int lt = lo, gt = hi, i = lo;
        Comparable v = a[lo];
        while (i <= gt) {
            if (less(a[i], v)) {
                exch(a, i++, lt++);
            } else if (less(v, a[i])) {
                exch(a, gt--, i);
            } else
                i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{5, 8, 9, 3, 2, 10};
        ThreeWayQuickSort.sort(arr, 0, arr.length - 1);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }

}
