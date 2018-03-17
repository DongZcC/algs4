package ch07;


import edu.princeton.cs.algs4.Bag;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description: 广度优先算法
 * User: dzczyw
 * Date: 2018-03-17
 * Time: 14:02
 */
public class BreadthFirstPaths {
    private boolean[] marked;
    private int edgTo[];
    private int s;

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgTo = new int[G.V()];
        this.s = s;

        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (Integer w : G.adj(v)) {
                if (!marked[w]) {
                    queue.add(w);
                    marked[w] = true;
                    edgTo[w] = v;
                }
            }
        }
    }
}
