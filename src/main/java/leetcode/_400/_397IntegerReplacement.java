package leetcode._400;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/integer-replacement/">397. 整数替换</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Array
        ,
})
public class _397IntegerReplacement {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP4);
    }

    @Test
    public void case0() {
        Assertions.assertEquals(3, solution.integerReplacement(8));
    }
    @Test
    public void case1() {
        Assertions.assertEquals(4, solution.integerReplacement(7));
    }
    @Test
    public void case2() {
        Assertions.assertEquals(31, solution.integerReplacement(100000000));
    }
    @Test
    public void case3() {
        Assertions.assertEquals(32, solution.integerReplacement(2147483647));
    }


    enum Policy {
        DP,
        DP2,
        DP3,
        DP4,
        ;
    }


    /**
     * 大数递归，小数循环
     */
    public static class Dp4 implements Solution {


        @Override
        public int integerReplacement(int n) {
            return _integerReplacement(n);
        }

        private int _integerReplacement(long n){
            if (n == 1)
                return 0;

            if (n < (1 << 16)) {
                try {
                    int[] dp = new int[(int) (n + 2)];
                    dp[1] = 0;
                    dp[2] = 1;

                    for (int i = 3; i <= n; i += 2) {// i为基数，一次跳2格
                        dp[i + 1] = dp[(i + 1) / 2] + 1;
                        dp[i] = Math.min(dp[i - 1], dp[i + 1]) + 1;
                    }

                    return dp[(int) n];
                }catch (Exception e){
                    System.out.println(n);
                }

            }

            if ((n & 1) == 0)
                return _integerReplacement(n / 2) + 1;
            return
            Math.min(_integerReplacement(n - 1), _integerReplacement(n + 1)) + 1;
        }
    }

    /**
     * 长度还是受限制
     */
    public static class Dp3 implements Solution {


        @Override
        public int integerReplacement(int n) {
            List<Integer> dp = new ArrayList<>(n + 1);
            dp.add(0);
            dp.add(0);
            dp.add(1);

            for (int i = 3; i <= n; i+=2) {// i为基数，一次跳2格
                dp.add(0);
                dp.add(0);
                dp.set(i + 1, dp.get((i + 1) / 2) + 1);
                dp.set(i, Math.min(dp.get(i + 1), dp.get(i - 1)) + 1);
            }

            return dp.get(n);
        }
    }

    /**
     * 栈溢出，芙拉
     */
    public static class Dp2 implements Solution {


        @Override
        public int integerReplacement(int n) {
            return _integerReplacement(n);
        }

        private int _integerReplacement(int n){
            if (n == 1)
                return 0;
            if ((n & 1) == 0)
                return _integerReplacement(n / 2) + 1;
            return Math.min(_integerReplacement(n - 1), _integerReplacement(n + 1)) + 1;
        }
    }

    /**
     * 超出内存限制
     */
    public static class Dp implements Solution {


        @Override
        public int integerReplacement(int n) {
            int[] dp = new int[n + 2];
            dp[1] = 0;
            dp[2] = 1;

            for (int i = 3; i <= n; i += 2) {// i为基数，一次跳2格
                dp[i + 1] = dp[(i + 1) / 2] + 1;
                dp[i] = Math.min(dp[i - 1], dp[i + 1]) + 1;
            }

            return dp[n];
        }
    }


    interface Solution {
        /**
         * <pre>1 <= n <= 2<sup>31</sup> - 1</pre>
         * @param n
         * @return
         */
        int integerReplacement(int n);
    }


    public static class Factory {

        private static final Map<Policy, Solution> solutionMap = new HashMap<>();

        static {
            solutionMap.put(Policy.DP, new Dp());
            solutionMap.put(Policy.DP2, new Dp2());
            solutionMap.put(Policy.DP3, new Dp3());
            solutionMap.put(Policy.DP4, new Dp4());
        }

        public static Solution getSolution(Policy policy) {
            return solutionMap.get(policy);
        }

    }


}
