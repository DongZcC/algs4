import java.util.ArrayList;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-02-28<br>
 */
public class Board {

    private static final int BLANK = 0;
    private final int dimension;
    private final int[][] blocks;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        dimension = blocks.length;
        this.blocks = new int[dimension][dimension];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }
    }

    // board dimension n
    public int dimension() {
        return dimension;
    }

    // number of blocks out of place
    public int hamming() {
        int Hamming = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                if (blocks[i][j] != BLANK && blocks[i][j] != getValue(i, j))
                    Hamming++;
            }
        }
        return Hamming;
    }

    private int getValue(int row, int col) {
        return dimension * row + col + 1;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int Manhattan = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] != BLANK && blocks[i][j] != getValue(i, j)) {
                    // int stepx = Math.abs(i - getRow(blocks[i][j]));
                    // int stepy =  Math.abs(j - getCol(blocks[i][j]));
                    Manhattan += Math.abs(i - getRow(blocks[i][j])) + Math.abs(j - getCol(blocks[i][j]));
                    // Manhattan += stepx + stepy;
                }
            }
        }
        return Manhattan;
    }

    private int getCol(int value) {
        return (value - 1) % dimension;
    }

    private int getRow(int value) {
        return (value - 1) / dimension;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                if (blocks[i][j] != BLANK && blocks[i][j] != getValue(i, j))
                    return false;
            }
        }
        return true;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        Board twin = new Board(blocks);
        int firRow = 0;
        int firCol = 0;
        if (blocks[firRow][firCol] == BLANK)
            firCol++;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] != BLANK && blocks[i][j] != blocks[firRow][firCol]) {
                    twin.swap(i, j, firRow, firCol);
                    return twin;
                }
            }
        }
        return twin;
    }

    private void swap(int i, int j, int firRow, int firCol) {
        int temp = blocks[i][j];
        blocks[i][j] = blocks[firRow][firCol];
        blocks[firRow][firCol] = temp;
    }

    // does this board equal y?
    // * reflexive 自反
    // * symmetric 对称
    // * transitive 传递
    // * argument is null
    public boolean equals(Object y) {
        if (y == null)
            return false;
        // 自反
        if (y == this)
            return true;

        if (y instanceof Board) {
            if (this.dimension != ((Board) y).dimension)
                return false;

            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    if (((Board) y).blocks[i][j] != blocks[i][j])
                        return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                // 如果当前点是 空白
                if (blocks[i][j] == BLANK) {
                    // 如果不是最上方, 就和上面的交换位置
                    if (i > 0) {
                        Board neighbor = new Board(blocks);
                        neighbor.swap(i, j, i - 1, j);
                        neighbors.add(neighbor);
                    }
                    // 不是最下面一个
                    if (i < dimension - 1) {
                        Board neighbor = new Board(blocks);
                        neighbor.swap(i, j, i + 1, j);
                        neighbors.add(neighbor);
                    }

                    // 不是最左面的
                    if (j > 0) {
                        Board neighbor = new Board(blocks);
                        neighbor.swap(i, j, i, j - 1);
                        neighbors.add(neighbor);
                    }

                    // 不是最右面的
                    if (j < dimension - 1) {
                        Board neighbor = new Board(blocks);
                        neighbor.swap(i, j, i, j + 1);
                        neighbors.add(neighbor);
                    }
                }
            }
        }
        return neighbors;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimension + "\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {
    }
}
