package leetcode._300;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/maximal-square/">221. 最大正方形</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Array,
})
public class _221MaximalSquare {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }


    @Test
    public void case0() {
        Assertions.assertEquals(4, solution.maximalSquare(new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        }));
    }
    @Test
    public void case1() {
        Assertions.assertEquals(1, solution.maximalSquare(new char[][]{
                {'0','1'},{'1','0'},
        }));
    }

    @Test
    public void case2() {
        Assertions.assertEquals(1, solution.maximalSquare(new char[][]{
                {'1','0'},
        }));
    }






    enum Policy {
        DP,
        ;
    }


    public static class DP implements Solution {


        int max = 0;
        @Override
        public int maximalSquare(char[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;

            for (int i = 0; i < m; i++) {
                if (m - i <= max) {
                    continue;
                }
                for (int j = 0; j < n; j++) {
                    if (n - j <= max) {
                        continue;
                    }
                    max = Math.max(max, maximalSquare(matrix, i, j));
                }
            }

            return max * max;
        }

        public int maximalSquare(char[][] matrix, int i, int j) {
            if ('0' == matrix[i][j])
                return 0;

            int length = 1;
            do {
                if (allOne(matrix, i + length, j, true, length) && allOne(matrix, i, j + length, false, length))
                    length++;
                else return length;
            } while (true);
        }

        private boolean allOne(char[][] matrix, int x, int y, boolean vertical, int length) {
            if (vertical) {
                if (y + length >= matrix[0].length || x >= matrix.length) return false;
                for (int j = 0; j <= length; j++) {
                    if ('0' == matrix[x][y + j])
                        return false;
                }
            } else {
                if (x + length >= matrix.length || y >= matrix[0].length) return false;
                for (int i = 0; i <= length; i++) {
                    if ('0' == matrix[x + i][y])
                        return false;
                }
            }
            return true;
        }

    }


    interface Solution {

        /**
         * m == matrix.length
         * n == matrix[i].length
         * 1 <= m, n <= 300
         * matrix[i][j] 为 '0' 或 '1'
         *
         * @param matrix
         * @return
         */
        int maximalSquare(char[][] matrix);
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
