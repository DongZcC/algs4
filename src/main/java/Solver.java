import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Collections;
import java.util.LinkedList;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-02-28<br>
 */
public class Solver {
    private SearchNode currentNode;
    private LinkedList<Board> solution;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new IllegalArgumentException();
        // 根据初始化面板， 初始化
        MinPQ<SearchNode> boardMinPQ = new MinPQ<>();
        MinPQ<SearchNode> twinMinPQ = new MinPQ<>();
        currentNode = new SearchNode(initial, null);
        solution = new LinkedList<>();
        boardMinPQ.insert(currentNode);
        SearchNode currentTwin = new SearchNode(currentNode.board.twin(), null);
        twinMinPQ.insert(currentTwin);
        while (true) {
            currentNode = boardMinPQ.delMin();
            // 当前入栈移动数，和上次入栈相同时，移除上次无效操作
            // 如果当前节点已经是目标解法
            if (currentNode.board.isGoal())
                break;
            // 如果不是，就把所有邻居节点都放入优先级队列中
            putNeighborIntoQueue(currentNode, boardMinPQ);

            currentTwin = twinMinPQ.delMin();
            if (currentTwin.board.isGoal())
                break;
            // 如果不是，就把所有邻居节点都放入优先级队列中
            putNeighborIntoQueue(currentTwin, twinMinPQ);
        }

    }

    private void putNeighborIntoQueue(SearchNode searchNode, MinPQ<SearchNode> PQ) {
        for (Board neighbor : searchNode.board.neighbors()) {
            // 第一次 preSearchNode 为null
            if (searchNode.preSearchNode == null || !searchNode.preSearchNode.board.equals(neighbor)) {
                PQ.insert(new SearchNode(neighbor, searchNode));
            }
        }
    }


    private class SearchNode implements Comparable<SearchNode> {
        private final int move;
        private final SearchNode preSearchNode;
        private final Board board;
        private final int priority;

        public SearchNode(Board current, SearchNode preSearchNode) {
            this.board = current;
            this.preSearchNode = preSearchNode;
            if (preSearchNode == null)
                move = 0;
            else
                move = preSearchNode.move + 1;
            priority = board.manhattan() + move;
        }

        @Override
        public int compareTo(SearchNode o) {
            // 首先比较优先级
            // 优先级都相同应该比较 move的值, move的值越大，优先级越高
            if (Integer.compare(this.priority, o.priority) == 0) {
                return Integer.compare(o.preSearchNode.move, this.preSearchNode.move);
            }
            return Integer.compare(this.priority, o.priority);
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return currentNode.board.isGoal();
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable())
            return currentNode.move;
        return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (isSolvable()) {
            SearchNode node = currentNode;
            while (node != null) {
                solution.addFirst(node.board);
                node = node.preSearchNode;
            }
            return solution;
        }
        return null;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
        System.out.println(solver.solution.size());
    }
}

