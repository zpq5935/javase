package leetcode._400;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/guess-number-higher-or-lower-ii/">375. 猜数字大小 II</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
})
public class _375GuessNumberHigherOrLowerIi {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }

    @Test
    public void case0() {
        Assertions.assertEquals(0, solution.getMoneyAmount(1));
    }

    @Test
    public void case1() {
        Assertions.assertEquals(1, solution.getMoneyAmount(2));
    }
    @Test
    public void case2() {
        Assertions.assertEquals(16, solution.getMoneyAmount(10));
    }

    enum Policy {
        DP,
        ;
    }

    public static class Dp implements Solution {

        int[][] budget;

        @Override
        public int getMoneyAmount(int n) {
            budget = new int[n + 1][n + 1];// 从数字 i到j的最糟糕需要现金数
            return getMoneyAmount2(1,n);
        }

        private int getMoneyAmount2(int begin,int end){
            if (begin >= end)
                return 0;
            if (end - begin == 1)
                return begin;
            if (end - begin == 2)
                return begin + 1;

            if (budget[begin][end] != 0)
                return budget[begin][end];
            int ret = Integer.MAX_VALUE;
            for (int i = begin; i <= end; i++) {
                int cost = i + Math.max(getMoneyAmount2(begin, i - 1), getMoneyAmount2(i + 1, end));
                ret = Math.min(ret, cost);
            }

            budget[begin][end] = ret;
            return ret;
        }
    }


    interface Solution {
        /**
         * 1 <= n <= 200
         * @param n
         * @return 最糟糕的情况下赢得比赛需要的现金数
         */
        int getMoneyAmount(int n);
    }


    public static class Factory {

        private static final Map<Policy, Solution> solutionMap = new HashMap<>();

        static {
            solutionMap.put(Policy.DP, new Dp());
        }

        public static Solution getSolution(Policy policy) {
            return solutionMap.get(policy);
        }

    }


}
