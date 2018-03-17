package ch07;

import java.util.LinkedList;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-16<br>
 */
public class DepthFirstPaths {

    private boolean[] marked;

    private int[] edgTo;

    private int s;

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (Integer w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
                // 走过的，就去标记一下，已经路过
                edgTo[w] = v;
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }


    Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        LinkedList<Integer> result = new LinkedList<>();
        for (int x = v; x != s; x = edgTo[x])
            result.addLast(x);
        result.addLast(s);
        return result;
    }

}
