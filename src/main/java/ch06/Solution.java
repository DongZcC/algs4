package ch06;

import edu.princeton.cs.algs4.BST;
import org.apache.commons.lang3.StringUtils;
import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-03-14<br>
 */
public class Solution {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // public ListNode reverseList(ListNode head) {
    //     ListNode newHead = null;
    //     while (head != null) {
    //         ListNode next = head.next;
    //         head.next = newHead;
    //         newHead = head;
    //         head = next;
    //     }
    //     return newHead;
    // }

    // public ListNode reverseList(ListNode head) {
    //     return reverseList(head, null);
    // }
    //
    // private ListNode reverseList(ListNode head, ListNode newHead) {
    //     if (head == null)
    //         return newHead;
    //     ListNode next = head.next;
    //     head.next = newHead;
    //     return reverseList(newHead, next);
    // }


    // public static ListNode reverseBetween(ListNode head, int m, int n) {
    //     // In place
    //     int count = 0;
    //     ListNode newHead = null, tmpHead = null, reverseHead = null;
    //     if (m == n)
    //         return head;
    //     boolean reverseFlag = false;
    //     while (head != null) {
    //         ListNode next = head.next;
    //         // 反转操作
    //         if (++count >= m && count <= n) {
    //             head.next = reverseHead;
    //             reverseHead = head;
    //             // 在最后一次反转置标志位
    //             if (count == n) {
    //                 reverseFlag = true;
    //             }
    //         } else {
    //             if (tmpHead == null && !reverseFlag) {
    //                 tmpHead = head;
    //                 newHead = tmpHead;
    //             } else {
    //                 // 如果有反转标志，就把所有反转了的值，插入结果链表中
    //                 if (reverseFlag) {
    //                     // 置反转标志位空
    //                     reverseFlag = false;
    //                     ListNode tmp = reverseHead;
    //                     while (tmp != null) {
    //                         if (tmpHead == null) {
    //                             newHead = tmp;
    //                             tmpHead = newHead;
    //                         } else {
    //                             tmpHead.next = tmp;
    //                             tmpHead = tmpHead.next;
    //                         }
    //                         tmp = tmp.next;
    //                     }
    //                 }
    //                 tmpHead.next = head;
    //                 tmpHead = tmpHead.next;
    //             }
    //         }
    //         head = next;
    //     }
    //     // 如果这个时候有反转标志，说明最后一次反转，没有再进行while循环 , 也要把反转接入到结果链表中
    //     if (reverseFlag) {
    //         ListNode tmp = reverseHead;
    //         while (tmp != null) {
    //             if (tmpHead == null) {
    //                 newHead = tmp;
    //                 tmpHead = newHead;
    //             } else {
    //                 tmpHead.next = tmp;
    //                 tmpHead = tmpHead.next;
    //             }
    //             tmp = tmp.next;
    //         }
    //     }
    //     return newHead == null ? reverseHead : newHead;
    // }


    // public static ListNode reverseBetween(ListNode head, int m, int n) {
    //     ListNode dummy = new ListNode(0);
    //     dummy.next = head;
    //     ListNode pre = dummy, curr = head;
    //     int k = 1;
    //     // 找到开始反转的前一个节点
    //     while (curr != null && k != m) {
    //         pre = pre.next;
    //         curr = curr.next;
    //         k++;
    //     }
    //
    //     while (curr != null && k != n) {
    //         // 找到两个需要交换的节点
    //         ListNode preNext = pre.next;
    //         ListNode next = curr.next;
    //         pre.next = curr.next;
    //         curr.next = next.next;
    //         next.next = preNext;
    //         k++;
    //     }
    //     return dummy.next;
    // }


