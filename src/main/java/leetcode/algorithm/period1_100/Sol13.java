package leetcode.algorithm.period1_100;


import java.util.HashMap;
import java.util.Map;

/**
 * 13-罗马数字转整数-简单
 * 简单：
 * 哈希表
 * 数学
 * 字符串
 * https://leetcode.cn/problems/roman-to-integer/
 */
public class Sol13 {
    public static void main(String[] args) {
        System.out.println(new Sol1().romanToInt("MCMXMIV"));
        System.out.println(new Solution2().romanToInt("MCMXMIV"));
    }

    static class Sol1 implements Sol {
        @Override
        public int romanToInt(String s) {
            int number = 0;
            char[] chars = s.toCharArray();
            Map<String, Integer> mapping = new HashMap<>();
            mapping.put("I", 1);
            mapping.put("V", 5);
            mapping.put("X", 10);
            mapping.put("L", 50);
            mapping.put("C", 100);
            mapping.put("D", 500);
            mapping.put("M", 1000);
            for (int i = 0; i < chars.length; i++) {
                StringBuffer curSb = new StringBuffer();
                int curInt;
                if ((chars[i] == 'I' && i < chars.length - 1 && (chars[i + 1] == 'V' || chars[i + 1] == 'X'))
                        || (chars[i] == 'X' && i < chars.length - 1 && (chars[i + 1] == 'L' || chars[i + 1] == 'C'))
                        || (chars[i] == 'C' && i < chars.length - 1 && (chars[i + 1] == 'D' || chars[i + 1] == 'M'))) {
                    curSb.append(chars[i]).append(chars[i + 1]);
                    curInt = (mapping.get(String.valueOf(chars[i + 1])) - mapping.get(String.valueOf(chars[i])));
                    number += curInt;
                    i++;
                } else {
                    curSb.append(chars[i]);
                    curInt = mapping.get(String.valueOf(chars[i]));
                    number += curInt;
                }
//            System.out.println(curSb.toString() + " " + curInt);
            }
            return number;

        }
    }

    static class Solution2 implements Sol {

        Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};

        public int romanToInt(String s) {
            int ans = 0;
            int n = s.length();
            for (int i = 0; i < n; ++i) {
                int value = symbolValues.get(s.charAt(i));
                if (i < n - 1 && value < symbolValues.get(s.charAt(i + 1))) {
                    ans -= value;
                } else {
                    ans += value;
                }
            }
            return ans;
        }

    }

    interface Sol {

        int romanToInt(String s);
    }
}
