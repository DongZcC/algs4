package ch07;


/**
 * 功能说明: 计算强连通分量的 Kosaraju 算法<br>
 * 1.在给定的一幅有向图G中，使用DepthFirstOrder 来计算他的反向图Gr 的逆后续排列
 * 2.在G中进行标准的深度优先搜索，但是要按照刚才计算得到的顺序，来访问所有未被标记的点
 * 3.在构造函数中，所有在同一个递归的dfs()调用中被访问到的顶点都在同一个强连通分量重
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-23<br>
 */
public class KosarajuSCC implements SCC {

    private boolean[] marked; // 已访问过的点
    private int[] id; // 强连通分量标识符
    private int count; // 强连通分量的数量

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w])
                dfs(G, w);
        }
    }

    @Override
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int id(int v) {
        return id[v];
    }
}
