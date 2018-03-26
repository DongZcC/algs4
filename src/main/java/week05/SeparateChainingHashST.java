package week05;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-13<br>
 */
public class SeparateChainingHashST<Key, Value> {
    private int M = 91; // number of chains
    private Node[] st = new Node[M]; // array of chains

    private static class Node {
        private Node next;
        private Object key;
        private Object value;

        public Node(Object key, Object value, Node next) {
            this.key = key;
            this.value = value;
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key))
                return (Value) x.value;
        }
        return null;
    }

    public void put(Key key, Value value) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        st[i] = new Node(key, value, st[i]);
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, String> test = new SeparateChainingHashST<>();
        test.put("123", "456");
        test.put("1234", "4562");

        System.out.println(test.get("123"));
        System.out.println(test.get("1234"));
    }
}
