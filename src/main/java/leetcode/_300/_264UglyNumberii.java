package leetcode._300;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/ugly-number-ii/">264. 丑数 II</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Array,
})
public class _264UglyNumberii {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP3);
    }


    @Test
    public void case0() {
        Assertions.assertEquals(12, solution.nthUglyNumber(10));
    }
    @Test
    public void case1() {
        Assertions.assertEquals(402653184, solution.nthUglyNumber(1352));
    }







    enum Policy {
        DP,
        DP2,
        DP3,
        ;
    }

    public static class DP3 implements Solution {
        @Override
        public int nthUglyNumber(int n) {
            int[] dp = new int[n];
            dp[0] = 1;

            int p2 = 0, p3 = 0, p5 = 0;
            for (int i = 1; i < n; i++) {
                int n2 = dp[p2] * 2, n3 = dp[p3] * 3, n5 = dp[p5] * 5;
                int min = Math.min(Math.min(n2, n3), n5);
                dp[i] = min;
                if (n2 == min) {
                    p2++;
                }
                if (n3 == min) {
                    p3++;
                }
                if (n5 == min) {
                    p5++;
                }
            }

            return dp[n - 1];
        }
    }

    /**
     * 也不行 存储过多，没真正理解题目
     */
    public static class DP2 implements Solution {

        Set<Integer> uglyNumbers = new HashSet<>();
        @Override
        public int nthUglyNumber(int n) {
            uglyNumbers.add(1);
            uglyNumbers.add(2);
            uglyNumbers.add(3);
            uglyNumbers.add(5);

            int m = 1;
            int i = 0;
            while (i < n) {
                if (isUgly(m++)) {
                    uglyNumbers.add(m - 1);
                    i++;
                }
            }

            return m - 1;
        }

        public boolean isUgly(int n) {
            if(uglyNumbers.contains(n))
                return true;
            if (n % 2 == 0) {
                return uglyNumbers.contains(n / 2);
            }
            if (n % 3 == 0) {
                return uglyNumbers.contains(n / 3);
            }
            if (n % 5 == 0) {
                return uglyNumbers.contains(n / 5);
            }
            return false;
        }
    }


    /**
     * 无递归思想
     */
    public static class DP implements Solution {


        @Override
        public int nthUglyNumber(int n) {
            int m = 1;
            int i = 0;
            while (i < n) {
                if (isUgly(m++)) {
                    i++;
                }
            }

            return m - 1;
        }

        public boolean isUgly(int n) {
            boolean over;
            do {
                over = true;
                if (n % 2 == 0) {
                    over = false;
                    n = n / 2;
                }
                if (n % 3 == 0) {
                    over = false;
                    n = n / 3;
                }
                if (n % 5 == 0) {
                    over = false;
                    n = n / 5;
                }
            } while (!over);
            return n == 1;
        }
    }


    interface Solution {
        /**
         * 1 <= n <= 1690
         */
        int nthUglyNumber(int n);
    }


    public static class Factory {

        private static final Map<Policy, Solution> solutionMap = new HashMap<>();

        static {
            solutionMap.put(Policy.DP, new DP());
            solutionMap.put(Policy.DP2, new DP2());
            solutionMap.put(Policy.DP3, new DP3());
        }

        public static Solution getSolution(Policy policy) {
            return solutionMap.get(policy);
        }

    }


}
