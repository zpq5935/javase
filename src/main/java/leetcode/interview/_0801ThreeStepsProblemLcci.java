package leetcode.interview;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * <a href="https://leetcode.cn/problems/three-steps-problem-lcci/">面试题 08.01. 三步问题</a>
 */

@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.Array,
        LeetCodeConstant.TagEnum.DynamicPrograming,
})
public class _0801ThreeStepsProblemLcci {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.Sol1);
    }


    @Test
    public void case1() {
        // 1 2 3
        // 1 3
        // 2 3
        // 3
        Assertions.assertEquals(4, solution.waysToStep(3));
    }
    @Test
    public void case2() {
        Assertions.assertEquals(347873931, solution.waysToStep(100));
    }
    @Test
    public void case3() {
        Assertions.assertEquals(0, solution.waysToStep(1000000));
    }


    enum Policy {
        Sol1,
        ;
    }


    public static class Sol1 implements Solution {

        @Override
        public int waysToStep(int n) {
            long[] plans = new long[n + 3];
            plans[0] = 1;
            plans[1] = 1;
            plans[2] = 1;

            for (int i = 0; i < n; i++) {
                if (i + 1 < n) {
                    plans[i + 1] += plans[i];
                    plans[i + 1] %= 1000000007;
                }
                if (i + 2 < n) {
                    plans[i + 2] += plans[i];
                    plans[i + 1] %= 1000000007;
                }
                if (i + 3 < n) {
                    plans[i + 3] += plans[i];
                    plans[i + 1] %= 1000000007;
                }
            }

            return (int) plans[n - 1];
        }

    }


    interface Solution {
        /**
         * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007
         * <br>n范围在[1, 1000000]之间
         * @param n 台阶数量
         * @return 方案数量
         */
        int waysToStep(int n);
    }


    public static class Factory {

        private static final Map<Policy, Solution> solutionMap = new HashMap<>();

        static {
            solutionMap.put(Policy.Sol1, new Sol1());
        }

        public static Solution getSolution(Policy policy) {
            return solutionMap.get(policy);
        }

    }


}
