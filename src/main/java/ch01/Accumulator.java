package ch01;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-01-26<br>
 */
public class Accumulator {

    private double total;
    private int N;

    public Accumulator() {
    }

    void addDataValue(double val) {
        N++;
        total += val;
    }

    double mean() {
        return total / N;
    }

    @Override
    public String toString() {
        return "Mean (" + N + " values): " + String.format("%7.5f", mean());
    }


}
