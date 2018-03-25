import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;

/**
 * 功能说明: part 2  week 1<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-25<br>
 */
public class SAP {

    private final static int ORIGIN = 0;
    private final Digraph dg;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null)
            throw new IllegalArgumentException();
        this.dg = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        // int ancestor = ancestor(v, w);
        // if (ancestor != -1) {
        //     BreadthFirstDirectedPaths p = new BreadthFirstDirectedPaths(dg.reverse(), ancestor);
        //     return p.distTo(v) + p.distTo(w);
        // }
        // return -1;
        return findShortest(v, w)[0];
    }

    private void validatePoints(int v, int w) {
        if (v > dg.V() - 1 || v < 0 || w > dg.V() || w < 0)
            throw new IllegalArgumentException(v + ":" + w);
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        // BreadthFirstDirectedPaths paths = new BreadthFirstDirectedPaths(dg.reverse(), 0);
        // if (paths.hasPathTo(v) && paths.hasPathTo(w)) {
        //     HashSet<Integer> set = new HashSet<>();
        //     for (int i : paths.pathTo(v)) {
        //         set.add(i);
        //     }
        //
        //     ArrayList<Integer> list = new ArrayList<>();
        //     for (Integer i : paths.pathTo(w)) {
        //         list.add(i);
        //     }
        //
        //     for (int i = list.size() - 1; i >= 0; i--)
        //         if (set.contains(list.get(i)))
        //             return list.get(i);
        // }
        // return -1;
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
        try {
            for (int i : bfdv.pathTo(ORIGIN)) {
                for (int j : bfdw.pathTo(ORIGIN)) {
                    // 有交点
                    if (i == j) {
                        int dis = bfdv.distTo(i) + bfdw.distTo(j);
                        result[0] = dis;
                        result[1] = i;
                        return result;
                    }
                }
            }
        } catch (NullPointerException e) {

        }
        return result;
    }

    private int[] findShortest(Iterable<Integer> v, Iterable<Integer> w) {
        int[] result = new int[]{-1, -1};
        int shortestLength = Integer.MAX_VALUE;
        int shortestAncestor = -1;
        for (int i : v) {
            for (int j : w) {
                int length = findShortest(v, w)[0];
                if (length != -1 && length < shortestLength) {
                    shortestLength = length;
                    shortestAncestor = i;
                }
            }
        }
        if (shortestAncestor != -1) {
            result[0] = shortestLength;
            result[1] = shortestAncestor;
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
