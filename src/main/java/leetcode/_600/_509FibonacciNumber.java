package leetcode._600;

import leetcode.LeetCodeConstant;

/**
 * <a href="https://leetcode.cn/problems/fibonacci-number/">509. 斐波那契数</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.Dp,
        LeetCodeConstant.TagEnum.Math,
        LeetCodeConstant.TagEnum.MemorySearch,
})
public class _509FibonacciNumber {


    public static void main(String[] args) {
        Solution solution = Factory.getSolution(Policy.Sol1);
        System.out.println(solution.fib(3));
    }


    enum Policy {
        Sol1,
        ;
    }

    public static class Sol1 implements Solution {


        @Override
        public int fib(int n) {
            if (n < 2)
                return n == 0 ? 0 : 1;

            int[] nums = new int[n + 1];
            nums[0] = 0;
            nums[1] = 1;
            for (int i = 2; i <= n; i++) {
                nums[i] = nums[i - 1] + nums[i - 2];
            }

            return nums[n];
        }
    }

    interface Solution {
        /**
         * 0 <= n <= 30
         */
        int fib(int n);
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

