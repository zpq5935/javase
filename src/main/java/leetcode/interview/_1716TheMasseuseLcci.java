package leetcode.interview;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * <a href="https://leetcode.cn/problems/the-masseuse-lcci/">面试题 17.16. 按摩师</a>
 */

@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
})
public class _1716TheMasseuseLcci {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.Sol1);
    }


    @Test
    public void case1() {
        Assertions.assertEquals(4, solution.massage(new int[]{1, 2, 3, 1}));
    }

    @Test
    public void case2() {
        Assertions.assertEquals(12, solution.massage(new int[]{2, 1, 4, 5, 3, 1, 1, 3}));
    }
    @Test
    public void case3() {
        Assertions.assertEquals(4, solution.massage(new int[]{2, 1, 1, 2}));
    }


    enum Policy {
        Sol1,
        ;
    }


    public static class Sol1 implements Solution {


        @Override
        public int massage(int[] nums) {
            if (nums.length == 0)
                return 0;
            if (nums.length == 1) {
                return nums[0];
            }
            if (nums.length == 2) {
                return Math.max(nums[0], nums[1]);
            }
            int[] f = new int[nums.length + 2];// 表示前i位数字的不存在连续最大和
            f[0] = nums[0];
            f[1] = Math.max(nums[0], nums[1]);
            int max = Math.max(f[0], f[1]);

            for (int i = 2; i < nums.length; i++) {
                f[i] = f[i - 2] + nums[i];
                f[i] = Math.max(f[i - 1], f[i]);

                max = Math.max(max, f[i]);
            }

            return max;
        }
    }


    interface Solution {
        int massage(int[] nums);
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
