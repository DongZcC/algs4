package ch07;

import edu.princeton.cs.algs4.StdOut;

/**
 * 功能说明: 拓补排序<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-23<br>
 */
public class Topological {

    private Iterable<Integer> order;

    public Topological(Digraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
            // order = dfs.pre();
            // order = dfs.post();
        }
    }

    public boolean isDAG() {
        return order != null;
    }

    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        Digraph dg = new Digraph(4);
        dg.addEdge(0, 3);
        dg.addEdge(3, 2);
        dg.addEdge(2, 1);
        // dg.addEdge(0, 2);
        // dg.addEdge(1, 3);

        Topological topological = new Topological(dg);
        if (topological.isDAG()) {
            for (int w : topological.order) {
                StdOut.print(w + " ");
            }
        } else {
            StdOut.println("No");
        }
    }
}
