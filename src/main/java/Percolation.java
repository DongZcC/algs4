import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-01-25<br>
 */
public class Percolation {

    private WeightedQuickUnionUF uf;

    private int n;


    public Percolation(int n) {
        if (n < 0)
            throw new IllegalArgumentException("the argument is illegal");
        n = n;
        uf = new WeightedQuickUnionUF(n * n);
    }


    public void open(int row, int col) {
        validate(row, col);
        int index = getIndex(row, col);
        //left
        if (col > 1) {
            if (isOpen(row, col - 1)) {
                uf.union(index, index - 1);
            }
        }
        //right
        if (col < n) {
            if (isOpen(row, col + 1)) {
                uf.union(index, index + 1);
            }
        }
        //top
        if (row > 1) {
            if (isOpen(row - 1, col)) {
                uf.union(index, index - n);
            }
        }
        //bottom
        if (row < n) {
            if (isOpen(row + 1, col)) {
                uf.union(index, index + n);
            }
        }
    }

    private void validate(int row, int col) {
        if (row < 1 || col < 1) {
            throw new IllegalArgumentException("the argument is illegal");
        }
    }

    private int getIndex(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return false;
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return false;
    }

    /**
     * @return the number of the open sites
     */
    public int numberOfOpenSites() {
        return n * n - uf.count() + 1;
    }

    public boolean percolates() {
        return false;
    }
}
