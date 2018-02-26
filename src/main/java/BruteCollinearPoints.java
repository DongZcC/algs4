import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-02-23<br>
 */
public class BruteCollinearPoints {

    private final ArrayList<LineSegment> segments = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        validate(points);
        // for (int i = 0; i < points.length; i++) {
        //     for (int j = i + 1; j < points.length; j++) {
        //         for (int k = j + 1; k < points.length; k++) {
        //             for (int l = k + 1; l < points.length; l++) {
        //                 double slope = points[i].slopeTo(points[j]);
        //                 if (Double.compare(slope, points[i].slopeTo(points[k])) == 0 && Double.compare(slope, points[i].slopeTo(points[l])) == 0) {
        //                     // at the same line
        //                     Point[] aux = new Point[]{points[i], points[j], points[k], points[l]};
        //                     Merge.sort(aux);
        //                     LineSegment lineSegment = new LineSegment(aux[0], aux[aux.length - 1]);
        //                     boolean existFlag = false;
        //                     for (LineSegment segment : segments) {
        //                         if (segment != null) {
        //                             if (lineSegment.toString().equals(segment.toString())) {
        //                                 existFlag = true;
        //                             }
        //                         }
        //                     }
        //                     if (!existFlag)
        //                         segments[count++] = lineSegment;
        //                 }
        //             }
        //         }
        //     }
        // }

        // 先拷贝数组
        Point[] copy = new Point[points.length];
        System.arraycopy(points, 0, copy, 0, points.length);

        // 先把数组排序
        // 数组先排序之后，这样 ，共线的点一定是
        Arrays.sort(copy);

        for (int i = 0; i < copy.length - 3; i++) {
            for (int j = i + 1; j < copy.length - 2; j++) {
                double slope1 = copy[i].slopeTo(copy[j]);
                for (int k = j + 1; k < copy.length - 1; k++) {
                    double slope2 = copy[i].slopeTo(copy[k]);
                    // 不相等就是不共线
                    if (Double.compare(slope1, slope2) != 0)
                        continue;
                    int temp = 0;
                    for (int l = k + 1; l < copy.length; l++) {
                        double slope3 = copy[i].slopeTo(copy[l]);
                        // 四点共线, 先记录当前位置的下标
                        if (Double.compare(slope1, slope3) == 0) {
                            temp = l;
                        }
                        // 当循环到最后一次后， temp中的值一定是， 共线线段中 最大的值
                        if (l == copy.length - 1 && temp != 0) {
                            segments.add(new LineSegment(copy[i], copy[temp]));
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] result = new LineSegment[segments.size()];
        return segments.toArray(result);
    }

    private void validate(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();
            for (int j = i + 1; j < points.length; j++) {
                if (points[j] == null)
                    throw new IllegalArgumentException();
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();
            }
        }
    }


    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        // 32466 16322
        // null
        // 18649 10862
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
