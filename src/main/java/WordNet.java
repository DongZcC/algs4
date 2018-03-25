import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;

import java.util.ArrayList;
import java.util.List;


/**
 * 功能说明: part 2  week 1<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-20<br>
 */
public class WordNet {
    private SET<Noun> nouns;
    private final SAP sap;
    private final List<String> keys;

    /**
     * 名词数据结构,用来保存所有的名词
     * 这个数据结构，一开始想的有点不对了
     */
    private class Noun implements Comparable<Noun> {
        private String noun; // 名词
        private ArrayList<Integer> id; // 一个名词,对应有很多个id

        public Noun(String noun) {
            this.noun = noun;
            id = new ArrayList<>();
        }

        public String getNoun() {
            return noun;
        }

        public ArrayList<Integer> getId() {
            return id;
        }

        public void addId(Integer id) {
            this.id.add(id);
        }

        @Override
        public int compareTo(Noun o) {
            return this.noun.compareTo(o.noun);
        }
    }

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null)
            throw new IllegalArgumentException("the file name is null");
        nouns = new SET<>();
        keys = new ArrayList<>();
        In synsetStream = new In(synsets);
        int vertices = 0;
        while (synsetStream.hasNextLine()) {
            String[] words = synsetStream.readLine().split(",");
            int id = Integer.parseInt(words[0]);
            String[] wordSet = words[1].split(" ");
            for (String word : wordSet) {
                Noun noun = new Noun(word);
                if (nouns.contains(noun)) {
                    nouns.ceiling(noun).addId(id);
                } else {
                    nouns.add(noun);
                    noun.addId(id);
                }
            }
            keys.add(words[1]);
            vertices++;
        }

        Digraph dg = new Digraph(vertices);
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

        int root = 0;
        for (int i = 0; i < vertices; i++) {
            // 如果没有邻居节点
            if (!dg.adj(i).iterator().hasNext()) {
                root++;
            }
        }
        // 应该只有一个节点,没有邻居节点
        if (root > 1)
            throw new IllegalArgumentException("Not a rooted DAG");
        sap = new SAP(dg);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        ArrayList<String> result = new ArrayList<>();
        for (Noun noun : nouns) {
            result.add(noun.getNoun());
        }
        return result;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null)
            throw new IllegalArgumentException("the input is null");
        return nouns.contains(new Noun(word));
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        return sap.length(nouns.ceiling(new Noun(nounA)).getId(),
                nouns.ceiling(new Noun(nounB)).getId());
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        int ancestor = sap.ancestor(nouns.ceiling(new Noun(nounA)).getId(),
                nouns.ceiling(new Noun(nounB)).getId());
        return keys.get(ancestor);
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}
