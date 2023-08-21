package leetcode._300;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/perfect-squares/">279. 完全平方数</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Array,
})
public class _279PerfectSquares {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }

    @Test
    public void case0() {
        Assertions.assertEquals(2, solution.numSquares(13));
    }
    @Test
    public void case1() {
        Assertions.assertEquals(3, solution.numSquares(12));
    }

    enum Policy {
        Greedy,
        DP,
        ;
    }

    public static class Dp implements Solution {
        @Override
        public int numSquares(int n) {
            int[] dp = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                int min = Integer.MAX_VALUE;
                for (int j = 1; j * j <= i; j++) {
                    min = Math.min(min, dp[i - j * j]);
                }
                dp[i] = min + 1;
            }

            return dp[n];
        }
    }



    /**
     * 贪心-求解有误
     */
    public static class Greedy implements Solution {


        @Override
        public int numSquares(int n) {
            int count = 0;
            int sup = 100;
            boolean flag = true;

            while (flag){
                for (int i = sup; i >= 1; i--) {
                    int pow = i * i;
                    if (pow == n) {
                        count++;
                        flag = false;
                        break;
                    } else if (pow < n) {
                        sup = i;
                        count++;
                        n -= pow;
                        break;
                    }
                }
            }

            return count;
        }
    }


    interface Solution {
        /**
         * <pre>1 <= n <= 10<sup>4</sup></pre>
         * @param n
         * @return
         */
        int numSquares(int n);
    }


    public static class Factory {

        private static final Map<Policy, Solution> solutionMap = new HashMap<>();

        static {
            solutionMap.put(Policy.Greedy, new Greedy());
            solutionMap.put(Policy.DP, new Dp());
        }

        public static Solution getSolution(Policy policy) {
            return solutionMap.get(policy);
        }

    }


}
