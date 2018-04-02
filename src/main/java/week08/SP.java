package week08;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 功能说明: 最短路径API<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-04-02<br>
 */
public class SP {
    public SP(EdgeWeightedDigraph G, int s) {

    }

    // 从顶点s 到 v 的距离，如果不存在则路径为无穷大
    public double distTo(int v) {
        return 0.0;
    }

    // 是否存在从顶点s 到v 的路径
    public boolean hasPathTo(int v) {
        return false;
    }

    // 从顶点s 到v 的距离，如果不存在则为null
    Iterable<DirectedEdge> pathTo(int v) {
        return null;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G;
        G = new EdgeWeightedDigraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        SP sp = new SP(G, s);

        for (int t = 0; t < G.V(); t++) {
            StdOut.print(s + " to " + t);
            StdOut.printf(" (%4.2f): ", sp.distTo(t));
            if (sp.hasPathTo(t)) {
                for (DirectedEdge e : sp.pathTo(t)) {
                    StdOut.print(e + "   ");
                }
            }
            StdOut.println();
        }
    }
}
