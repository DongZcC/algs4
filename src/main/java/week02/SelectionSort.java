package week02;

import static week02.SortUtil.*;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-02-06<br>
 */

// 选择排序
public class SelectionSort {
    public static void sort(Comparable[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (less(array[j], array[min]))
                    min = j;
            }
            exch(array, i, min);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{5, 8, 9, 3, 2, 10};
        SelectionSort.sort(arr);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }
}
