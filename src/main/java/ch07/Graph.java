package ch07;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-16<br>
 */
public class Graph {

    private final int V; // vertices 点
    private Bag<Integer>[] adj;  // 临近节点 adjacent

    // create an empty graph with V vertices
    public Graph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    // create a graph from input stream
    // public Graph(In in) {
    //
    // }

    // add an edg v-w
    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    // vertices adjacent to v
    Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // number of vertices
    int V() {
        return V;
    }

    // number of edges
    int E() {
        return 0;
    }


    // string representation
    @Override
    public String toString() {
        return super.toString();
    }
}
