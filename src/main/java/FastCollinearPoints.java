import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-02-23<br>
 */
public class FastCollinearPoints {

    private final ArrayList<LineSegment> lineSegments;


    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        // 校验入参是否合法
        validate(points);
        lineSegments = new ArrayList<>();

        // 拷贝数组 , 对拷贝数组进行排序
        // 这个copy数组 是按照 点的y轴大小进行排序的
        Point[] copy = new Point[points.length];
        Point[] temp = new Point[points.length];
        System.arraycopy(points, 0, copy, 0, points.length);
        System.arraycopy(points, 0, temp, 0, points.length);
        Arrays.sort(copy);

        // 开始比较逻辑
        // 至少四点连成一条直线
        for (int i = 0; i < copy.length - 3; i++) {
            Point origin = copy[i];
            // 用原点的比较器，进行斜率排序
            Arrays.sort(temp, origin.slopeOrder());
            int count = 1;
            ArrayList<Point> pointList = new ArrayList<>();
            double slope0 = origin.slopeTo(temp[0]);
            // 先把原点加入
            pointList.add(temp[0]);
            for (int j = 1; j < temp.length; j++) {
                // 另一点斜率
                double slope1 = origin.slopeTo(temp[j]);
                // 连续斜率相等
                if (Double.compare(slope0, slope1) == 0) {
                    // 加入到List中去
                    pointList.add(temp[j]);
                    count++;
                    // 循环的最后一步
                    if (count > 2 && j == temp.length - 1) {
                        // 把原点加入进来
                        pointList.add(copy[i]);
                        // 排序
                        Collections.sort(pointList);
                        // 观察集合中的最小值， 是不是比当前的原点小 , 小的话，证明已经加入到集合中去了
                        if (pointList.get(0).compareTo(copy[i]) < 0) {

                        } else {
                            // 如果不比当前原点小， 说明当前为一个 线段的开始 , 加入到集合中去
                            lineSegments.add(new LineSegment(pointList.get(0), pointList.get(pointList.size() - 1)));
                        }
                        // 无论有没有存在一个新的值， 都要重新设置count变量
                        count = 1;
                    }
                } else {
                    if (count > 2) {
                        // 把原点加入进来
                        pointList.add(copy[i]);
                        // 排序
                        Collections.sort(pointList);
                        // 观察集合中的最小值， 是不是比当前的原点小 , 小的话，证明已经加入到集合中去了
                        if (pointList.get(0).compareTo(copy[i]) < 0) {

                        } else {
                            // 如果不比当前原点小， 说明当前为一个 线段的开始 , 加入到集合中去
                            lineSegments.add(new LineSegment(pointList.get(0), pointList.get(pointList.size() - 1)));
                        }
                    }
                    count = 1;
                    pointList.clear();
                    // 把当前点 加入到集合中
                    pointList.add(temp[j]);
                }
                // 斜率要变化
                slope0 = slope1;
            }
        }

        // // 开始比较逻辑
        // // 最后三个点不需要比较, 至少四个点连成一条线
        // for (int i = 0; i < copy.length - 3; i++) {
        //     // i点作为原点
        //     Point origin = copy[i];
        //     // 根据原点的斜率排序
        //     // 由于归并排序是 保留原本排序顺序的，因此这样排序 也会保留原本的排序
        //     Arrays.sort(copy, origin.slopeOrder());
        //     // 检查是否连续三个点的斜率相等
        //     ArrayList<PointsRecord> pointsRecords = new ArrayList<>();
        //     double slope = origin.slopeTo(copy[1]);
        //     int startPosition = 0;
        //     // 先前取走了两个点
        //     int temp = 0;
        //     int currentPosition = 2;
        //     for (; currentPosition < copy.length; currentPosition++) {
        //         if (Double.compare(slope, origin.slopeTo(copy[currentPosition])) == 0) {
        //             // nothing
        //         } else {
        //             // 如果 大于等于三 说明至少有四点是共线了
        //             if (currentPosition - startPosition >= 3) {
        //                 pointsRecords.add(new PointsRecord(startPosition, currentPosition - 1));
        //             }
        //             slope = origin.slopeTo(copy[currentPosition]);
        //             startPosition = currentPosition;
        //         }
        //     }
        //     // 最后一个节点也可能 是共线的四个点中的最后一个
        //     if (currentPosition - startPosition >= 3) {
        //         pointsRecords.add(new PointsRecord(startPosition, currentPosition - 1));
        //     }
        //
        //     // 根据开始位置，结束位置，创建LineSegment
        //     for (PointsRecord record : pointsRecords) {
        //         int startIndex = record.start;
        //         int endIndex = record.end;
        //         // 把原点位置加到数组中去
        //         Point[] linearPoints = new Point[endIndex - startIndex + 2];
        //         linearPoints[0] = origin;
        //         int l = 1;
        //         while (startIndex <= endIndex) {
        //             linearPoints[l++] = copy[startIndex++];
        //         }
        //         Arrays.sort(linearPoints);
        //         LineSegment lineSegment = new LineSegment(linearPoints[0], linearPoints[l - 1]);
        //         boolean existFlag = false;
        //         for (LineSegment segment : lineSegments) {
        //             if (lineSegment.toString().equals(segment.toString())) {
        //                 existFlag = true;
        //             }
        //         }
        //         if (!existFlag)
        //             lineSegments.add(lineSegment);
        //     }
        // }
        // count = lineSegments.size();


    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] result = new LineSegment[lineSegments.size()];
        return lineSegments.toArray(result);
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

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
