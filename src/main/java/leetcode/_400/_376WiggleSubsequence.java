package leetcode._400;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/wiggle-subsequence/">376. 摆动序列</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Array,
})
public class _376WiggleSubsequence {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }

    @Test
    public void case0() {
        Assertions.assertEquals(6, solution.wiggleMaxLength(new int[]{1, 7, 4, 9, 2, 5}));
    }

    @Test
    public void case1() {
        Assertions.assertEquals(7, solution.wiggleMaxLength(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8}));
    }


    enum Policy {
        DP,
        ;
    }

    public static class Dp implements Solution {

        @Override
        public int wiggleMaxLength(int[] nums) {
            int length = nums.length;
            if (length == 1)
                return 1;
            int maxLength = 0;
            int[] dp = new int[length];
            int[] flag = new int[length];// 0-初始 1差为正数 -1差为负数
            dp[0] = 1;
            for (int i = 1; i < length; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[i] == nums[j])
                        continue;
                    if (flag[j] == 0 && dp[j] + 1 > dp[i]) {
                        flag[i] = nums[i] > nums[j] ? 1 : -1;
                        dp[i] = dp[j] + 1;
                    } else if (flag[j] == -1 && nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                        flag[i] = 1;
                        dp[i] = dp[j] + 1;
                    } else if (flag[j] == 1 && nums[i] < nums[j] && dp[j] + 1 > dp[i]) {
                        flag[i] = -1;
                        dp[i] = dp[j] + 1;
                    }
                }
                maxLength = Math.max(maxLength, dp[i]);
            }

            return maxLength;
        }
    }


    interface Solution {
        /**
         * 1 <= nums.length <= 1000
         * 0 <= nums[i] <= 1000
         *
         * @param nums
         * @return
         */
        int wiggleMaxLength(int[] nums);
    }


    public static class Factory {

        private static final Map<Policy, Solution> solutionMap = new HashMap<>();

        static {
            solutionMap.put(Policy.DP, new Dp());
        }

        public static Solution getSolution(Policy policy) {
            return solutionMap.get(policy);
        }

    }


}
