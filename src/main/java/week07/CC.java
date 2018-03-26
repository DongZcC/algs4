package week07;

/**
 * 功能说明: 连通分量<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-22<br>
 */
public interface CC {
    boolean connected(int v, int w); // v 和 w 连通吗

    int count(); // 连通分量数

    int id(int v); // v 所在的连通分量的标识符
}
