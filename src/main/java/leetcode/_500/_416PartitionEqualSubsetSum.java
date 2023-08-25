package leetcode._500;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TransferQueue;

/**
 * <a href="https://leetcode.cn/problems/arithmetic-slices/">416. 分割等和子集</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Array
        ,
})
public class _416PartitionEqualSubsetSum {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.Official);
    }

    @Test
    public void case0() {
        Assertions.assertTrue(solution.canPartition(new int[]{1, 5, 11, 5}));
    }
    @Test
    public void case1() {
        Assertions.assertFalse(solution.canPartition(new int[] {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
            100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 99, 97}));
    }


    enum Policy {
        DP,
        Official,
        ;
    }


    public static class Official implements Solution {
        @Override
        public boolean canPartition(int[] nums) {
            int sum = Arrays.stream(nums).sum();
            if (sum % 2 != 0)
                return false;
            int length = nums.length;
            if (length < 2)
                return false;
            int max = Arrays.stream(nums).max().getAsInt();
            if (max * 2 > sum)
                return false;
            int target = sum / 2;
            
            boolean[][] dp = new boolean[length][target+1];// dp[i][j] 表示 取 nums数组的 [0,i]范围的数字和是否存在等于 j
            for (int i = 0; i < length; i++)
                dp[i][0] = true;
            dp[0][nums[0]] = true;
            
            for (int i = 1; i < length; i++) {
                int num = nums[i];
                for (int j = 1; j <= target; j++) {
                    if (num <= j) {
                        dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }


            return dp[length - 1][target];
        }
    }

    /**
     * 超出时间限制，不止，求不出解
     */
    public static class Dp implements Solution {


        @Override
        public boolean canPartition(int[] nums) {
            int sum = Arrays.stream(nums).sum();
            if ((sum & 1) == 1)
                return false;

            return carP(nums, sum / 2, 0, 0, 0);
        }

        private boolean carP(int[] nums,int max, int left, int right, int index) {
            int n = nums[index];
            if (Objects.equals(index + 1, nums.length)) {
                return Math.min(left, right) + n == Math.max(left, right);
            }

            if (left + n <= max) {
                if (carP(nums, max, left + n, right, index + 1))
                    return true;
            }
            if (right + n <= max) {
                if (carP(nums, max, left, right + n, index + 1))
                    return true;
            }

            return false;

        }

    }


    interface Solution {
        /**
         * 1 <= nums.length <= 200 <br>
         * 1 <= nums[i] <= 100
         *
         * @param nums
         * @return
         */
        boolean canPartition(int[] nums);
    }


    public static class Factory {

        private static final Map<Policy, Solution> solutionMap = new HashMap<>();

        static {
            solutionMap.put(Policy.DP, new Dp());
            solutionMap.put(Policy.Official, new Official());
        }

        public static Solution getSolution(Policy policy) {
            return solutionMap.get(policy);
        }

    }


}
