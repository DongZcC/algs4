package week02;

import static week02.SortUtil.*;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-02-06<br>
 */
public class ShellSort {

    public static void sort(Comparable[] array) {
        // 选择一个步长
        int length = array.length;
        int h = 1;
        while (h < length / 3)
            h = 3 * h + 1;

        while (h >= 1) {
            for (int i = h; i < length; i += h) {
                for (int j = i; j > 0; j -= h) {
                    if (less(array[j], array[j - h]))
                        exch(array, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{5, 8, 9, 3, 2, 10};
        ShellSort.sort(arr);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }
}
