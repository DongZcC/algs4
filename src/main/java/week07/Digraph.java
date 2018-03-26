package week07;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-23<br>
 */
public class Digraph {
    private final int V; // vertices
    private int E; // edges
    private Bag<Integer>[] adj;  // 临近节点 adjacent

    public Digraph(int v) {
        this.V = v;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Bag<>();
    }

    public Digraph(In in) {
        this(in.readInt());
        int E = in.readInt();

        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // 倒置的话，应该也还是比较简单的
    public Digraph reverse() {
        Digraph G = new Digraph(this.V);
        for (int i = 0; i < this.V; i++) {
            for (int w : adj[i]) {
                G.addEdge(w, i);
            }
        }
        return G;
    }

    public String toString() {
        return super.toString();
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }
}
