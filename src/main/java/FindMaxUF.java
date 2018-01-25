import edu.princeton.cs.algs4.StdOut;

public class FindMaxUF {

    private int count;
    private int[] parent;
    private int[] size;

    public FindMaxUF(int N) {
        parent = new int[N];
        size = new int[N];
        count = N;
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int i) {
        validate(i);
        while (parent[i] != i) {
            i = parent[i];
        }
        return i;
    }

    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;

        // change the union logic
        // make sure the largest one is the root element
        if (rootP > rootQ) {
            parent[rootQ] = rootP;
        } else {
            parent[rootP] = rootQ;
        }
        // if (size[rootP] > size[rootQ]) {
        //     size[rootP] += size[rootQ];
        //     parent[rootQ] = rootP;
        // } else {
        //     size[rootQ] += size[rootP];
        //     parent[rootP] = rootQ;
        // }
        count--;
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        FindMaxUF uf = new FindMaxUF(10);
        uf.union(1,2);
        uf.union(6,9);
        uf.union(1,9);
        StdOut.println("The largest element is " +  uf.find(1));
    }
}
