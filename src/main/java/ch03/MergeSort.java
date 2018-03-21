package ch03;

import static ch02.SortUtil.*;

/**
 * Description:
 * User: dzczyw
 * Date: 2018-02-07
 * Time: 21:29
 */
public class MergeSort {
    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[i], aux[j]))
                a[k] = aux[i++];
            else
                a[k] = aux[j++];
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{5, 8, 9, 3, 2, 10};
        Integer[] aux = new Integer[arr.length];
        MergeSort.sort(arr, aux, 0, arr.length - 1);
        String s = "1";
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }
}
