package leetcode._500;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/arithmetic-slices/">413. 等差数列划分</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Array
        ,
})
public class _413ArithmeticSlices {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }

    @Test
    public void case0() {
        Assertions.assertEquals(3, solution.numberOfArithmeticSlices(new int[]{1,2,3,4}));
    }


    enum Policy {
        DP,
        ;
    }


    public static class Dp implements Solution {


        @Override
        public int numberOfArithmeticSlices(int[] nums) {
            int[] dp = new int[nums.length];

            int ret = 0;
            for (int i = 2; i < nums.length; i++) {
                if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2])
                    dp[i] += dp[i - 1] + 1;
                ret += dp[i];
            }

            return ret;
        }
    }


    interface Solution {
        /**
         * 1 <= nums.length <= 5000 <br>
         * -1000 <= nums[i] <= 1000
         * @param nums
         * @return
         */
        int numberOfArithmeticSlices(int[] nums);
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
