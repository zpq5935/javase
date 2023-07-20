package leetcode.onehundred;

import leetcode.LeetCodeConstant;


/**
 * <a href="https://leetcode.cn/problems/regular-expression-matching/">10. 正则表达式匹配</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.String,
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Dp,
})
public class _010RegularExpressionMatching {



    public static void main(String[] args) {
        Solution solution = Factory.getSolution(Policy.Sol1);
        System.out.println(solution.isMatch("abcc","abc*d"));
    }



    enum Policy {
        Sol1,
        ;
    }

    public static class Sol1 implements Solution {

        /**
         * 状态转移方程，；及其实现
         */
        @Override
        public boolean isMatch(String source, String regex) {
            int m = source.length();
            int n = regex.length();

            boolean[][] flags = new boolean[m + 1][n + 1];
            flags[0][0] = true;
            for (int i = 1; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    if (regex.charAt(j - 1) == '*') {
                        if (match(source, i , regex, j - 1)) {
                            flags[i][j] = flags[i - 1][j] || flags[i][j - 2];
                        } else {
                            flags[i][j] = flags[i][j - 2];
                        }
                    } else {
                        if (match(source, i, regex, j)) {
                            flags[i][j] = flags[i - 1][j - 1];
                        }
                    }
                }
            }



            return flags[m][n];
        }

        private boolean match(String source, int i,String regex, int j) {
            if (regex.charAt(j - 1) == '.')
                return true;

            return source.charAt(i - 1) == regex.charAt(j - 1);
        }
    }

    interface Solution {
        /**
         * 1 <= s.length <= 20
         * 1 <= p.length <= 20
         * s 只包含从 a-z 的小写字母。
         * p 只包含从 a-z 的小写字母，以及字符 . 和 *。
         * 保证每次出现字符 * 时，前面都匹配到有效的字符
         *
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode.cn/problems/regular-expression-matching
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */
        boolean isMatch(String source, String regex);
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
