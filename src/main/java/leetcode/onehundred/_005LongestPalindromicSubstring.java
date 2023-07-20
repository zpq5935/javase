package leetcode.onehundred;

import leetcode.LeetCodeConstant;

/**
 * <a href="https://leetcode.cn/problems/longest-palindromic-substring/">5. 最长回文子串</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.String,
        LeetCodeConstant.TagEnum.DynamicPrograming,
})
public class _005LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println(longestPalindrome(Policy.ExpandCenter, "babad"));
    }


    /**
     * <li>1 <= s.length <= 1000</li>
     * <li>s 仅由数字和英文字母组成</li>
     */
    public static String longestPalindrome(Policy policy,String s) {
        return Factory.getSolution(policy).longestPalindrome(s);
    }


    enum Policy{
        Sol1,
        ExpandCenter,
        /*还有个什么臂长的算法，没看太复杂来着*/
        ;
    }


    interface Solution {
        String longestPalindrome(String s);
    }

    public static class ExpandCenterSolution implements Solution {
        @Override
        public String longestPalindrome(String s) {
            if (s.length() <= 2)
                return s;
            String retS = "";
            for (int i = 0; i < s.length(); i++) {
                String expand1 = expand(s, i, i);
                String expand2 = expand(s, i, i+1);
                int length = Math.max(expand1.length(), expand2.length());
                if (length > retS.length()) {
                    retS = expand1.length() > expand2.length() ? expand1 : expand2;
                }
            }

            return retS;
        }


        private String expand(String s,int i,int j){
            while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            }
            i++;
            j--;

            return j >= i ? s.substring(i, j + 1) : "";
        }
    }
    public static class Sol1Solution implements Solution {
        @Override
        public String longestPalindrome(String s) {
            return null;
        }
    }

    public static class Factory {

        public static Solution getSolution(Policy policy) {
            switch (policy) {
                case Sol1:
                    return new Sol1Solution();
                case ExpandCenter:
                    return new ExpandCenterSolution();
                default:
                    throw new UnsupportedOperationException();
            }
        }

    }


}
