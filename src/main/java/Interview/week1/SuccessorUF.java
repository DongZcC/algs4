package Interview.week1;

import edu.princeton.cs.algs4.StdOut;

public class SuccessorUF {
    int[] val;
    boolean[] isremoved;
    int count;

    public SuccessorUF(int N) {
        count = N;
        val = new int[N];
        isremoved = new boolean[N];
        for (int i = 0; i < N; i++) {
            val[i] = i;
            isremoved[i] = false;
        }
    }


    private void remove(int x) {
        validate(x);
        isremoved[x] = true;
        if (x >= 1 && isremoved[x - 1])
            union(x - 1, x);
        if (x <= count - 1 && isremoved[x + 1])
            union(x, x + 1);
    }

    private void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ)
            return;
        if (rootP > rootQ) {
            val[rootQ] = rootP;
        } else {
            val[rootP] = rootQ;
        }
    }

    private int find(int p) {
        while (p != val[p])
            p = val[p];
        return p;
    }

    private void validate(int x) {
        int n = val.length;
        if (x < 0 || x >= n) {
            throw new IllegalArgumentException("index " + x + " is not between 0 and " + (n - 1));
        }
    }


    public int getSuccessor(int x) {
        remove(x);

        return find(x) + 1;
    }

    public static void main(String[] args) {
        SuccessorUF successor = new SuccessorUF(10);
        StdOut.println( "The successor of  3 is " + successor.getSuccessor(3));
        StdOut.println( "The successor of  3 is " + successor.getSuccessor(4));
        StdOut.println( "The successor of  3 is " + successor.getSuccessor(5));
        StdOut.println( "The successor of  3 is " + successor.getSuccessor(0));
        StdOut.println( "The successor of  3 is " + successor.getSuccessor(1));
        StdOut.println( "The successor of  3 is " + successor.getSuccessor(2));
    }
}
