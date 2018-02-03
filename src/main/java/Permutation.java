import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Description:
 * User: dzczyw
 * Date: 2018-02-03
 * Time: 13:09
 */
public class Permutation {
    public static void main(String[] args) {
        int count = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        In in = new In();
        while (!in.isEmpty()) {
            queue.enqueue(in.readString());
        }
        for (String item : queue) {
            StdOut.println(item);
            if (--count == 0)
                break;
        }
    }
}
