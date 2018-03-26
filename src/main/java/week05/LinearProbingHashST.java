package week05;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-13<br>
 */
public class LinearProbingHashST<Key, Value> {
    private int M = 3001;
    private Value[] vals = (Value[]) new Object[M];
    private Key[] keys = (Key[]) new Object[M];


    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value value) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key))
                break;
        }
        keys[i] = key;
        vals[i] = value;
    }

    public Value get(Key key) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key))
                return vals[i];
        }
        return null;
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, String> test = new SeparateChainingHashST<>();
        test.put("123", "456");
        test.put("1234", "4562");

        System.out.println(test.get("123"));
        System.out.println(test.get("1234"));
    }
}
