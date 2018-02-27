package ch04;


/**
 * Description:
 * 优先级队列
 * User: dzczyw
 * Date: 2018-02-27
 * Time: 21:09
 */
public class MaxPQ<Key extends Comparable<Key>> {

    private int N;
    private final Key[] PQ;

    public MaxPQ(int capacity) {
        PQ = (Key[]) new Comparable[capacity + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    // 插入操作
    public void insert(Key key) {
        // 把key 插入到队列中
        PQ[++N] = key;
        // 该位置值上浮，找到合适自己位置
        swim(N);
    }

    private void swim(int k) {
        // 上浮， 保证这个父节点值大于当前值
        // 当k的位置不是根节点时,一直上浮
        while (k > 1) {
            if (less(k / 2, k))
                exch(k / 2, k);
            k = k / 2;
        }
    }

    public Key delMax() {
        Key max = PQ[1];
        // 交换最大值和最后一个值得位置
        exch(1, N);
        PQ[N--] = null;
        // 下沉找到自己应该在的位置
        sink(1);
        return max;
    }

    private void sink(int k) {
        // 下沉操作，保证两个子节点都比当前节点小
        while (2 * k <= N) {
            int j = 2 * k;
            // 保证j是子节点中 大的那一个
            if (j < N && less(j, j + 1))
                j++;
            // 如果当前节点 不小于子节点，就没必要继续循环下去了
            if (!less(k, j))
                break;
            exch(j, k);
            k = j;
        }
    }

    private boolean less(int a, int b) {
        return PQ[a].compareTo(PQ[b]) < 0;
    }

    private void exch(int a, int b) {
        Key temp = PQ[a];
        PQ[a] = PQ[b];
        PQ[b] = temp;
    }


    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<>(10);
        pq.insert("R");
        pq.insert("N");
        pq.insert("H");
        pq.insert("O");
        pq.insert("T");
        pq.insert("P");
        pq.insert("A");

        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
    }
}

