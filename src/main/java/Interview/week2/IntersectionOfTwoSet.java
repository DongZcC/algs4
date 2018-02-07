package Interview.week2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;

/**
 * 功能说明: <br>
 * 给定两个数组，求交集的个数
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-02-07<br>
 */
public class IntersectionOfTwoSet {
    private final static int COUNT = 10;

    public static int intersection(Point[] a, Point[] b) {
        int length = a.length + b.length;
        HashSet<Point> count = new HashSet<Point>();
        count.addAll(Arrays.asList(a));
        count.addAll(Arrays.asList(b));
        return length - count.size();
    }

    public static void main(String[] args) {
        Point[] a = new Point[COUNT];
        Point[] b = new Point[COUNT];
        for (int i = 0; i < 10; i++) {
            a[i] = new Point(StdRandom.uniform(11), StdRandom.uniform(11));
            b[i] = new Point(StdRandom.uniform(11), StdRandom.uniform(11));
        }

        System.out.println(Arrays.asList(a));
        System.out.println(Arrays.asList(b));
        System.out.println("The same point count = " + intersection(a, b));

    }
}
