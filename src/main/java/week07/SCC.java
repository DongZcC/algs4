package week07;

/**
 * 功能说明: 强连通分量<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-23<br>
 */
public interface SCC {
    boolean stronglyConnected(int v, int w);

    int count();

    int id(int v); // v 所在的强两桶分量的标识符 在 0 - count() -1 之间
}
