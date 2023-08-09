package leetcode._200;

import cn.hutool.core.collection.ListUtil;
import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/word-break/">139. 单词拆分</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.String,
})
public class _139WordBreak {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }


    @Test
    public void case0() {
        Assertions.assertEquals(true,
                solution.wordBreak(
                        "leetcode",
                        ListUtil.of("leet", "code")
                ));
    }

    @Test
    public void case1() {
        Assertions.assertEquals(false,
                solution.wordBreak(
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                        ListUtil.of("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")
                ));
    }





    enum Policy {
        DP,
        DP2,
        ;
    }
    public static class DP2 implements Solution {

        @Override
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordDicts = new HashSet<>(wordDict);
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;

            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && wordDicts.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }

            }


            return dp[s.length()];
        }
    }


    public static class DP implements Solution {


        Set<String> wordDicts;
        int max;
        Boolean[] flags = new Boolean[300];// 表示从下标开始的后续字符串无法匹配
        int maxlevel = 0;

        @Override
        public boolean wordBreak(String s, List<String> wordDict) {
            wordDicts = new HashSet<>(wordDict);
            max = wordDicts.stream().mapToInt(String::length).max().getAsInt();

            boolean ret = wordBreak(s, 0, 0);
            System.out.println("最大递归层数 " + maxlevel);
            return ret;
        }

        private boolean wordBreak(String s, int beginIndex,int level) {
            maxlevel = Math.max(level, maxlevel);
            if (flags[beginIndex] != null)
                return flags[beginIndex];

            for (int i = 1; i <= s.length(); i++) {
                if(wordDicts.contains(s.substring(0,i))){
                    if (i == s.length()) {
                        flags[beginIndex] = true;
                        return true;
                    }
                    if (wordBreak(s.substring(i), beginIndex + i, level+1)) {
                        flags[beginIndex] = true;
                        return true;
                    }
                } else if (i == max){
                    flags[beginIndex] = false;
                    return false;
                }
            }

            flags[beginIndex] = false;
            return false;
        }


    }


    interface Solution {
        /**
         * 1 <= s.length <= 300
         * 1 <= wordDict.length <= 1000
         * 1 <= wordDict[i].length <= 20
         * s 和 wordDict[i] 仅有小写英文字母组成
         * wordDict 中的所有字符串 互不相同
         */
        boolean wordBreak(String s, List<String> wordDict);
    }


    public static class Factory {

        private static final Map<Policy, Solution> solutionMap = new HashMap<>();

        static {
            solutionMap.put(Policy.DP, new DP());
            solutionMap.put(Policy.DP2, new DP2());
        }

        public static Solution getSolution(Policy policy) {
            return solutionMap.get(policy);
        }

    }


}
