package week08;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 功能说明: 加权无向图API<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-26<br>
 */
public class EdgeWeightedGraph {
    private final int V; //vertices
    private int E; // edges
    private Bag<Edge>[] adj; // 邻接表

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }


    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<>();
        for (int v = 0; v < V; v++)
            for (Edge e : adj[v])
                if (e.other(v) > v)
                    b.add(e);
        return b;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }
}