    // public static List<List<Integer>> generate(int numRows) {
    //     List<List<Integer>> resultList = new ArrayList<>();
    //     for (int i = 0; i < numRows; i++) {
    //         List<Integer> row = new ArrayList<>();
    //         // 每次都是1开头
    //         row.add(1);
    //         for (int j = 1; j <= i; j++) {
    //             // 把前一行的值的出来
    //             List<Integer> pre = resultList.get(i - 1);
    //             if (pre.size() <= j) {
    //                 row.add(1);
    //                 break;
    //             } else {
    //                 row.add(pre.get(j -1) + pre.get(j ));
    //             }
    //         }
    //         resultList.add(row);
    //     }
    //     return resultList;
    // }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 求最小的差值
    // public int getMinimumDifference(TreeNode root) {
    //     // 找到最小的节点
    //     int min = findMin(root);
    //     // 找到最大的节点
    //     int max = findMax(root);
    //     ArrayList<Integer> queue = new ArrayList<>();
    //     // 遍历所有的节点，按照从小到大顺序排列
    //     // 有左边的节点，就先去遍历左边节点， 然后把这个节点加入到队列中， 然后遍历右侧节点
    //     keys(root, queue, min, max);
    //     int minDiff = Integer.MAX_VALUE;
    //     for (int i = 0; i < queue.size(); i++) {
    //         for (int j = i + 1; j < queue.size(); j++) {
    //             int currMin = Math.abs((queue.get(i) - queue.get(j)));
    //             if (Integer.compare(currMin, minDiff) < 0)
    //                 minDiff = currMin;
    //         }
    //     }
    //     return minDiff;
    // }
    //
    // private void keys(TreeNode x, ArrayList<Integer> queue, int min, int max) {
    //     if (x == null)
    //         return;
    //     int cmplo = Integer.compare(min, x.val);
    //     int cmphi = Integer.compare(max, x.val);
    //     if (cmplo < 0) keys(x.left, queue, min, max);
    //     if (cmplo <= 0 && cmphi >= 0) queue.add(x.val);
    //     if (cmphi > 0) keys(x.right, queue, min, max);
    // }
    //
    //
    // private int findMax(TreeNode root) {
    //     if (root.right == null) return root.val;
    //     else return findMax(root.right);
    // }
    //
    // private int findMin(TreeNode root) {
    //     if (root.left == null) return root.val;
    //     else return findMin(root.left);
    // }


    private int min = Integer.MAX_VALUE;
    private TreeNode prev;

