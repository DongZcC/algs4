import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

/**
 * 功能说明: part 2  week 1<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-25<br>
 */
public class SAP {

    private final Digraph dg;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null)
            throw new IllegalArgumentException();
        this.dg = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        return findShortest(v, w)[0];
    }

    private void validatePoints(int v, int w) {
        if (v > dg.V() - 1 || v < 0 || w > dg.V() || w < 0)
            throw new IllegalArgumentException(v + ":" + w);
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        return findShortest(v, w)[1];
    }


    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return findShortest(v, w)[0];
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return findShortest(v, w)[1];
    }


    private int[] findShortest(int v, int w) {
        validatePoints(v, w);
        int[] result = new int[]{-1, -1};
        BreadthFirstDirectedPaths bfdv = new BreadthFirstDirectedPaths(dg, v);
        BreadthFirstDirectedPaths bfdw = new BreadthFirstDirectedPaths(dg, w);
        int distance = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int i = 0; i < dg.V(); i++) {
            if (bfdv.hasPathTo(i) && bfdw.hasPathTo(i) &&
                    distance > (bfdv.distTo(i) + bfdw.distTo(i))) {
                distance = bfdv.distTo(i) + bfdw.distTo(i);
                ancestor = i;
            }
        }

        if (ancestor != -1) {
            result[0] = distance;
            result[1] = ancestor;
        }
        return result;
    }

    private int[] findShortest(Iterable<Integer> v, Iterable<Integer> w) {
        for (int i : v)
            for (int j : w)
                validatePoints(i, j);

        int[] result = new int[]{-1, -1};
        BreadthFirstDirectedPaths bfdv = new BreadthFirstDirectedPaths(dg, v);
        BreadthFirstDirectedPaths bfdw = new BreadthFirstDirectedPaths(dg, w);
        int distance = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int i = 0; i < dg.V(); i++) {
            if (bfdv.hasPathTo(i) && bfdw.hasPathTo(i) &&
                    distance > (bfdv.distTo(i) + bfdw.distTo(i))) {
                distance = bfdv.distTo(i) + bfdw.distTo(i);
                ancestor = i;
            }
        }

        if (ancestor != -1) {
            result[0] = distance;
            result[1] = ancestor;
        }
        return result;
    }


    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
