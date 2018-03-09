import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-08<br>
 */
public class KdTree {

    private Node root; // root of the K-d tree

    private final Comparator<Point2D> xCoordinateComparator = new XCoordinateComparator();

    private final Comparator<Point2D> yCoordinateComparator = new YCoordinateComparator();

    private class XCoordinateComparator implements Comparator<Point2D> {
        @Override
        public int compare(Point2D o1, Point2D o2) {
            return Double.compare(o1.x(), o2.x());
        }
    }

    private class YCoordinateComparator implements Comparator<Point2D> {
        @Override
        public int compare(Point2D o1, Point2D o2) {
            return Double.compare(o1.y(), o2.y());
        }
    }

    private class Node {
        private final Point2D key;
        private final RectHV val;
        private final NodeVal nodeVal;
        private Node left, right;
        private int size;
        private final boolean xCoordinate; //是否x轴划分

        public Node(Point2D key, RectHV val, int size, boolean xCoordinate) {
            this.key = key;
            this.val = val;
            this.size = size;
            this.xCoordinate = xCoordinate;
            nodeVal = new NodeVal();
        }

        private class NodeVal {
            private final RectHV left;
            private final RectHV right;

            public NodeVal() {
                if (xCoordinate) {
                    left = new RectHV(val.xmin(), val.ymin(), key.x(), val.ymax());
                    right = new RectHV(key.x(), val.ymin(), val.xmax(), val.ymax());
                } else {
                    left = new RectHV(val.xmin(), val.ymin(), val.xmax(), key.y());
                    right = new RectHV(val.xmin(), key.y(), val.xmax(), val.ymax());
                }
            }
        }
    }


    // construct an empty set of points
    public KdTree() {
    }

    // is the set empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // number of points in the set
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("argument to contain is null");
        return get(p);
    }

    private boolean get(Point2D key) {
        return get(root, key);
    }

    private boolean get(Node x, Point2D key) {
        if (key == null) throw new IllegalArgumentException("called get() with a null key");
        if (x == null) return false;
        if (x.key.compareTo(key) == 0)
            return true;
        int cmp = 0;
        if (x.xCoordinate) {
            cmp = xCoordinateComparator.compare(key, x.key);
        } else {
            cmp = yCoordinateComparator.compare(key, x.key);
        }
        if (cmp < 0) return get(x.left, key);
        else return get(x.right, key);
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("called put with a null val");

        if (!contains(p)) {
            // 插入
            if (root == null) {
                root = new Node(p, new RectHV(0, 0, 1, 1), 1, true);
                return;
            }
            put(root, p);
        }
    }

    private void put(Node x, Point2D key) {
        // 如果按照x轴切分
        if (x.xCoordinate) {
            int cmp = xCoordinateComparator.compare(key, x.key);
            // 左侧
            if (cmp < 0) {
                if (x.left != null)
                    put(x.left, key);
                    // 为空
                else {
                    RectHV parent = x.val;
                    double yMin = parent.ymin();
                    double yMax = parent.ymax();
                    double xMin = parent.xmin();
                    double xMax = x.key.x();
                    x.left = new Node(key, new RectHV(xMin, yMin, xMax, yMax), 1, false);
                }
                // 右侧
            } else {
                if (x.right != null)
                    put(x.right, key);
                else {
                    RectHV parent = x.val;
                    double yMin = parent.ymin();
                    double yMax = parent.ymax();
                    double xMin = x.key.x();
                    double xMax = parent.xmax();
                    x.right = new Node(key, new RectHV(xMin, yMin, xMax, yMax), 1, false);
                }
            }
        } else {
            int cmp = yCoordinateComparator.compare(key, x.key);
            if (cmp < 0) {
                if (x.left != null) {
                    put(x.left, key);
                } else {
                    RectHV parent = x.val;
                    double yMin = parent.ymin();
                    double yMax = x.key.y();
                    double xMin = parent.xmin();
                    double xMax = parent.xmax();
                    x.left = new Node(key, new RectHV(xMin, yMin, xMax, yMax), 1, true);
                }
            } else {
                if (x.right != null)
                    put(x.right, key);
                else {
                    RectHV parent = x.val;
                    double yMin = x.key.y();
                    double yMax = parent.ymax();
                    double xMin = parent.xmin();
                    double xMax = parent.xmax();
                    x.right = new Node(key, new RectHV(xMin, yMin, xMax, yMax), 1, true);
                }
            }
        }
        x.size = 1 + size(x.left) + size(x.right);
    }

    // draw all points to standard draw
    public void draw() {
        // 先画所有的点
        // StdDraw.setXscale(0, 1);
        // StdDraw.setYscale(0, 1);
        draw(root);
    }

    private void draw(Node x) {
        if (x == null)
            return;
        if (x.xCoordinate) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.setPenRadius(0.005);
            Point2D start = new Point2D(x.key.x(), x.val.ymin());
            Point2D end = new Point2D(x.key.x(), x.val.ymax());
            start.drawTo(end);
        } else {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.setPenRadius(0.005);
            Point2D start = new Point2D(x.val.xmin(), x.key.y());
            Point2D end = new Point2D(x.val.xmax(), x.key.y());
            start.drawTo(end);
        }
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.02);
        x.key.draw();
        draw(x.left);
        draw(x.right);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException("called range with null val");
        ArrayList<Point2D> results = new ArrayList<>();
        intersect(root, rect, results);
        return results;
    }

    private void intersect(Node x, RectHV rect, ArrayList<Point2D> results) {
        if (x == null)
            return;
        // 如果包含这个点，就加入这个点
        if (rect.contains(x.key)) {
            results.add(x.key);
        }
        // 如果在左边
        if (x.nodeVal.left.intersects(rect)) {
            intersect(x.left, rect, results);
        }
        if (x.nodeVal.right.intersects(rect)) {
            intersect(x.right, rect, results);
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("called nearest with null val");
        return findNearest(root, p);
    }

    private Point2D findNearest(Node x, Point2D key) {
        if (x == null)
            return null;
        Point2D nearest = x.key;
        // 如果距离比较小
        Point2D leftTmp = findNearest(x.left, key);
        if (leftTmp != null && Double.compare(leftTmp.distanceTo(key), nearest.distanceTo(key)) < 0)
            nearest = leftTmp;
        // 如果找到的最小距离，比右边的最小距离大 此时才需要继续比较
        if (Double.compare(nearest.distanceTo(key), x.nodeVal.right.distanceTo(key)) > 0) {
            Point2D rightTmp = findNearest(x.right, key);
            if (rightTmp != null && Double.compare(rightTmp.distanceTo(key), nearest.distanceTo(key)) < 0)
                nearest = rightTmp;
        }
        return nearest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

        String filename = args[0];
        In in = new In(filename);
        KdTree kdTree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdTree.insert(p);
        }
        kdTree.draw();
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
                StdDraw.setPenColor(Color.ORANGE);
                testp.draw();
                StdDraw.setPenRadius(0.005);
                kdTree.nearest(testp).drawTo(testp);
                System.out.println(kdTree.nearest(testp));
                StdDraw.show();
            }
        }
    }
}
