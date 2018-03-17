package ch07;


/**
 * Description: 判断图中的两点是否相连
 * User: dzczyw
 * Date: 2018-03-17
 * Time: 18:53
 */
public class CC {
    private boolean[] marked;

    private int[] id;

    private int count;

    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    /**
     * 深度优先算法
     *
     * @param G 图
     * @param v 点
     */
    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (Integer w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }
}
