package Interview.week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * 功能说明: <br>
 * 荷兰国旗问题
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-02-07<br>
 */
public class DutchNationalFlag {
    private final static int SIZE = 10;
    private static Color[] buckets = new Color[SIZE];

    private enum Color {
        RED, WHITE, BLUE;
    }

    public static void swap(int i, int j) {
        Color c = buckets[i];
        buckets[i] = buckets[j];
        buckets[j] = c;
    }

    public static Color color(int index) {
        if (index > SIZE || index < 0)
            throw new IllegalArgumentException();
        return buckets[index];
    }

    public static void main(String[] args) {
        // 初始化一个 n个 桶的 混乱的数组。
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = Color.values()[StdRandom.uniform(3)];
        }
        System.out.println(Arrays.asList(buckets));

        sort(buckets);

        System.out.println(Arrays.asList(buckets));
    }

    // 通过最多n次调用 color ，n次调用swap 把整个数组顺序排好 ， 左边是红 、白 、蓝
    private static void sort(Color[] buckets) {
        int lo = 0; // 白球开始的位置
        int hi = buckets.length - 1; //白球结束的位置
        int current = 0;
        while (current <= hi) {
            switch (buckets[current]) {
                case RED:
                    // 这个红球出现的位置不对，需要交换
                    if (lo < current) {
                        swap(lo, current);
                        lo++;
                        current++;
                    } else {
                        lo++;
                        current++;
                    }
                    break;
                case BLUE:
                    swap(current, hi);
                    hi--;
                    break;
                case WHITE:
                    current++;
                    break;
            }
        }
    }
}
