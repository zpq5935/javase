package leetcode._100;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/decode-ways/">91. 解码方法</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
})
public class _091DecodeWays {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }


    @Test
    public void case0() {
        Assertions.assertEquals(2, solution.numDecodings("12"));
    }
    @Test
    public void case1() {
        Assertions.assertEquals(3, solution.numDecodings("226"));
    }
    @Test
    public void case2() {
        Assertions.assertEquals(0, solution.numDecodings("06"));
    }
    @Test
    public void case3() {
        Assertions.assertEquals(4, solution.numDecodings("2611055971756562"));
    }
    @Test
    public void case4() {
        Assertions.assertEquals(1, solution.numDecodings("2101"));
    }





    enum Policy {
        DP,
        ;
    }

    public static class DP implements Solution {


        @Override
        public int numDecodings(String s) {
            if (s.charAt(0) == '0')
                return 0;

            int n = s.length();
            int[] f = new int[n + 1];// 前k个字符的解码方案数
            f[0] = 1;
            f[1] = 1;
            int offset = -1;

            for (int i = 2; i < n + 1; i++) {
                int curI = i + offset;
                char c = s.charAt(curI);
                char preC = s.charAt(curI - 1);


                if (c == '0') {
                    if (preC > '2' || preC == '0')
                        return 0;
                    else {
                        f[i] += f[i - 2];
                        continue;
                    }
                }

                if (preC == '0'){
                    f[i] += f[i - 1];
                    continue;
                }

                f[i] += f[i - 1];
                if ((preC == '2' && c <= '6') || (preC == '1'))
                    f[i] += f[i - 2];
            }

            return f[n];
        }
    }



    interface Solution {
        /**
         * 1 <= s.length <= 100
         * s 只包含数字，并且可能包含前导零
         *
         * <pre>
         * A = 1
         * ...
         * Z = 26
         * </pre>
         *
         * @param s 已编码的字符串
         * @return 解码的可能方案数
         */
        int numDecodings(String s);
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
