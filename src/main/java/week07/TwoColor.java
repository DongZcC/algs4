package week07;

import edu.princeton.cs.algs4.In;

/**
 * 功能说明: G 是二分图吗(双色问题) 能用两种颜色，将图的所有顶点着色，使得任意两条边
 * 的颜色都不相同
 * 二分图的场景，比如说，顶点是两种类型，一种是电影，一种是演员 <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-22<br>
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];

        for (int s = 0; s < G.V(); s++) {
            if (!marked[s])
                dfs(G, s);
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(G, w);
            } else if (color[w] == color[v])
                isTwoColorable = false;
        }
    }

    private boolean isBipartite() {
        return isTwoColorable;
    }


    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        TwoColor twoColor = new TwoColor(G);
    }
}
