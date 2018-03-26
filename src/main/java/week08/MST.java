package week08;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 功能说明: 最小生成树的API<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-26<br>
 */
public interface MST {

    Iterable<Edge> edges();

    double weight();
}
