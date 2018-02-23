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
public class FastCollinearPoints {

    private final ArrayList<LineSegment> lineSegments;
    private final int count;


    private class PointsRecord {

        public PointsRecord(int start, int end) {
            this.start = start;
            this.end = end;
        }

        private final int start;
        private final int end;
    }

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        // 校验入参是否合法
        validate(points);
        lineSegments = new ArrayList<>();
        // 开始比较逻辑
        // 最后三个点不需要比较, 至少四个点连成一条线
        for (int i = 0; i < points.length - 3; i++) {
            // i点作为原点
            Point origin = points[i];
            // 新建一个辅助数组
            Point[] temp = new Point[points.length - i - 1];
            int tpNum = 0;
            for (int j = i + 1; j < points.length; j++) {
                temp[tpNum++] = points[j];
            }
            // 根据原点的斜率排序
            Arrays.sort(temp, origin.slopeOrder());

            // 检查是否连续三个点的斜率相等
            ArrayList<PointsRecord> pointsRecords = new ArrayList<>();
            double slope = origin.slopeTo(temp[0]);
            int startPosition = 0;
            int currentPosition = 1;
            for (; currentPosition < temp.length; currentPosition++) {
                if (Double.compare(slope, origin.slopeTo(temp[currentPosition])) == 0) {
                    // nothing
                } else {
                    // 如果 大于等于三 说明至少有四点是共线了
                    if (currentPosition - startPosition >= 3) {
                        pointsRecords.add(new PointsRecord(startPosition, currentPosition - 1));
                    }
                    slope = origin.slopeTo(temp[currentPosition]);
                    startPosition = currentPosition;
                }
            }
            // 最后一个节点也可能 是共线的四个点中的最后一个
            if (currentPosition - startPosition >= 3) {
                pointsRecords.add(new PointsRecord(startPosition, currentPosition - 1));
            }

            // 根据开始位置，结束位置，创建LineSegment
            for (PointsRecord record : pointsRecords) {
                int startIndex = record.start;
                int endIndex = record.end;
                // 把原点位置加到数组中去
                Point[] linearPoints = new Point[endIndex - startIndex + 2];
                linearPoints[0] = origin;
                int l = 1;
                while (startIndex <= endIndex) {
                    linearPoints[l++] = temp[startIndex++];
                }
                Arrays.sort(linearPoints);
                LineSegment lineSegment = new LineSegment(linearPoints[0], linearPoints[l - 1]);
                boolean existFlag = false;
                for (LineSegment segment : lineSegments) {
                    if (lineSegment.toString().equals(segment.toString())) {
                        existFlag = true;
                    }
                }
                if (!existFlag)
                    lineSegments.add(lineSegment);
            }
        }
        count = lineSegments.size();
    }

    // the number of line segments
    public int numberOfSegments() {
        return count;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] result = new LineSegment[count];
        return lineSegments.toArray(result);
    }

    private void validate(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException();
            for (int j = i + 1; j < points.length; j++) {
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
