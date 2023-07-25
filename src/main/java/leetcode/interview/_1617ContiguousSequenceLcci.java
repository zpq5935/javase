package leetcode.interview;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * <a href="https://leetcode.cn/problems/contiguous-sequence-lcci/">面试题 16.17. 连续数列</a>
 */

@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
})
public class _1617ContiguousSequenceLcci {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.Sol1);
    }


    @Test
    public void case1() {
        Assertions.assertEquals(6, solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }


    enum Policy {
        Sol1,
        ;
    }


    public static class Sol1 implements Solution {


        @Override
        public int maxSubArray(int[] nums) {
            int[] f = new int[nums.length];// 以i为子序列末尾的子系列最大值
            int max = nums[0];
            f[0] = nums[0];

            for (int i = 1; i < nums.length; i++) {
                f[i] = Math.max(nums[i], f[i - 1] + nums[i]);
                max = Math.max(max, f[i]);
            }

            return max;
        }
    }


    interface Solution {
        /**
         * 给定一个整数数组，找出总和最大的连续数列，并返回总和
         * @param nums 完整序列
         * @return 最大连续序列之和
         */
        int maxSubArray(int[] nums);
    }


    public static class Factory {

        private static final Map<Policy, Solution> solutionMap = new HashMap<>();

        static {
            solutionMap.put(Policy.Sol1, new Sol1());
        }

        public static Solution getSolution(Policy policy) {
            return solutionMap.get(policy);
        }

    }


}
