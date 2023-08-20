package leetcode._400;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/coin-change/">322. 零钱兑换</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Array,
})
public class _322CoiChange {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }


    @Test
    public void case0() {
        Assertions.assertEquals(3, solution.coinChange(new int[]{1, 2, 5}, 11));
    }


    enum Policy {
        DP,
        DP2,
        ;
    }

    /**
     * 超出内存限制，甚至不知道能不能解答全部
     */
    public static class DP implements Solution {


        @Override
        public int coinChange(int[] coins, int amount) {
            if (amount == 0)
                return 0;

            int[] dp = new int[amount + 1];
            for (int i = 1; i <= amount; i++) {
                int inf = Integer.MAX_VALUE;
                for (int coin : coins) {
                    int reduce = i - coin;
                    if (reduce >= 0 && dp[reduce] >= 0) {
                        inf = Math.min(inf, dp[reduce] + 1);
                    }
                }
                dp[i] = inf == Integer.MAX_VALUE ? -1 : inf;

            }


            return dp[amount] != 0 ? dp[amount] : -1;
        }
    }


    interface Solution {
        /**
         * 1 <= coins.length <= 12
         * <pre>1 <= coins[i] <= 2<sup>31</sup> - 1</pre>
         * <pre>0 <= amount <= 10<sup>4</sup></pre>
         *
         * @param coins
         * @param amount
         * @return 最少的硬币数，若无结果，返回-1
         */
        int coinChange(int[] coins, int amount);
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