    // 这种比较方式，保证了一个原则，永远是找的一个 最小的开始比较
    // 先去最左的节点找一个最小的值， 然后和中间节点比较。
    // 然后用中间节点去和最大的节点比较
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return min;
        getMinimumDifference(root.left);
        if (prev != null) {
            min = Math.min(min, Math.abs(prev.val - root.val));
            System.out.println(prev.val + ":" + root.val);
        }
        prev = root;
        getMinimumDifference(root.right);
        return min;
    }


    private final static String SPLITER = "";
    private final static String NULL = "n";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder st = new StringBuilder();
        serialize(root, st);
        return st.toString();
    }

    public void serialize(TreeNode root, StringBuilder st) {
        if (root == null) st.append(NULL + SPLITER);
        else {
            st.append(root.val + SPLITER);
            serialize(root.left, st);
            serialize(root.right, st);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> nodeVal = new LinkedList<>(Arrays.asList(data.split(SPLITER)));
        return deserialize(nodeVal);
    }

    private TreeNode deserialize(LinkedList<String> nodes) {
        String val = nodes.removeFirst();
        if (NULL.equals(val))
            return null;
        TreeNode head = new TreeNode(Integer.parseInt(val));
        head.left = deserialize(nodes);
        head.right = deserialize(nodes);
        return head;
    }


    private void preOrder(TreeNode root) {
        if (root == null)
            return;

        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }


    private void inOrder(TreeNode root) {
        if (root == null)
            return;

        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }

    private void postOrder(TreeNode root) {
        if (root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val);
    }

    // 卖出最佳的手续费
    public int maxProfit(int[] prices) {
        // 最简单的是 暴力破解 , 不满足时间要求
        // int profit = 0;
        // for (int i = 0; i < prices.length; i++) {
        //     for (int j = i + 1; j < prices.length; j++) {
        //         profit = Math.max(profit, prices[j] - prices[i]);
        //     }
        // }
        // return profit;

        // Kadane`s algorithm
        int maxEndingHere = 0, maxSoFar = 0;
        for (int i = 1; i < prices.length; i++) {
            maxEndingHere = Math.max(0, maxEndingHere + prices[i] - prices[i - 1]);
            maxSoFar = Math.max(maxEndingHere, maxSoFar);
        }
        return maxSoFar;
    }

    public int maxProfit(int[] prices, int fee) {
        return 0;
    }


    public int kadans(int[] price) {
        int maxSoFar = 0, maxEndingHere = 0;
        for (int i = 0; i < price.length; i++) {
            maxEndingHere = Math.max(0, maxEndingHere + price[i]);
            maxSoFar = Math.max(maxEndingHere, maxSoFar);
        }
        return maxSoFar;
    }


    // 27
    // public int removeElement(int[] nums, int val) {
    //     int i = 0, j = nums.length;
    //     while (i < j) {
    //         // 如果相等
    //         if (nums[i] == val) {
    //             nums[i] = nums[j - 1];
    //             j--;
    //         } else {
    //             i++;
    //         }
    //     }
    //     return j;
    // }

    // // 最优解法
    // public int removeElement(int[] nums, int val) {
    //     int left = 0;
    //     int right = nums.length - 1;
    //     int len = nums.length;
    //     while (left <= right) {
    //         if (nums[left] == val) {
    //             while (left < right && nums[right] == val) {
    //                 len--;
    //                 right--;
    //             }
    //             nums[left] = nums[right];
    //             right--;
    //             len--;
    //         }
    //         left++;
    //     }
    //     return len;
    // }


    //203
//    public ListNode removeElements(ListNode head, int val) {
//        if (head == null)
//            return null;
//        ListNode dummy = new ListNode(0);
//        dummy.next = head;
//        ListNode pre = dummy, curr = head;
//        while (curr != null) {
//            if (curr.val == val) {
//                pre.next = curr.next;
//            } else {
//                pre = pre.next;
//            }
//            curr = curr.next;
//
//        }
//        return dummy.next;
//    }





    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        // 最简单的方式，就是中序遍历一遍树的节点,这个时候就是有序的了。
        ArrayList<Integer> list = new ArrayList<>();
        inOrder(root, list);
        // 然后再用有序集合的那套东西来搞
        int left = 0, right = list.size() - 1;
        while(left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum == k)
                return true;
            else if (sum < k)
                left = binarySearchSmall(list, left, right, k - list.get(left));
            else
                right = binarySearchHigh(list, left, right,k - list.get(right));
        }
        return false;
    }

    private void inOrder(TreeNode root, ArrayList<Integer> list) {
        if (root == null)
            return ;
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }

    private Integer binarySearchSmall(ArrayList<Integer>  arr,int start, int end,int key) {
        int lo = start;
        int hi = end;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr.get(mid) > key)
                hi = mid - 1;
            else if (arr.get(mid) < key)
                lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }

    private Integer binarySearchHigh(ArrayList<Integer>  arr,int start, int end,int key) {
        int lo = start;
        int hi = end;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr.get(mid) > key)
                hi = mid - 1;
            else if (arr.get(mid) < key)
                lo = mid + 1;
            else
                return mid;
        }
        return hi;
    }
    public static void main(String[] args) {

        TreeNode t2 = new TreeNode(2);
        TreeNode t1 = new TreeNode(1);
        TreeNode t3 = new TreeNode(3);
        t2.left = t1;
        t2.right = t3;
        Solution s = new Solution();
        s.findTarget(t2, 3);
    }
}
