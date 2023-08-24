package leetcode._400;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/rotate-function/description/">396. 旋转函数</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Array
        ,
})
public class _396RotateFunction {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }

    @Test
    public void case0() {
        Assertions.assertEquals(26, solution.maxRotateFunction(new int[]{4,3,2,6}));
    }


    enum Policy {
        DP,
        ;
    }


    public static class Dp implements Solution {


        @Override
        public int maxRotateFunction(int[] nums) {
            int sum = Arrays.stream(nums).sum();
            int length = nums.length;
            int max = 0;
            for (int i = 0; i < length; i++) {// F[0]
                max += i * nums[i];
            }

            int preSum = max;
            for (int i = length - 1; i > 0; i--) {
                preSum = preSum + sum - length  * nums[i];// F[i] -> F[i+1] 的转移方程
                max = Math.max(max, preSum);
            }

            return max;
        }
    }


    interface Solution {
        /**
         * n == nums.length
         * 1 <= n <= 105
         * -100 <= nums[i] <= 100
         *
         * @param nums
         * @return
         */
        int maxRotateFunction(int[] nums);
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
