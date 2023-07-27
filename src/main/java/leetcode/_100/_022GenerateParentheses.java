package leetcode._100;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/generate-parentheses/">22. 括号生成</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
})
public class _022GenerateParentheses {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }


    @Test
    public void case1() {
        ArrayList<Object> expect = new ArrayList<>();
        expect.add("()");
        Assertions.assertEquals(expect, solution.generateParenthesis(1));
    }



    enum Policy {
        DP,
        ;
    }

    public static class DP implements Solution {
        @Override
        public List<String> generateParenthesis(int n) {
            List<String[]> dp = new ArrayList<>();// 分别表示各自的
            dp.add(0, new String[]{"()"});

            return Arrays.asList(dp.get(n - 1));
        }


    }


    interface Solution {
        /**
         * 1 <= n <= 8
         *
         * tip: 以 (a)b 做状态转移
         */
        List<String> generateParenthesis(int n);
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
