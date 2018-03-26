package week08;

/**
 * 功能说明: 加权边的API <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-26<br>
 */
public class Edge implements Comparable<Edge> {

    private final int v; // vertex
    private final int w; // other vertex
    private final double weight; // weight of the edge


    // 用于初始化的构造函数
    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    // 边两端的顶点之一
    int either() {
        return v;
    }

    // 返回另一个顶点
    int other(int vertex) {
        if (vertex == v)
            return w;
        else if (vertex == w)
            return v;
        else
            throw new RuntimeException("Inconsistent edge");
    }

    @Override
    public int compareTo(Edge that) {
        if (this.weight() < that.weight()) return -1;
        else if (this.weight() > that.weight()) return 1;
        else return 0;
    }


    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
