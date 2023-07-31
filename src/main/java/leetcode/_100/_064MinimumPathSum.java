package leetcode._100;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/minimum-path-sum/">64. 最小路径和</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
})
public class _064MinimumPathSum {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }


    @Test
    public void case0() {
        Assertions.assertEquals(7, solution.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
    }





    enum Policy {
        DP,
        ;
    }

    public static class DP implements Solution {


        @Override
        public int minPathSum(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int[][] dp = new int[m][n];

            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++) {
                    if (i == 0 && j == 0) dp[i][j] = grid[i][j];
                    else if (i == 0) dp[i][j] = grid[i][j] + dp[i][j - 1];
                    else if (j == 0) dp[i][j] = grid[i][j] + dp[i - 1][j];
                    else dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
                }

            return dp[m - 1][n - 1];
        }
    }



    interface Solution {
        /**
         * m == grid.length
         * n == grid[i].length
         * 1 <= m, n <= 200
         * 0 <= grid[i][j] <= 200
         * @param grid 路径及其数字
         * @return 最小路径和
         */
        int minPathSum(int[][] grid);
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
