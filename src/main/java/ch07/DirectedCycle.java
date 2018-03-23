package ch07;

import java.util.Stack;

/**
 * 功能说明: 有向环的API<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-23<br>
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle; // 有向环中的所有顶点
    private boolean[] onStack; // 递归调用的栈上的所有顶点

    public DirectedCycle(Digraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }

    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (this.hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.push(x);

                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }


    // 有向环中的所有顶点( 如果存在的话 )
    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        Digraph dg = new Digraph(4);
        dg.addEdge(0, 3);
        dg.addEdge(3, 2);
        dg.addEdge(2, 1);
        dg.addEdge(1, 3);

        DirectedCycle cycle = new DirectedCycle(dg);
    }
}
