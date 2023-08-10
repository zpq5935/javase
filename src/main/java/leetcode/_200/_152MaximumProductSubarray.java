package leetcode._200;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/maximum-product-subarray/description/">139. 乘积最大子数组</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Array,
})
public class _152MaximumProductSubarray {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }


    @Test
    public void case0() {
        Assertions.assertEquals(6, solution.maxProduct(new int[]{2, 3, -2, 4}));
    }
    @Test
    public void case1() {
        Assertions.assertEquals(-2, solution.maxProduct(new int[]{-2}));
    }

    @Test
    public void case2() {
        Assertions.assertEquals(3, solution.maxProduct(new int[]{-3,-1,-1}));
    }
    @Test
    public void case3() {
        Assertions.assertEquals(2, solution.maxProduct(new int[]{0, 2}));
    }






    enum Policy {
        DP,
        DP2,
        ;
    }

    /**
     * 针对 {@link DP} 尝试采用滚动数组优化
     */
    public static class DP2 implements Solution {
        @Override
        public int maxProduct(int[] nums) {
            int max = nums[0];
            int[] products = new int[nums.length];// 从下标到此处循环 末尾 的乘积
            products[0] = nums[0];

            for (int i = 1; i < nums.length; i++) {// 末尾
                for (int j = i; j >= 0; j--) {// 左侧尾
                    if (i == j) {
                        products[j] = nums[i];
                        max = Math.max(products[j], max);
                    } else {
                        products[j] = products[j] * nums[i];
                        max = Math.max(products[j], max);
                    }
                }
            }
            return max;
        }
    }

    /**
     * 超出内存限制，甚至不知道能不能解答全部
     */
    public static class DP implements Solution {



        @Override
        public int maxProduct(int[] nums) {
            int max = nums[0];
            int[][] products = new int[nums.length + 1][nums.length + 1];// 从i到j位的乘积
            products[0][0] = nums[0];

            for (int i = 1; i < nums.length; i++) {// 末尾
                for (int j = i; j >= 0; j--) {// 左侧尾
                    if (i == j) {
                        products[j][i] = nums[i];
                        max = Math.max(products[j][i], max);
                    } else {
                        products[j][i] = products[j][i - 1] * nums[i];
                        max = Math.max(products[j][i], max);
                    }
                }
            }
            return max;
        }

    }


    interface Solution {
        /**
         *  <pre>1 <= nums.length <= 2 *10<sup>4</sup></pre>
         * -10 <= nums[i] <= 10
         * nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数
         * @param nums
         * @return
         */
        int maxProduct(int[] nums);
    }


    public static class Factory {

        private static final Map<Policy, Solution> solutionMap = new HashMap<>();

        static {
            solutionMap.put(Policy.DP, new DP());
            solutionMap.put(Policy.DP2, new DP2());
        }

        public static Solution getSolution(Policy policy) {
            return solutionMap.get(policy);
        }

    }


}
