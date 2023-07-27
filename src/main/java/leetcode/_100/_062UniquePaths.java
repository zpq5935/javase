package leetcode._100;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/unique-paths/">62. 不同路径</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
})
public class _062UniquePaths {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP2);
    }


    @Test
    public void case1() {
        Assertions.assertEquals(6, solution.uniquePaths(3,3));
    }
    @Test
    public void case2() {
        Assertions.assertEquals(28, solution.uniquePaths(7,3));
    }



    enum Policy {
        DP,
        DP2,
        ;
    }

    public static class DP implements Solution {

        Integer[][] cache = new Integer[101][101];

        @Override
        public int uniquePaths(int m, int n) {
            if (m == 1 || n == 1)
                return 1;
            return cachePlans(m - 1, n) + cachePlans(m, n - 1);
        }

        public int cachePlans(int m, int n) {
            if (m == 0 || n == 0)
                return 0;
            if (m == 1 || n == 1)
                return 1;


            Integer plan = cache[m][n] != null ? cache[m][n] : cache[n][m];
            if (plan != null)
                return plan;

            plan = cachePlans(m - 1, n) + cachePlans(m, n - 1);
            cache[m][n] = plan;
            return plan;
        }
    }
    public static class DP2 implements Solution {

        Integer[][] dp = new Integer[100][100];// [0][0] 到达 [下标+1][下标+1] 的方案数

        @Override
        public int uniquePaths(int m, int n) {
            dp[0][0] = 1;


            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 || j == 0)
                        dp[i][j] = 1;
                    else {
                        dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                    }
                }
            }

            return dp[m - 1][n - 1];

        }

    }


    interface Solution {
        /**
         * 1 <= m, n <= 100
         * 题目数据保证答案小于等于  <pre>2 *10<sup>9</sup></pre>
         */
        int uniquePaths(int m, int n);
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
