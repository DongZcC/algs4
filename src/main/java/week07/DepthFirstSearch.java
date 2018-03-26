package week07;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-21<br>
 */
public class DepthFirstSearch implements Search {

    private boolean[] marked;

    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    @Override
    public boolean marked(int v) {
        return marked[v];
    }

    private void dfs(Graph G, int s) {
        marked[s] = true;
        count++;
        for (Integer w : G.adj(s)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    @Override
    public int count() {
        return count;
    }
}
