package leetcode.algorithm.period1_100;


import java.util.HashMap;
import java.util.Map;

/**
 * 20. 有效的括号-简单
 * 栈
 * 字符串
 * https://leetcode.cn/problems/valid-parentheses
 */
public class Sol20 {
    public static void main(String[] args) {
        System.out.println(new Solution4().isValid("[]"));

    }

    /**
     * 有效括号
     */
    static class Solution4 {
        public boolean isValid(String s) {
            if (s.length() % 2 != 0) return false;
            Map<Character, Character> map = new HashMap();
            map.put('}', '{');
            map.put(')', '(');
            map.put(']', '[');
            char[] left = new char[s.length()];
            int lIndex = 0;
            for (int i = 0; i < s.length(); i++) {
                char cur = s.charAt(i);
                if (cur == '(' || cur == '[' || cur == '{')
                    left[lIndex++] = s.charAt(i);
                else if (!(lIndex > 0 && left[--lIndex] == map.get(cur))) {
                    return false;
                }
            }
            return lIndex == 0;
        }
    }
}
