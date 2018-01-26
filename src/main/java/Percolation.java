import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-01-25<br>
 */
public class Percolation {

    // these field can be final
    private final int length;
    //原本只想用一个结构存储的，结果发现搞不定 isFull的问题，只能整两个了
    private final WeightedQuickUnionUF topVirtualNode;
    private final WeightedQuickUnionUF bottomVirtualNode;
    private boolean[] isOpen;
    private boolean percolationFlag = false;
    private final int virtual;
    private int count;

    public Percolation(int length) {
        this.length = length;
        if (length < 0) {
            throw new IllegalArgumentException("the argument is illegal");
        }
        isOpen = new boolean[length * length];
        topVirtualNode = new WeightedQuickUnionUF(length * length + 1);
        bottomVirtualNode = new WeightedQuickUnionUF(length * length + 1);
        virtual = length * length;
        // two virtual site,
        // one is connected to top
        for (int i = 0; i < this.length; i++) {
            topVirtualNode.union(i, length * length);
        }
        // one is connected to bottom
        for (int i = (this.length - 1) * this.length; i < this.length * this.length; i++) {
            bottomVirtualNode.union(i, length * length);
        }
    }


    public void open(int row, int col) {
        validate(row, col);
        int index = getIndex(row, col);
        count++;
        isOpen[index] = true;
        // left
        if (col > 1) {
            if (isOpen(row, col - 1)) {
                topVirtualNode.union(index, index - 1);
                bottomVirtualNode.union(index, index - 1);
            }
        }
        // right
        if (col < length) {
            if (isOpen(row, col + 1)) {
                topVirtualNode.union(index, index + 1);
                bottomVirtualNode.union(index, index + 1);
            }
        }

        // top
        if (row > 1) {
            if (isOpen(row - 1, col)) {
                topVirtualNode.union(index, index - length);
                bottomVirtualNode.union(index, index - length);
            }
        }

        // bottom
        if (row < length) {
            if (isOpen(row + 1, col)) {
                topVirtualNode.union(index, index + length);
                bottomVirtualNode.union(index, index + length);
            }
        }

        if ((count > length) && topVirtualNode.connected(index, virtual) && bottomVirtualNode.connected(index, virtual)) {
            percolationFlag = true;
        }
    }

    private int getIndex(int row, int col) {
        return (row - 1) * length + (col - 1);
    }

    private void validate(int row, int col) {
        if (row < 1 || col < 1 || row > length || col > length) {
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
        return isOpen[index] && topVirtualNode.connected(index, virtual);
    }

    public int numberOfOpenSites() {
        return this.count;
    }

    public boolean percolates() {
        return percolationFlag;
    }

    private static void checkFromfile(String fileName) {
        In in = new In(fileName);
        int n = in.readInt();
        Percolation p = new Percolation(n);
        while (in.hasNextLine()) {
            int row = in.readInt();
            int col = in.readInt();
            p.open(row, col);
            p.printCheckResult(row, col);
        }
    }


    private void printCheckResult(int row, int col) {
        StdOut.println("p(" + row + "," + col + ") is open=" + isOpen(row, col) + ";is full=" + isFull(row, col)
                + ";percolates=" + percolates() + "; the number of opensite = " + numberOfOpenSites());
    }

    public static void main(String[] args) {
        // generateCheck();
        // args[0] = "input20.txt";
        checkFromfile(args[0]);
    }
}