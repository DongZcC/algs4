package ch07;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 功能说明: 某人和某人之间的距离<br>
 *     使用了 SymbolGraph 和 BreadthFirstPaths 来寻找最短路径
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-22<br>
 */
public class DegreeOfSeparation {
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(args[0], args[1]);
        Graph G = sg.G();

        String source = args[2];
        if (!sg.contains(source)) {
            StdOut.println(source + "not in database.");
            return ;
        }

        int s = sg.index(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

        while(!StdIn.isEmpty()) {
            String sink = StdIn.readLine();
            if (sg.contains(sink)) {
                int t = sg.index(sink);
                if (bfs.hasPathTo(t))
                    for (int v : bfs.pathTo(t))
                        StdOut.println("   " + sg.name(v));
                else
                    StdOut.println("Not connected");
            }
            else
                StdOut.println("Not in database.");;
        }
    }
}
