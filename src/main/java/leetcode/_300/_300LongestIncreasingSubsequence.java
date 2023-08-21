package leetcode._300;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/longest-increasing-subsequence/">300. 最长递增子序列</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Array,
})
public class _300LongestIncreasingSubsequence {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }

    @Test
    public void case0() {
        // 0 1 97 98 99 50 51 52 53 54
        Assertions.assertEquals(4, solution.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
    }

    enum Policy {
        DP,
        ;
    }

    public static class Dp implements Solution {
        @Override
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            int max = 1;


            for (int i = 0; i < nums.length; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if(nums[j]<nums[i]){
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                max = Math.max(dp[i], max);
            }

            return max;
        }
    }





    interface Solution {
        /**
         * 1 <= nums.length <= 2500
         * -104 <= nums[i] <= 104
         */
        int lengthOfLIS(int[] nums);
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
