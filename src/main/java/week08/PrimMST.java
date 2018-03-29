package week08;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 功能说明: Prim's Algorithm eager implementation<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-28<br>
 */
public class PrimMST {

    private Edge[] edgeTo; // the most intimate distance edges
    private double[] distTo; // distTo[w] = edgeTo[w].weight
    private boolean[] marked; // v is in tree , is true
    private IndexMinPQ<Double> pq; // valid cut

    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        pq = new IndexMinPQ<>(G.V());
        distTo[0] = 0.0;
        pq.insert(0, 0.0); // 用顶点 0 和权重 0.0初始化 pq
        while (!pq.isEmpty()) {
            visit(G, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w])
                continue;  // v-w 失效
            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;

                distTo[w] = e.weight();
                if (pq.contains(w))
                    pq.changeKey(w, distTo[w]);
                else
                    pq.insert(w, distTo[w]);
            }
        }
    }

    public Iterable<Edge> edges() {
        return Arrays.asList(edgeTo);
    }

    public double weight() {
        double total = 0.0;
        for (double weight : distTo)
            total += weight;
        return total;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        PrimMST mst = new PrimMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
