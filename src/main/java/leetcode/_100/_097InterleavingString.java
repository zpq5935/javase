package leetcode._100;

import leetcode.LeetCodeConstant;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <a href="https://leetcode.cn/problems/interleaving-string/description/">97. 交错字符串</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.String
})
public class _097InterleavingString {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }


    @Test
    public void case0() {
        Assert.assertEquals(true,solution.isInterleave("aabcc","dbbca","aadbbcbcac"));
    }
    @Test
    public void case1() {
        Assert.assertEquals(true,solution.isInterleave("","",""));
    }
    @Test
    public void case2() {
        Assert.assertEquals(false,solution.isInterleave("aabcc","dbbca","aadbbbaccc"));
    }
    @Test
    public void case3() {
        Assert.assertEquals(false,solution.isInterleave(
                "bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa",
                "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab",
                "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab"
        ));
    }





    enum Policy {
        DP,
        ;
    }


        /**
     * 加了缓存才不重复计算没那么耗时，但是总体的时间 空间复杂度还是高
     */
    public static class DP implements Solution {

        public static class Tuple {
            private String s1;
            private String s2;
            private String s3;

            public Tuple(String s1, String s2, String s3) {
                this.s1 = s1;
                this.s2 = s2;
                this.s3 = s3;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Tuple tuple = (Tuple) o;
                return Objects.equals(s1, tuple.s1) && Objects.equals(s2, tuple.s2) && Objects.equals(s3, tuple.s3);
            }

            @Override
            public int hashCode() {
                return Objects.hash(s1, s2, s3);
            }
        }

        HashMap<Tuple, Boolean> cache = new HashMap<>();

        @Override
        public boolean isInterleave(String s1, String s2, String s3) {

            if (cache.get(new Tuple(s1, s2, s3)) != null) {
                return cache.get(new Tuple(s1, s2, s3));
            }
            if (s1.length() + s2.length() != s3.length())
                return false;
            if (s1.length() == 0 && s2.length() == 0 && s3.length() == 0)
                return true;


            char tc = s3.charAt(0);
            if (s1.length() > 0 && s1.charAt(0) == tc) {
                if (isInterleave(s1.length() > 1 ? s1.substring(1) : "", s2, s3.length() > 1 ? s3.substring(1) : "")) {
                    cache.put(new Tuple(s1, s2, s3), true);
                    return true;
                }
            }

            if (s2.length() > 0 && s2.charAt(0) == tc) {
                if (isInterleave(s1, s2.length() > 1 ? s2.substring(1) : "", s3.length() > 1 ? s3.substring(1) : "")) {
                    cache.put(new Tuple(s1, s2, s3), true);
                    return true;
                }
            }

            cache.put(new Tuple(s1, s2, s3), false);
            return false;
        }
    }



    interface Solution {
        /**
         * 0 <= s1.length, s2.length <= 100
         * 0 <= s3.length <= 200
         * s1、s2、和 s3 都由小写英文字母组成
         */
        boolean isInterleave(String s1, String s2, String s3);
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
