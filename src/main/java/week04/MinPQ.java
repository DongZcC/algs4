package week04;


/**
 * 功能说明: 最小优先级队列<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-29<br>
 */
public class MinPQ<Key extends Comparable<Key>> {
    private int N;
    private final Key[] PQ;

    public MinPQ(int capacity) {
        PQ = (Key[]) new Comparable[capacity + 1];
    }

    public void insert(Key key) {
        PQ[++N] = key;
        swim(N);
    }

    public Key deleteMin() {
        Key min = PQ[1];
        // 交换
        exch(1, N--);
        sink(1);
        PQ[N + 1] = null;
        return min;
    }

    private void exch(int i, int j) {
        Key tmp = PQ[i];
        PQ[i] = PQ[j];
        PQ[j] = tmp;
    }

    private void sink(int k) {
        while (2 * k <= N) {
            // 找到两个孩子中小的那个
            int j = 2 * k;
            if (j < N && !less(j, j + 1))
                j++;
            if (!less(k, j))
                exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return PQ[i].compareTo(PQ[j]) < 0;
    }

    private void swim(int k) {
        while (k > 1 && !less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    public static void main(String[] args) {
        MinPQ<String> pq = new MinPQ<>(10);
        pq.insert("R");
        pq.insert("N");
        pq.insert("H");
        pq.insert("O");
        pq.insert("T");
        pq.insert("P");
        pq.insert("A");

        System.out.println(pq.deleteMin());
        System.out.println(pq.deleteMin());
    }
}
