package week02;

import static week02.SortUtil.*;

/**
 * 功能说明: <br>
 * 插入排序
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-02-06<br>
 */
public class InsertSort {
    public static void sort(Comparable[] array) {
        int length = array.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(array[j], array[j - 1]))
                    exch(array, j, j - 1);
            }
        }
    }


    public static void main(String[] args) {
        Integer[] arr = new Integer[]{5, 8, 9, 3, 2, 10};
        InsertSort.sort(arr);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }
}
