package leetcode._200;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/">122. 买卖股票的最佳时机 II</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Array,
})
public class _122BestTimeToBuyAndSellStockii {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }


    @Test
    public void case0() {
        Assertions.assertEquals(7, solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }





    enum Policy {
        DP,
        ;
    }

    public static class DP implements Solution {

        @Override
        public int maxProfit(int[] prices) {

            int profit = 0;
            Integer haveIndex= null;
            for (int i = 0; i < prices.length; i++) {
                if (haveIndex == null) {
                    if (i + 1 < prices.length && prices[i + 1] > prices[i]) {
                        haveIndex = i;
                    }
                }else {
                    if (i + 1 >= prices.length || prices[i + 1] < prices[i]) {
                        profit += prices[i] - prices[haveIndex];
                        haveIndex = null;
                    }
                }
            }


            return profit;
        }
    }


    interface Solution {
        /**
         * 1 <= prices.length <= 3 * 104
         * 0 <= prices[i] <= 104
         * @param prices
         * @return
         */
        int maxProfit(int[] prices);
    }


    public static class Factory {

        private static final Map<Policy, Solution> solutionMap = new HashMap<>();

        static {
            solutionMap.put(Policy.DP, new DP());
        }

        public static Solution getSolution(Policy policy) {
            return solutionMap.get(policy);
        }

    }


}
