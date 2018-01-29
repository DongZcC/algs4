package Interview.week1;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Description:
 * User: dzczyw
 * Date: 2018-01-28
 * Time: 19:18
 */
public class ThreeSum {

    public static int count(int[] arr) {
        Arrays.sort(arr);
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                Integer mid = binarySearch(arr, -(arr[i] + arr[j]));
                if (mid != -1)
                    count++;
            }
        }
        return count;
    }

    private static Integer binarySearch(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > arr[mid])
                lo = mid + 1;
            else if (key < arr[mid])
                hi = mid - 1;
            else {
                return mid;
            }
        }
        return -1;
    }


    public static int count2(int[] arr) {
        HashSet<Integer> sets = new HashSet<Integer>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int key = -(arr[i] + arr[j]);
                sets.add(key);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{30, -40, -20, -10, 40, 0, 10, 5};
        StdOut.println("The count of arr is " + count(arr));
    }
}
