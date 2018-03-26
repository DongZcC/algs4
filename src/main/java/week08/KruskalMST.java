package week08;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

/**
 * 功能说明: kruskal's algorithm<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-26<br>
 */
public class KruskalMST implements MST {
    Queue<Edge> mst = new Queue<>();


    public KruskalMST(EdgeWeightedGraph G) {
        MinPQ<Edge> minPQ = new MinPQ<>();
        for (Edge e : G.edges())
            minPQ.insert(e);
        UF uf = new UF(G.V());
        while (!minPQ.isEmpty() && mst.size() != G.V() - 1) {
            Edge e = minPQ.delMin();
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                mst.enqueue(e);
                uf.union(v, w);
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }

    @Override
    public double weight() {
        double weight = 0.0;
        for (Edge e : mst)
            weight += e.weight();
        return weight;
    }
}
