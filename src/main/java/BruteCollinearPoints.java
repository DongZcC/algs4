import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;


/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-02-23<br>
 */
public class BruteCollinearPoints {

    private LineSegment[] segments;
    private int count;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        validate(points);
        segments = new LineSegment[points.length];

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        double slope = points[i].slopeTo(points[j]);
                        if (Double.compare(slope, points[i].slopeTo(points[k])) == 0 && Double.compare(slope, points[i].slopeTo(points[l])) == 0) {
                            // at the same line
                            Point[] aux = new Point[]{points[i], points[j], points[k], points[l]};
                            Merge.sort(aux);
                            LineSegment lineSegment = new LineSegment(aux[0], aux[aux.length - 1]);
                            boolean existFlag = false;
                            for (LineSegment segment : segments) {
                                if (segment != null) {
                                    if (lineSegment.toString().equals(segment.toString())) {
                                        existFlag = true;
                                    }
                                }
                            }
                            if (!existFlag)
                                segments[count++] = lineSegment;
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return count;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] result = new LineSegment[count];
        System.arraycopy(segments, 0, result, 0, count);
        return result;
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
