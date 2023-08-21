package leetcode._200;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/house-robber/">198. 打家劫舍</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Array,
})
public class _192HouseRobber {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }


    @Test
    public void case0() {
        Assertions.assertEquals(4, solution.rob(new int[]{1, 2, 3, 1}));
    }






    enum Policy {
        DP,
        ;
    }


    public static class DP implements Solution {


        /**
         * 1 <= nums.length <= 100
         * 0 <= nums[i] <= 400
         * @param nums
         * @return
         */
        @Override
        public int rob(int[] nums) {
            int length = nums.length;
            int[] dp = new int[length];
            dp[0] = nums[0];
            if (length >= 2) dp[1] = Math.max(nums[0], nums[1]);

            for (int i = 2; i < length; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }


            return dp[length -1];
        }
    }


    interface Solution {
        int rob(int[] nums);
    }


    public static class Factory {

        private static final Map<Policy, Solution> solutionMap = new HashMap<>();

        static {
            solutionMap.put(Policy.DP, new DP());
        }

        public static Solution getSolution(Policy policy) {
            return solutionMap.get(policy);
        }

    }


}
