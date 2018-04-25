package week05;

import edu.princeton.cs.algs4.In;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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


    public String longestPalindrome2(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
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
            sign = str.charAt(index++) == '-' ? -1 : 1;
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

    public String longestCommonPrefix(String[] strs) {
        String result = "";
        if (strs == null || strs.length == 0)
            return "";

        if (strs.length == 1)
            return strs[0];

        // 找到数组中长度最短的那个
        String minLength = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() < minLength.length())
                minLength = strs[i];
        }

        result = minLength;
        while (!"".equals(result)) {
            boolean flag = true;
            for (String str : strs) {
                if (!str.startsWith(result)) {
                    result = result.substring(0, result.length() - 1);
                    flag = false;
                    break;
                }
            }

            if (flag)
                return result;
        }
        return result;
    }


    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        // 数组排序先
        // [-2, -1, 0, 0, 1, 2]
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i == 0 || i > 0 && nums[i] != nums[i - 1]) {
                for (int j = i + 1; j < nums.length - 2; j++) {
                    if (j == 1 || nums[j] != nums[j - 1]) {
                        int t = target - nums[i] - nums[j];
                        int lo = j + 1, hi = nums.length - 1;
                        while (lo < hi) {
                            int sum = nums[lo] + nums[hi];
                            if (sum < t)
                                lo++;
                            else if (sum > t)
                                hi--;
                            else {
                                resultList.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
                                while (lo < hi && nums[lo] == nums[++lo]) ;
                                while (lo < hi && nums[hi] == nums[--hi]) ;
                            }
                        }
                    }
                }
            }
        }

        return resultList;
    }


    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }


    // public List<String> generateParenthesis(int n) {
    //     List<String> result = new ArrayList<>();
    //     generateAllCharacters(new char[n * 2], 0, result);
    //     return result;
    // }
    private void generateAllCharacters(char[] current, int pos, List<String> result) {
        if (current.length == pos) {
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAllCharacters(current, pos + 1, result);
            current[pos] = ')';
            generateAllCharacters(current, pos + 1, result);
        }
    }

    private boolean valid(char[] current) {
        int balance = 0;
        for (int i = 0; i < current.length; i++) {
            if (current[i] == '(')
                balance++;
            else
                balance--;

            if (balance < 0)
                return false;
        }
        return balance == 0;
    }


    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur);
        }

        if (open < max)
            backtrack(ans, cur + "(", open + 1, close, max);
        if (close < open)
            backtrack(ans, cur + ")", open, close + 1, max);
    }

    /**
     * 太难理解了
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis3(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left : generateParenthesis3(c))
                    for (String right : generateParenthesis3(n - 1 - c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                curr.next = l2;
                l2 = l2.next;
            } else if (l2 == null) {
                curr.next = l1;
                l1 = l1.next;
            } else if (less(l1, l2)) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        return dummy.next;
    }

    private boolean less(ListNode l1, ListNode l2) {
        if (l1.val <= l2.val)
            return true;
        else
            return false;
    }


    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (ListNode node : lists) {
            while (node != null) {
                queue.add(node.val);
                node = node.next;
            }
        }

        while (!queue.isEmpty()) {
            head.next = new ListNode(queue.poll());
            head = head.next;
        }
        return dummy.next;
    }


    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        // 代表lists中有几个可用的
        int k = lists.length;
        while (k > 0) {
            // 循环list 中找到一个最小的node值,和下标
            ListNode minNode = new ListNode(Integer.MAX_VALUE);
            int minIndex = 0;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && lists[i].val < minNode.val) {
                    minNode = lists[i];
                    minIndex = i;
                }
            }
            // already exhausted
            if (minNode.next == null) {
                k--;
            }
            // next
            if (lists[minIndex] != null) {
                lists[minIndex] = lists[minIndex].next;
                // add to the result linkedlist
                head.next = minNode;
                head = head.next;
            }

        }
        return dummy.next;
    }


    public ListNode mergeKLists3(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        Queue<ListNode> queue = new PriorityQueue<>(new ListNodeComparator());
        for (ListNode node : lists) {
            while (node != null) {
                ListNode n = node;
                n.next = null;
                queue.add(n);
                node = node.next;
            }

        }

        while (!queue.isEmpty()) {
            head.next = queue.poll();
            head = head.next;
        }

        return dummy.next;
    }

    private class ListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return Integer.compare(o1.val, o2.val);
        }
    }


    public ListNode mergeKLists4(ListNode[] lists) {
        int k = lists.length;
        int interval = 1;
        while (interval < k) {
            for (int i = 0; i < k - interval; i += interval * 2) {
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        if (k > 0)
            return lists[0];
        else
            return null;
    }

    public ListNode mergeKList5(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        return sort(lists, 0, lists.length - 1);
    }

    private ListNode sort(ListNode[] lists, int lo, int hi) {
        if (lo >= hi)
            return lists[lo];
        int mid = lo + (hi - lo) / 2;
        ListNode left = sort(lists, lo, mid);
        ListNode right = sort(lists, mid + 1, hi);
        return mergeTwoLists(left, right);
    }


    public ListNode mergeKList6(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        int N = lists.length;
        for (int sz = 1; sz < N; sz += sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                lists[lo] = mergeTwoLists(lists[lo], lists[lo + sz]);
            }
        }
        if (N > 0)
            return lists[0];
        else
            return null;
    }


    public ListNode swapPairs(ListNode head) {
        if (head == null)
            return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = head, prepre = dummy;
        ListNode curr = head.next;
        int index = 1;
        while (curr != null) {
            if (++index % 2 == 0) {
                ListNode next = curr.next;
                curr.next = pre;
                pre.next = next;
                prepre.next = curr;

                // next ;
                prepre = curr;
                curr = next;
            } else {
                pre = pre.next;
                curr = curr.next;
                prepre = prepre.next;
            }

        }
        return dummy.next;
    }


    public ListNode swapPairs2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        // 0->1->2->3  ===>  0->2->1->3
        while (pre.next != null && pre.next.next != null) {
            ListNode next = pre.next.next.next; // 3
            ListNode tmp = pre.next.next;  // 2
            tmp.next = pre.next; // 2->1
            pre.next.next = next; // 1->3
            pre.next = tmp; // 0->2
            pre = tmp.next; // pre->1
        }
        return dummy.next;
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        // 找到当前开始反转的k + 1个节点
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        // 找到了可以反转的足够的点
        if (count == k) {
            // 寻找是否有下一组
            curr = reverseKGroup(curr, k);
            // 这个时候开始反转组内的元素
            while (count-- > 0) {
                ListNode tmp = head.next;
                head.next = curr;
                curr = head;
                head = tmp;
            }
            head = curr;
        }
        return head;
    }


    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }


    public int divide(int dividend, int divisor) {
        // 如果除数 是0
        if (divisor == 0)
            return 0;
        // 符号位
        int sign = 1;
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            sign = -1;
        }

        // 把除数被除数转成long ，这样如果溢出了，可以正常判断
        long did = Math.abs((long) dividend);
        long dior = Math.abs((long) divisor);

        long ans = ldivide(did, dior);
        int result;
        if (ans > Integer.MAX_VALUE) {
            result = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            result = (int) (sign * ans);
        }

        return result;
    }

    private long ldivide(long dividend, long divisor) {
        if (dividend / divisor == 0)
            return 0;
        long multi = 1;
        long sum = divisor;
        while ((sum + sum) <= dividend) {
            sum += sum;
            multi += multi;
        }
        return multi + ldivide(dividend - sum, divisor);
    }


    // 30.
    public List<Integer> findSubstring(String s, String[] words) {
        // 结果数组
        List<Integer> indexs = new ArrayList<>();
        Map<String, Integer> w = new HashMap<>();
        for (String word : words) {
            // 放值初始化值，以及每个词语的出现次数
            w.put(word, w.getOrDefault(word, 0) + 1);
        }
        int strLength = s.length(), num = words.length, wordLength = words[0].length();
        for (int i = 0; i <= strLength - num * wordLength; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while (j < num) {
                String word = s.substring(i + j * wordLength, i + (j + 1) * wordLength);
                // 有满足条件的就自增
                if (w.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    // 如果出现的次数，超过预定的次数,也属于不满足要求的
                    if (seen.get(word) > w.get(word)) {
                        break;
                    }
                } else {
                    // 找不到 ，直接退出这次寻找
                    break;
                }
                j++;
            }

            // 如果满足条件的，就增加到索引数组中
            if (j == num) {
                indexs.add(i);
            }
        }
        return indexs;
    }

    // 31
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1)
            return;
        // 最后一个值
        int i = nums.length - 1;
        // 找到第一个需要交换的值
        for (; i >= 1; i--) {
            if (nums[i] > nums[i - 1]) {
                break;
            }
        }

        if (i != 0) {
            // 交换
            swap(nums, i - 1);
        }

        Arrays.sort(nums, i, nums.length);

    }

    // 找到第一个比需要交换的值大的值进行交换
    private void swap(int[] nums, int i) {
        for (int j = nums.length - 1; j > i; j--) {
            if (nums[j] > nums[i]) {
                int tmp = nums[j];
                nums[j] = nums[i];
                nums[i] = tmp;
                break;
            }
        }
    }

    // 32. brute force  Time Limited
    public int longestValidParentheses(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (isValid2(s.substring(i, j + 1))) {
                    if (j - i > result) {
                        result = j - i;
                    }
                }
            }
        }
        return result;
    }

    // 这个函数来判断是否是合法的括号
    private boolean isValid2(String s) {
        Stack<Character> st = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == '(')
                st.push(')');
            else if (st.empty() || c != st.pop())
                return false;
        }
        return st.empty();
    }


    // dynamic programming
    public int longestValidParentheses2(String s) {
        int maxlen = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i == 1) ? 2 : dp[i - 2] + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
            }
            maxlen = Math.max(maxlen, dp[i]);
        }
        return maxlen;
    }


    // 37.
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0)
            return;
        solve(board);
    }

    public boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {//trial. Try 1 through 9
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c; //Put c for this cell

                            if (solve(board))
                                return true; //If it's the solution return true
                            else
                                board[i][j] = '.'; //Otherwise go back
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] != '.' && board[i][col] == c) return false; //check row
            if (board[row][i] != '.' && board[row][i] == c) return false; //check column
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.' &&
                    board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        // 一重循环
        for (int i = 0; i < board.length; i++) {
            HashSet<Character> rows = new HashSet<>();
            HashSet<Character> cols = new HashSet<>();
            HashSet<Character> boxs = new HashSet<>();
            for (int j = 0; j < board.length; j++) {
                // 加入行
                if (board[i][j] != '.' && !rows.add(board[i][j]))
                    return false;
                // 加入列
                if (board[j][i] != '.' && !cols.add(board[j][i]))
                    return false;

                // 加入boxs ,因为每次循环都是 9次，所以一次就找到一个box
                int RowIndex = 3 * (i / 3);
                int ColIndex = 3 * (i % 3);
                if (board[RowIndex + j / 3][ColIndex + j % 3] != '.' && !boxs.add(board[RowIndex + j / 3][ColIndex + j % 3]))
                    return false;

            }
        }
        return true;
    }


    public String countAndSay(int n) {
        // 当 n = 1时,直接返回 1
        if (n <= 1)
            return "1";

        // n > 1 ; n = 2
        String result = countAndSay(n - 1);
        Map<Character, Integer> count = new TreeMap<>();
        for (int i = 0; i < result.length(); i++) {
            Character key = result.charAt(i);
            if (count.containsKey(key)) {
                count.put(key, 1);
            } else {
                count.put(key, count.get(key) + 1);
            }
        }

        String r = "";
        for (Character key : count.keySet()) {
            r = "" + count.get(key) + key;
        }
        return r;
    }

    // 39.
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> result = new ArrayList<Integer>();
        // combination(candidates, target, 0, result, results);
        combination(results, result, candidates, target, 0);
        return results;
    }


    private void combination(List<List<Integer>> list, List<Integer> tempList, int[] candidates, int remain, int start) {
        if (remain == 0)
            list.add(new ArrayList<>(tempList));
        else if (remain > 0) {
            for (int i = start; i < candidates.length; i++) {
                tempList.add(candidates[i]);
                combination(list, tempList, candidates, remain - candidates[i], i);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    // 40.
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(results, tempList, candidates, target, 0);
        return results;
    }


    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remain, int start) {
        if (remain == 0)
            result.add(new ArrayList<>(tempList));
        else if (remain > 0) {
            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i] == candidates[i - 1]) continue;
                tempList.add(candidates[i]);
                backtrack(result, tempList, candidates, remain - candidates[i], i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public int firstMissingPositive(int[] A) {
        int n = A.length;
        if (n == 0)
            return 1;
        int k = partition(A) + 1;
        int temp = 0;
        int first_missing_Index = k;
        for (int i = 0; i < k; i++) {
            temp = Math.abs(A[i]);
            if (temp <= k)
                A[temp - 1] = (A[temp - 1] < 0) ? A[temp - 1] : -A[temp - 1];
        }
        for (int i = 0; i < k; i++) {
            if (A[i] > 0) {
                first_missing_Index = i;
                break;
            }
        }
        return first_missing_Index + 1;
    }

    public int partition(int[] A) {
        int n = A.length;
        int q = -1;
        for (int i = 0; i < n; i++) {
            if (A[i] > 0) {
                q++;
                swap(A, q, i);
            }
        }
        return q;
    }

    public void swap(int[] A, int i, int j) {
        if (i != j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }
    }

    public int trap(int[] height) {
        // 循环每一个位置，找到该位置的菜单最小值
        int size = height.length;
        int ans = 0;
        for (int i = 1; i < size - 1; i++) {
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                max_left = Math.max(max_left, height[j]);
            }
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                max_right = Math.max(max_right, height[j]);
            }
            ans += Math.min(max_left, max_right) - height[i];
        }
        return ans;
    }

    // 43.
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int multi = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j , p2 = i + j + 1; // ???
                int sum = multi + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }
        return "";
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        s.multiply("123", "45");

        int i = 0;
        int j = 1;
        if (i != 0 || i == 0 && j == 2)
            System.out.println("test");

        // System.out.println(s.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    // Solution s = new Solution();
    //     s.longestValidParentheses2(")(((((()())()()))()(()))(");
    //
    //     System.out.println(-Math.log(0.741) - Math.log(1.366) - Math.log(0.995));
    // char[][] t = new char[][]{
    //         {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
    //         {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
    //         {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
    //         {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
    //         {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
    //         {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
    //         {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
    //         {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
    //         {'.', '.', '.', '2', '7', '5', '9', '.', '.'}};
    //
    //     s.solveSudoku(t);
    //     for (int i = 0; i < t.length; i++) {
    //     System.out.println(Arrays.toString(t[i]));
    // }
    //     System.out.println(s.isValidSudoku(t));
    // ;
    // s.fourSum(new int[]{-1, 0, 1, 2, -1, -4}, -1);
    // s.longestCommonPrefix(new String[]{"flower", "flow", "flight"});
    // s.generateParenthesis3(3);
    // ListNode l1 = new ListNode(1);
    // ListNode l2 = new ListNode(2);
    // ListNode l3 = new ListNode(3);
    // ListNode l4 = new ListNode(4);
    // ListNode l5 = new ListNode(5);
    //
    //
    // l1.next = l2;
    // l2.next = l3;
    // l3.next = l4;
    // l4.next = l5;
    //
    // ListNode[] lists = new ListNode[]{null, l2};
    //
    // s.longestValidParentheses(")))((()))");
    // s.nextPermutation(new int[]{1,3,2});
    // s.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"});
    // s.divide(-2147483648 ,-1);
    // s.reverseKGroup(l1, 3);
    // s.strStr("hello", "ll");
    // s.mergeKList6(lists);
    // System.out.println(s.isValid("()[]{}"));
    // System.out.println('[' - ']');

    // In in = new In(args[0]);
    // Map<String, Set<String>> functions = new TreeMap<>();
    // while (!in.isEmpty()) {
    //     String functionId = in.readString();
    //     String key;
    //     if (functionId.startsWith("HS")) {
    //         key = functionId.substring(0, 4);
    //     } else {
    //         key = functionId.substring(0, 2);
    //     }
    //
    //     if (functions.get(key) == null) {
    //         Set<String> fs = new TreeSet<>();
    //         fs.add(functionId);
    //         functions.put(key, fs);
    //     } else {
    //         functions.get(key).add(functionId);
    //     }
    // }
    //
    // Workbook wb = new HSSFWorkbook();
    // try {
    //     FileOutputStream fileOut = new FileOutputStream("workbook.xls");
    //
    //     for (String key : functions.keySet()) {
    //         Sheet sheet = wb.createSheet(key);
    //         Set<String> fs = functions.get(key);
    //         int index = 1;
    //         for (String f : fs) {
    //             Row row = sheet.createRow(index++);
    //             Cell cell = row.createCell(1);
    //             cell.setCellValue(f);
    //         }
    //     }
    //     wb.write(fileOut);
    //     fileOut.close();
    // } catch (Exception e) {
    //
    // }

}
