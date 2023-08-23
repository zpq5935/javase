package leetcode._400;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/combination-sum-iv/">377. 组合总和 Ⅳ</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
})
public class _377CombinationSumIv {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.Official);
    }

    @Test
    public void case0() {
        Assertions.assertEquals(7, solution.combinationSum4(new int[]{1, 2, 3}, 4));
    }
    @Test
    public void case1() {
        Assertions.assertEquals(1132436852, solution.combinationSum4(new int[]{2,1,3}, 35));
    }


    enum Policy {
        DP,
        Official,
        ;
    }

    public static class OfficialDp implements Solution {

        @Override
        public int combinationSum4(int[] nums, int target) {
            int[] dp = new int[target + 1];
            dp[0] = 1;

            for (int i = 1; i <= target; i++) {
                for (int num : nums) {
                    if (i >= num) dp[i] += dp[i - num];
                }
            }
            return dp[target];
        }
    }

    public static class Dp implements Solution {


        Integer[][] cache = new Integer[1000][1000];
        @Override
        public int combinationSum4(int[] nums, int target) {
            Arrays.sort(nums);
            return _combinationSum4(nums, target, -1);
        }

        public int _combinationSum4(int[] nums,int target,int excludeIndex){
            if (excludeIndex >= 0 && cache[target][excludeIndex] != null)
                return cache[target][excludeIndex];
            int ret = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i == excludeIndex)
                    continue;
                int count = target / nums[i];
                for (int j = 1; j <= count; j++) {
                    int remain = target - j * nums[i];
                    if (remain == 0)
                        ret += 1;
                    else
                        ret += _combinationSum4(nums, remain, i);
                }
            }
            if (excludeIndex >= 0) cache[target][excludeIndex] = ret;
            return ret;
        }
    }


    interface Solution {
        /**
         * 1 <= nums.length <= 200 <br>
         * 1 <= nums[i] <= 1000 <br>
         * nums 中的所有元素 互不相同 <br>
         * 1 <= target <= 1000 <br>
         * @param nums
         * @param target
         * @return
         */
        int combinationSum4(int[] nums, int target);
    }


    public static class Factory {

        private static final Map<Policy, Solution> solutionMap = new HashMap<>();

        static {
            solutionMap.put(Policy.DP, new Dp());
            solutionMap.put(Policy.Official, new OfficialDp());
        }

        public static Solution getSolution(Policy policy) {
            return solutionMap.get(policy);
        }

    }


}
