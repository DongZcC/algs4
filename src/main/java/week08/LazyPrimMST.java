package week08;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-26<br>
 */
public class LazyPrimMST {
    private boolean[] marked; // 最小生成树的顶点
    private Queue<Edge> mst; // 最小生成树的边
    private MinPQ<Edge> pq; // 横切边(包括失效的边)

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new MinPQ<>();
        marked = new boolean[G.V()];
        mst = new Queue<>();

        visit(G, 0); // 假设G是连通的
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue; // 跳过失效的边
            mst.enqueue(e);
            if (!marked[v])
                visit(G, v);
            if (!marked[w])
                visit(G, w);
        }

    }

    private void visit(EdgeWeightedGraph G, int v) {
        // 标价顶点v 并将所有连接v 和未被标记的顶点加入pq
        marked[v] = true;
        for (Edge e : G.adj(v))
            if (!marked[e.other(v)])
                pq.insert(e);
    }

    public double weight() {
        double weight = 0.0;
        for (Edge edge : mst) {
            weight += edge.weight();
        }
        return weight;
    }


    public Iterable<Edge> edges() {
        return mst;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        LazyPrimMST mst = new LazyPrimMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
