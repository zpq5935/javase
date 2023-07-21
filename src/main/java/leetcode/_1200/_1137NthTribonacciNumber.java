package leetcode._1200;

import leetcode.LeetCodeConstant;


/**
 * <a href="https://leetcode.cn/problems/n-th-tribonacci-number/">1137. 第 N 个泰波那契数</a>
 */

@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.Array,
        LeetCodeConstant.TagEnum.DynamicPrograming,
})
public class _1137NthTribonacciNumber {


    public static void main(String[] args) {
        Solution solution = Factory.getSolution(Policy.Sol1);
        System.out.println(solution.tribonacci(4));
    }


    enum Policy {
        Sol1,
        Math,
        ;
    }

    public static class Sol1 implements Solution {

        @Override
        public int tribonacci(int n) {
            int[] nums = new int[40];
            nums[0] = 0;
            nums[1] = 1;
            nums[2] = 1;

            for (int i = 3; i <= n; i++) {
                nums[i] = nums[i - 1] + nums[i - 2] + nums[i - 3];
            }

            return nums[n];
        }
    }

    interface Solution {
        int tribonacci(int n);
    }


    public static class Factory {

        public static Solution getSolution(Policy policy) {
            switch (policy) {
                case Sol1:
                    return new Sol1();
                default:
                    throw new UnsupportedOperationException();
            }
        }

    }


}
