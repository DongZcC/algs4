package Interview.week3;

import static ch02.SortUtil.less;

/**
 * 功能说明: 计数反演<br>
 * 给定 数组a, 如果 i < j  但是 a[i] > a[j]</>
 * 要求： linearithmic   nlogn
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-02-23<br>
 */
public class CountingInversions {

    private static int count = 0;

    public static int countInversions(Comparable[] a) {
        int count = 0;
        // 这个解法 n2 复杂度
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (i < j && a[i].compareTo(a[j]) > 0) {
                    count++;
                }
            }
        }

        // 在归并排序的时候 计算
        //拷贝数组
        Comparable[] aux = a.clone();
        Comparable[] aux2 = new Comparable[a.length];

        sort(aux, aux2, 0, aux.length - 1);
        return count;
    }

    private static void sort(Comparable[] aux, Comparable[] aux2, int lo, int hi) {
        if (lo >= hi)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(aux, aux2, lo, mid);
        sort(aux, aux2, mid + 1, hi);
        merge(aux, aux2, lo, mid, hi);
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
            else {
                a[k] = aux[j++];
                count++;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{0, 8, 7, 6};
        countInversions(a);
        System.out.println(count);
    }
}

