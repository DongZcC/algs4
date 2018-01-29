package Interview.week1;

import edu.princeton.cs.algs4.StdOut;

/**
 * Description:
 * User: dzczyw
 * Date: 2018-01-28
 * Time: 19:39
 */
public class SearchBitonicArray {

    public static boolean isExist(int[] arr, int key) {
        // find the max integer
        int maxIndex = findMaxIndex(arr);
        // search in the left
        if (!searchLeft(key, arr, maxIndex)) {
            // if not find search in the right
            return searchRight(key, arr, maxIndex);
        }
        return true;
    }

    private static boolean searchRight(int key, int[] arr, int maxIndex) {
        int lo = maxIndex + 1;
        int hi = arr.length - 1;
        if (search(key, arr, lo, hi))
            return true;
        return false;
    }

    private static boolean searchLeft(int key, int[] arr, int maxIndex) {
        int lo = 0;
        int hi = maxIndex;
        if (search(key, arr, lo, hi))
            return true;
        return false;
    }

    private static boolean search(int key, int[] arr, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > arr[mid])
                lo = mid + 1;
            else if (key < arr[mid])
                hi = mid - 1;
            else
                return true;
        }
        return false;
    }

    private static int findMaxIndex(int[] arr) {
        int max = arr[0];
        int index = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 7, 10, 25, 33, 30, 27};
        StdOut.println("isExist ? " + isExist(arr, 28));
    }
}
