package ch06;

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
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, curr = head;
        while (curr != null) {
            if (curr.val == val) {
                pre.next = curr.next;
            } else {
                pre = pre.next;
            }
            curr = curr.next;

        }
        return dummy.next;
    }


    // 66
    public int[] plusOne(int[] digits) {
        int length = digits.length;
        for (int i = length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int[] result = new int[length + 1];
        result[0] = 1;
        System.arraycopy(digits, 0, result, 1, length);
        return result;
    }

    public int maxProfit2(int[] prices) {
        int[] temp = new int[prices.length - 1];
        // 先一次循环，找到差值
        for (int i = 1; i < prices.length; i++) {
            temp[i - 1] = prices[i] - prices[i - 1];
        }
        // 循环临时数组，只要数组值大于 0
        int reuslt = 0;
        for (Integer e : temp) {
            if (e > 0)
                reuslt += e;
        }
        return reuslt;
    }

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0, count = 0;
        // 保存去重数据
        ArrayList<String> set = new ArrayList<>();

        String[] temp = s.split("");
        int lo = 0, hi = temp.length - 1;
        while (lo <= hi) {
            if (!set.contains(temp[lo])) {
                set.add(temp[lo]);
                lo++;
                count++;
            } else {
                maxLength = Math.max(maxLength, count);
                count = set.size() - set.indexOf(temp[lo]) - 1;
                for (int i = set.indexOf(temp[lo]); i >= 0; i--)
                    set.remove(i);
            }
        }
        if (count != 0) {
            maxLength = Math.max(maxLength, count);
        }
        return maxLength;
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int[] arr = new int[nums1.length + nums2.length];
        int i1 = 0, j1 = nums1.length, i2 = 0, j2 = nums2.length;
        for (int k = 0; k < length; k++) {
            if (i1 == j1)
                arr[k] = nums2[i2++];
            else if (i2 == j2)
                arr[k] = nums1[i1++];
            else if (nums1[i1] < nums2[i2]) {
                arr[k] = nums1[i1++];
            } else {
                arr[k] = nums2[i2++];
            }
        }

        // 奇数
        if (arr.length % 2 == 1) {
            return (double) arr[length / 2];
        } else {
            return (double) (arr[length / 2] + arr[length / 2 - 1]) / 2;
        }
    }


    boolean[][] dp;

    public String longestPalindrome(String s) {
        dp = new boolean[s.length()][s.length()];
        int i, j;
        for (i = 0; i < s.length(); i++) {
            for (j = 0; j < s.length(); j++) {
                if (i >= j) {
                    dp[i][j] = true; //当i == j 的时候，只有一个字符的字符串; 当 i > j 认为是空串，也是回文

                } else {
                    dp[i][j] = false; //其他情况都初始化成不是回文
                }
            }
        }
        int k;
        int maxLen = 1;
        int rf = 0, rt = 0;
        for (k = 1; k < s.length(); k++) {
            for (i = 0; k + i < s.length(); i++) {
                j = i + k;
                //对字符串 s[i....j] 如果 s[i] != s[j] 那么不是回文
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    //如果s[i] == s[j] 回文性质由 s[i+1][j-1] 决定
                    dp[i][j] = dp[i + 1][j - 1];
                    if (dp[i][j]) {
                        if (k + 1 > maxLen) {
                            maxLen = k + 1;
                            rf = i;
                            rt = j;
                        }
                    }
                }
            }
        }
        return s.substring(rf, rt + 1);
    }


    public String convert(String s, int numRows) {
        int len = s.length();
        StringBuffer[] sb = new StringBuffer[numRows];
        for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();
        int i = 0;
        while (i < len) {
            for (int idx = 0; idx < numRows && i < len; idx++) // vertically down
                sb[idx].append(s.charAt(i++));
            for (int idx = numRows - 2; idx >= 1 && i < len; idx--) // obliquely up
                sb[idx].append(s.charAt(i++));
        }
        return "";
    }


    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result)
                return 0;
            x = x / 10;
            result = newResult;
        }
        return result;
    }

    //  8
    public int myAtoi(String str) {
        if (str == null || "".equals(str.trim()))
            return 0;
        int sign = 1, base = 0, index = 0;
        // 前导空格删除
        while (str.charAt(index) == ' ')
            index++;
        // 判断符号位
        if (str.charAt(index) == '-' || str.charAt(index) == '+') {
            sign = str.charAt(index++)  == '-' ? -1 : 1;
        }

        // 开始搞数字了, 不是数字的全部排出
        while (str.charAt(index) >= '0' && str.charAt(index) <= '9') {
            // 判断是否溢出
            if (base > Integer.MAX_VALUE / 10 || base == Integer.MAX_VALUE / 10 && str.charAt(index) > '7') {
                if (sign == 1)
                    return Integer.MAX_VALUE;
                else
                    return Integer.MIN_VALUE;
            }
            base = base * 10 + str.charAt(index++) - '0';
        }
        return base * sign;
    }


    public int romanToInt(String s) {

        int sum = 0;
        if (s.contains("IV"))
            sum -= 2;
        if (s.contains("IX"))
            sum -= 2;
        if (s.contains("XL"))
            sum -= 20;
        if (s.contains("XC"))
            sum -= 20;
        if (s.contains("CD"))
            sum -= 200;
        if (s.contains("CM"))
            sum -= 200;

        Map<Character, Integer> map = new HashMap<Character, Integer>() {
            {
                put('I', 1);
                put('V', 5);
                put('X', 10);
                put('L', 50);
                put('C', 100);
                put('D', 500);
                put('M', 1000);
            }
        };


        for (int i = 0; i < s.length(); i++) {
            sum += map.get(s.charAt(i));
        }

        return sum;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        String x = "1";
        s.myAtoi(x);
        char z = 'A';
        // 97
        System.out.println((int) z);
        s.myAtoi(x);
    }
}
