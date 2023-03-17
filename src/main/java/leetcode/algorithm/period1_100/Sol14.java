package leetcode.algorithm.period1_100;


/**
 * 14. 最长公共前缀-简单
 * 字符串
 * https://leetcode.cn/problems/roman-to-integer
 */
public class Sol14 {
    public static void main(String[] args) {
        System.out.println(new Solution3().longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(new Solution3().longestCommonPrefix(new String[]{"", ""}));
        System.out.println(new Solution3().longestCommonPrefix(new String[]{"ab", "a"}));
        System.out.println(new Solution3().longestCommonPrefix(new String[]{"ab", "abc"}));
    }

    /**
     * 最长公共前缀
     */
    static class Solution3 implements Sol {
        @Override
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 1) return strs[0];
            char[] prefix = new char[200];
            int index = 0;
            String compare = strs[0];
            boolean flag = true;
            int limit = compare.length();
            do {
                for (int i = 0; i < strs.length; i++) {
                    String str = strs[i];
                    if (str.length() == 0) {
                        return "";
                    }

                    limit = limit > str.length() ? str.length() : limit;
                    if (!(str.charAt(index) == compare.charAt(index))) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    prefix[index] = (compare.charAt(index));
                    index++;
                }
            } while (flag && index < limit);

            return String.valueOf(prefix, 0, index);
        }
    }


    interface Sol {
         String longestCommonPrefix(String[] strs);
    }
}
