import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.io.File;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-01-25<br>
 */
public class Percolation {

    private int N;
    //原本只想用一个结构存储的，结果发现搞不定 isFull的问题，只能整两个了
    private WeightedQuickUnionUF TOP;
    private WeightedQuickUnionUF BOTTOM;
    private boolean[] isOpen;
    boolean percolationFlag = false;
    private final int VIRTUAL;

    public Percolation(int n) {
        this.N = n;
        if (n < 0) {
            throw new IllegalArgumentException("the argument is illegal");
        }
        isOpen = new boolean[n * n];
        TOP = new WeightedQuickUnionUF(n * n + 1);
        BOTTOM = new WeightedQuickUnionUF(n * n + 1);
        VIRTUAL = n * n;
        //two virtual site,
        //one is connected to top
        for (int i = 0; i < N; i++) {
            TOP.union(i, n * n);
        }
        //one is connected to bottom
        for (int i = (N - 1) * N; i < N * N; i++) {
            BOTTOM.union(i, n * n);
        }
    }


    public void open(int row, int col) {
        validate(row, col);
        int index = getIndex(row, col);
        isOpen[index] = true;
        //left
        if (col > 1) {
            if (isOpen(row, col - 1)) {
                TOP.union(index, index - 1);
                BOTTOM.union(index, index - 1);
            }
        }
        //right
        if (col < (N - 1)) {
            if (isOpen(row, col + 1)) {
                TOP.union(index, index + 1);
                BOTTOM.union(index, index + 1);
            }
        }

        //top
        if (row > 1) {
            if (isOpen(row - 1, col)) {
                TOP.union(index, index - N);
                BOTTOM.union(index, index - N);
            }
        }

        //bottom
        if (row < (N - 1)) {
            if (isOpen(row + 1, col)) {
                TOP.union(index, index + N);
                BOTTOM.union(index, index + N);
            }
        }

        if (TOP.connected(index, VIRTUAL) && BOTTOM.connected(index, VIRTUAL)) {
            percolationFlag = true;
        }
    }

    private int getIndex(int row, int col) {
        return (row - 1) * N + (col - 1);
    }

    private void validate(int row, int col) {
        if (row < 1 || col < 1 || row > N || col > N) {
            throw new IllegalArgumentException("the argument is illegal");
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return isOpen[getIndex(row, col)];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        int index = getIndex(row, col);
        return isOpen[index] && TOP.connected(index, VIRTUAL);
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < N * N; i++) {
            if (isOpen[i])
                count++;
        }
        return count;
    }

    public boolean percolates() {
        return percolationFlag;
    }


//    private static void generateCheck() {
//        // test client (optional)
//        Percolation p = new Percolation(3);
//        int row = 1, col = 3;
//        p.open(row, col);
//        p.printCheckResult(row, col);
//        row = 2;
//        col = 3;
//        p.open(row, col);
//        p.printCheckResult(row, col);
//        row = 3;
//        col = 3;
//        p.open(row, col);
//        p.printCheckResult(row, col);
//        row = 3;
//        col = 1;
//        p.open(row, col);
//        p.printCheckResult(row, col);
//        row = 2;
//        col = 1;
//        p.open(row, col);
//        p.printCheckResult(row, col);
//        row = 1;
//        col = 1;
//        p.open(row, col);
//        p.printCheckResult(row, col);
//    }

    private static void checkFromfile(String fileName) {
        In in = new In(new File(fileName));
        int n = in.readInt();
        Percolation p = new Percolation(n);
        while (in.hasNextLine()) {
            int row = in.readInt();
            int col = in.readInt();
            p.open(row, col);
        }

        StdOut.println("percolates is " + p.percolates());
    }


//    private void printCheckResult(int row, int col) {
//        StdOut.println("p(" + row + "," + col + ") is open=" + isOpen(row, col) + ";is full=" + isFull(row, col)
//                + ";percolates=" + percolates() + "; the number of opensite = " + numberOfOpenSites());
//    }

    public static void main(String[] args) {
        //generateCheck();
        //args[0] = "input20.txt";
        checkFromfile(args[0]);
    }
}
