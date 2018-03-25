import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 功能说明: part 2  week 1<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-20<br>
 */
public class WordNet {

    // private final ST<String, Integer> st; // 符号名 -> 索引
    private final List<String> nouns;
    private final String[] keys; // 索引 -> 符号名
    // private final ST<String, Noun> relation; // 名称和数据结构的对应关系
    private final Digraph dg; // 图
    private final SAP sap;

    // private class Noun {
    //     int id;
    //     String synonymSet;
    //     String gloss;
    //
    //     public Noun(int id, String synonymSet, String gloss) {
    //         this.id = id;
    //         this.synonymSet = synonymSet;
    //         this.gloss = gloss;
    //     }
    // }

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null)
            throw new IllegalArgumentException("the file name is null");
        nouns = new ArrayList<>();
        // st = new ST<>();
        // relation = new ST<>();
        In noun = new In(synsets);
        while (noun.hasNextLine()) {
            String[] words = noun.readLine().split(",");
            // st.put(words[1], Integer.parseInt(words[0]));
            nouns.add(words[1]);
            // relation.put(words[1], new Noun(Integer.parseInt(words[0]), words[1], words[2]));
        }

        keys = new String[nouns.size()];
        while (noun.hasNextLine()) {
            String[] words = noun.readLine().split(",");
            keys[Integer.parseInt(words[0])] = words[1];
        }

        dg = new Digraph(nouns.size());
        // 初始化图
        In relation = new In(hypernyms);
        while (relation.hasNextLine()) {
            String[] w = relation.readLine().split(",");
            for (int i = 1; i < w.length; i++)
                dg.addEdge(Integer.parseInt(w[0]), Integer.parseInt(w[i]));
        }

        // 检测是否为rooted DAG
        DirectedCycle cycle = new DirectedCycle(dg);
        if (cycle.hasCycle())
            throw new IllegalArgumentException("Not a rooted DAG");

        sap = new SAP(dg);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nouns;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null)
            throw new IllegalArgumentException("the input  is null");
        return nouns.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        return sap.length(nouns.indexOf(nounA), nouns.indexOf(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        return keys[sap.ancestor(nouns.indexOf(nounA), nouns.indexOf(nounB))];
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}
