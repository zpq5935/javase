package leetcode._lcp100;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;


/**
 * <a href="https://leetcode.cn/problems/Ju9Xwi/">LCS 01. 下载插件</a>
 */

@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.Array,
        LeetCodeConstant.TagEnum.DynamicPrograming,
})
public class _001Ju9Xwi {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.Math);
    }


    @Test
    public void case1() {
        Assertions.assertEquals(2, solution.leastMinutes(2));
    }
    @Test
    public void case2() {
        Assertions.assertEquals(8, solution.leastMinutes(100));
    }


    enum Policy {
        Sol1,
        Math,
        ;
    }


    public static class Sol1 implements Solution {

        /**
         * 1 <= n <= 10^5
         *
         * @param n 需要下载的插件数量
         * @return 消耗多少分钟
         */
        @Override
        public int leastMinutes(int n) {

            for (int i = 1; i < n; i++) {// 分钟数 y = 2sup()
                int maxNumbers = i;
                for (int j = 0; j < i; j++) {// 提升带宽分钟数
                    maxNumbers = (int) Math.max(maxNumbers, Math.pow(2, j) * (i - j));
                    if (maxNumbers >= n) {// 首次遇到 就是最少消耗的分钟数
                        return i;
                    }
                }
            }
            return n;
        }
    }

    public static class MathSol implements Solution {

        /**
         * 1 <= n <= 10^5 <br>
         * 当 n > 2时，每分钟最大能下载插件数如下 <pre>y = 2<sup>n-1</sup></pre>
         *
         * @param n 需要下载的插件数量
         * @return 消耗多少分钟
         */
        @Override
        public int leastMinutes(int n) {
            if (n <= 2)
                return n;

            int minute = 3;
            while (true) {
                if (Math.pow(2, minute - 1) >= n)
                    return minute;
                minute++;
            }
        }
    }

    interface Solution {
        int leastMinutes(int n);
    }


    public static class Factory {

        private static final Map<Policy, Solution> solutionMap = new HashMap<>();

        static {
            solutionMap.put(Policy.Sol1, new Sol1());
            solutionMap.put(Policy.Math, new MathSol());
        }

        public static Solution getSolution(Policy policy) {
            return solutionMap.get(policy);
        }

    }


}
