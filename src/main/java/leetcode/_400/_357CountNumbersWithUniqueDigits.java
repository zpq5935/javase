package leetcode._400;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;
import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/count-numbers-with-unique-digits/">357. 统计各位数字都不同的数字个数</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Array,
})
public class _357CountNumbersWithUniqueDigits {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }

    @Test
    public void case0() {
        Assertions.assertEquals(91, solution.countNumbersWithUniqueDigits(2));
    }
    @Test
    public void case1() {
        Assertions.assertEquals(739, solution.countNumbersWithUniqueDigits(3));
    }

    enum Policy {
        DP,
        ;
    }

    public static class Dp implements Solution {
        @Override
        public int countNumbersWithUniqueDigits(int n) {
            if (n == 0)
                return 1;
            if (n == 1)
                return 10;
            if (n == 2)
                return 91;


            int uniqueCount = 91;
            int preZCount = 9;// 前轮有0计数
            int preNZCount = 72;// 前轮无0计数

            for (int i = 3; i <= n; i++) {

                int cNCount = cc(i - 2) * (10 - i + 1);// 当前轮有0计数
                int a1 = preZCount * (10 - i + 1);
                int a2 = preNZCount * (10 - i);

                preZCount = a1 + cNCount;
                preNZCount = a2;

                uniqueCount += (preNZCount + preZCount);
            }

            return uniqueCount;
        }

        private int cc(int n) {
            int ret = 1;
            for (int i = 9; n > 0; i--,n--)
                ret *= i;
            return ret;
        }

    }


    interface Solution {
        /**
         * <p>
         * 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 <pre>0 <= x < 10<sup>n</sup></pre> <br>
         * 0 <= n <= 8
         * </p>
         *
         * @param n
         * @return
         */
        int countNumbersWithUniqueDigits(int n);
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
