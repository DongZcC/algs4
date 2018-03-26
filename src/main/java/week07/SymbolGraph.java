package week07;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 功能说明: 符号表
 *  通过这个符号表，实现了一个双向的关联关系
 *  既可以通过电影，找到所有的演员
 *  也可以通过演员，找到他演过的所有电影
 *  很溜<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-22<br>
 */
public class SymbolGraph {

    private ST<String, Integer> st; // 符号名 -> 索引
    private String[] keys; // 索引 -> 符号名
    private Graph G;

    public SymbolGraph(String fileName, String delim) {
        st = new ST<>();
        In in = new In(fileName);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delim); // 读取字符串
            for (int i = 0; i < a.length; i++) { // 为每个不同的字符串关联一个索引
                if (!st.contains(a[i]))
                    st.put(a[i], st.size());
            }
        }
        keys = new String[st.size()];

        for (String name : st.keys())
            keys[st.get(name)] = name;

        G = new Graph(st.size());
        in = new In(fileName); // 第二次
        while (in.hasNextLine()) { // 构造图
            String[] a = in.readLine().split(delim);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++)
                G.addEdge(v, st.get(a[i]));
        }
    }

    public boolean contains(String key) {
        return st.contains(key);
    }

    public int index(String key) {
        return st.get(key);
    }

    public String name(int v) {
        return keys[v];
    }

    //隐藏的Graph对象
    public Graph G() {
        return G;
    }


    public static void main(String[] args) {
        String fileName = args[0];
        String delim = args[1];
        SymbolGraph sg = new SymbolGraph(fileName, delim);

        Graph G = sg.G();
        while (StdIn.hasNextLine()) {
            String resource = StdIn.readLine();
            for (int w : G.adj(sg.index(resource)))
                StdOut.println("   " + sg.name(w));
        }
    }
}
