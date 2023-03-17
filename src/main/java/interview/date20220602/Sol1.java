package interview.date20220602;

import java.util.HashSet;
import java.util.Set;

/**
 * 1，计算无重复字符的最长子串的长度
 * 描述：给定一个字符串s，请你找出其中不含有重复字符的最长子串的长度。
 * 示例1:
 * 输入:s="abcabcbb"
 * 输出:3
 * 解释:因为无重复字符的最长子串是"abc"，所以其长度为3。
 * 示例2:
 * 输入:s="bbbbb"
 * 输出:1
 * 解释:因为无重复字符的最长子串是"b"，所以其长度为1。
 * 请注意，你的答案必须是子串的长度。0<=s.length<=5*104
 * s由英文字母、数字、符号和空格组成
 **/


public class Sol1 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubString("adc"));
        System.out.println(lengthOfLongestSubString("abbb"));
    }
    public static int lengthOfLongestSubString(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
}
