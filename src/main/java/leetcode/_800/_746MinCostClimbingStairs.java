package leetcode._800;

import leetcode.LeetCodeConstant;


/**
 * <a href="https://leetcode.cn/problems/min-cost-climbing-stairs/">746. 使用最小花费爬楼梯</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.Array,
        LeetCodeConstant.TagEnum.DynamicPrograming,
})
public class _746MinCostClimbingStairs {



    public static void main(String[] args) {
        Solution solution = Factory.getSolution(Policy.Sol1);
        System.out.println(solution.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }



    enum Policy {
        Sol1,
        ;
    }

    public static class Sol1 implements Solution {

        @Override
        public int minCostClimbingStairs(int[] cost) {
            int[] dp = new int[cost.length + 1];
            dp[0] = 0;
            dp[1] = 0;
            for (int i = 2; i <= cost.length; i++) {
                dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
            }

            return dp[cost.length];
        }
    }

    interface Solution {
        /**
         * 2 <= cost.length <= 1000
         * 0 <= cost[i] <= 999
         */
        int minCostClimbingStairs(int[] cost);
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
