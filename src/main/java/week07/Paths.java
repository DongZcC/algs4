package week07;

/**
 * 功能说明: 解决迷宫问题<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-16<br>
 */
public interface Paths {
    boolean hasPathTo(int v);

    Iterable<Integer> pathTo(int v);
}
