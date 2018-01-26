import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-01-25<br>
 */
public class PercolationStats {

    private final double[] result;
    private final int trials;
    private static final double CONFIDENCE_95 = 1.96;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("The param is not illegal");
        this.trials = trials;
        result = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            int[] params = StdRandom.permutation(n * n);
            for (int pos : params) {
                int row = pos / n + 1;
                int col = pos % n + 1;
                percolation.open(row, col);
                if (percolation.percolates())
                    break;
            }
            int count = percolation.numberOfOpenSites();
            double r = (double) count / (n * n);
            result[i] = r;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(result);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(result);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * Math.sqrt(stddev()) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * Math.sqrt(stddev()) / Math.sqrt(trials));
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("the param is illegal");
        }

        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, trails);
        StdOut.printf("%-30s", "mean");
        StdOut.println("= " + stats.mean());

        StdOut.printf("%-30s", "stddev");
        StdOut.println("= " + stats.stddev());

        StdOut.printf("%-30s", "95% confidence interval");
        StdOut.printf("= [%f, %f]", stats.confidenceLo(), stats.confidenceHi());
    }
}