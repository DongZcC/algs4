package week05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-04-20<br>
 */
public class Solution2 {

    // 44. wildcard Matching 占位符匹配
    /*
    这里又是一道动态规划DP的问题，
    这种类型的问题，都是现在这个点，是否匹配/符合， 都依赖前一个
    因此，这里就初始化一个dp数组
    * */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        int s_len = s.length();
        int p_len = p.length();

        boolean[][] dp = new boolean[s_len + 1][p_len + 1];
        dp[0][0] = true;

        for (int i = 0; i < p_len; i++) {
            if (p.charAt(i) == '*') dp[0][i + 1] = dp[0][i];
        }

        for (int i = 0; i < s_len; i++) {
            for (int j = 0; j < p_len; j++) {
                if (p.charAt(j) == '?')
                    dp[i + 1][j + 1] = dp[i][j];
                if (p.charAt(j) == s.charAt(i))
                    dp[i + 1][j + 1] = dp[i][j];
                if (p.charAt(j) == '*')
                    dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j + 1];
            }
        }

        return dp[s_len][p_len];
    }

    // 45. Jump Game 2
    public int jump(int[] nums) {
        int step_count = 0;
        int last_jump_max = 0;
        int current_jump_max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            current_jump_max = Math.max(current_jump_max, i + nums[i]);
            if (i == last_jump_max) {
                step_count++;
                last_jump_max = current_jump_max;
            }
        }
        return step_count;
    }

    // 46.
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<Integer>(), nums);
        return result;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] candidates) {
        if (tempList.size() == candidates.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < candidates.length; i++) {
                if (tempList.contains(candidates[i])) continue; // element already exists, skip
                tempList.add(candidates[i]);
                backtrack(list, tempList, candidates);
                tempList.remove(tempList.size() - 1);
            }
        }
    }


    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 排序，来搞定去重的问题
        Arrays.sort(nums);
        backtrack(result, new ArrayList<Integer>(), nums);
        return result;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
        // 退出逻辑是一样的
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            // 现在就是这个加数的逻辑
            for (int i = 0; i < nums.length; i++) {
                if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
                used[i] = true;
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution2 s2 = new Solution2();
        // s2.isMatch("adceb", "*a*b");
        s2.permute(new int[]{1, 3, 2});
    }
}
