package Interview.week2;

import ch02.ShellSort;

/**
 * 功能说明: <br>
 * 判断两个数组是否只是顺序不同，项相同
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-02-07<br>
 */
public class Permutation {

    private final static int SIZE = 10;

    public static boolean isTheSame(Comparable[] a, Comparable[] b) {
        ShellSort.sort(a);
        ShellSort.sort(b);
        if (a.length != b.length)
            return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i].compareTo(b[i]) != 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{
                5, 8, 9, 3, 6, 7, 1, 4, 2, 10
        };
        Integer[] b = new Integer[]{
                7, 4, 1, 2, 5, 9, 6, 3, 0, 8,
        };

        System.out.println(isTheSame(a, b));
    }
}
