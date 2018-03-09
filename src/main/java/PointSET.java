import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * 功能说明: 暴力法 K-D Tree<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-08<br>
 */
public class PointSET {

    private final TreeSet<Point2D> points;

    // construct an empty set of points
    public PointSET() {
        points = new TreeSet<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return points.isEmpty();
    }

    // number of points in the set
    public int size() {
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        if (!contains(p))
            points.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return points.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }
        StdDraw.show();
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();
        ArrayList<Point2D> results = new ArrayList<>();
        for (Point2D point : points) {
            if (rect.contains(point)) {
                results.add(point);
            }
        }
        return results;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        double min = Double.POSITIVE_INFINITY;
        Point2D nearest = null;
        for (Point2D point : points) {
            if (Double.compare(point.distanceTo(p), min) < 0) {
                min = point.distanceTo(p);
                nearest = point;
            }
        }
        return nearest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        PointSET bute = new PointSET();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            bute.insert(p);
        }
        // StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.02);
        bute.draw();
        // StdDraw.setPenColor(Color.GREEN);
        // StdDraw.setPenRadius(0.005);
        // RectHV test = new RectHV(0.3, 0.1, 0.95, 0.7);
        // test.draw();

        // for (Point2D point2D : kdTree.range(test)) {
        //     StdDraw.setPenColor(Color.GREEN);
        //     point2D.draw();
        // }

        while (true) {
            if (StdDraw.isMousePressed()) {
                Point2D testp = new Point2D(StdDraw.mouseX(), StdDraw.mouseY());
                // StdDraw.setPenColor(Color.ORANGE);
                testp.draw();
                StdDraw.setPenRadius(0.005);
                bute.nearest(testp).drawTo(testp);
                System.out.println(bute.nearest(testp));
                StdDraw.show();
            }
        }
    }
}
