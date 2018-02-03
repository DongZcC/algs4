package Interview.week1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-01-25<br>
 */
public class SocialconnectUF {

    private int N;

    private String fileName;

    public SocialconnectUF(int N, String fileName) {
        this.N = N;
        this.fileName = fileName;
    }

    public String getEarliestTimestamps() {
        //init the Interview.week1.FindMaxUF
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        In in = new In(new File(fileName));
        while (in.hasNextLine()) {
            String line = in.readLine();
            if (line != null && !line.trim().equals("")) {
                String[] info = line.split(" ");
                if (info.length == 3) {
                    Integer p = Integer.parseInt(info[1]);  //id
                    Integer q = Integer.parseInt(info[2]);  //id
                    if (uf.connected(p, q))
                        continue;
                    uf.union(p, q);
                    // All members have known each other
                    if (uf.count() == 1) {
                        return info[0];
                    }
                }
            }
        }
        // not enough
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        SocialconnectUF socialconnectUF = new SocialconnectUF(10, "data.txt");
        String timestamps = socialconnectUF.getEarliestTimestamps();
        StdOut.println("The earliest timestamps is  " + timestamps);
    }
}
