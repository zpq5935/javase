package leetcode._400;

import leetcode.LeetCodeConstant;

/**
 * <a href="https://leetcode.cn/problems/is-subsequence/">392. 判断子序列</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.Dp,
        LeetCodeConstant.TagEnum.Math,
        LeetCodeConstant.TagEnum.MemorySearch,
})
public class _342IsSubsequence {


    public static void main(String[] args) {
        Solution solution = Factory.getSolution(Policy.Sol1);
        //
        boolean shouldTrue = solution.isSubsequence("abc", "ahbgdc");
        System.out.println(Boolean.TRUE.equals(shouldTrue));
        //
        boolean plan2ShouleFalse = solution.isSubsequence("acb", "ahbgdc");
        System.out.println(Boolean.FALSE.equals(plan2ShouleFalse));
    }


    enum Policy {
        Sol1,
        ;
    }

    public static class Sol1 implements Solution {

        @Override
        public boolean isSubsequence(String s, String t) {
            if (s.length() > t.length())
                return false;

            int i = 0, j = 0;

            dc:
            for (; i < s.length(); i++) {

                while (j < t.length()) {
                    char c = t.charAt(j++);
                    if (c == s.charAt(i)) {
                        continue dc;
                    }
                }

                if (j == t.length())
                    return false;
            }
            return i == s.length();
        }
    }

    interface Solution {
        /**
         * 0 <= sub.length <= 100
         * 0 <= t.length <= 10^4
         * 两个字符串都只由小写字符组成。
         */
        boolean isSubsequence(String sub, String t);
    }


    public static class Factory {

        public static Solution getSolution(Policy policy) {
            switch (policy) {
                case Sol1:
                    return new Sol1();
                default:
                    throw new UnsupportedOperationException();
            }
        }

    }


}

