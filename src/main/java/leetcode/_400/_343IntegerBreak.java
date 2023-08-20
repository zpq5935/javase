package leetcode._400;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/integer-break/">343. 整数拆分</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Array,
})
public class _343IntegerBreak {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }


    @Test
    public void case0() {
        Assertions.assertEquals(1, solution.integerBreak(2));
    }
    @Test
    public void case1() {
        Assertions.assertEquals(36, solution.integerBreak(10));
    }


    enum Policy {
        DP,
        ;
    }

    public static class DP implements Solution {

        @Override
        public int integerBreak(int n) {
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 1;

            for (int i = 1; i < n + 1; ++i) {
                for (int j = 1; j < i; j++) {
                    dp[i] = Math.max(Math.max(dp[j], j) * Math.max(dp[i - j], i - j), dp[i]);
                }
            }

            return dp[n];
        }
    }


    interface Solution {

        /**
         * 2 <= n <= 58
         * @param n
         * @return
         */
        int integerBreak(int n);
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

