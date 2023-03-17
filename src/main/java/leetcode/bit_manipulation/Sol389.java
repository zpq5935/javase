package leetcode.bit_manipulation;


/**
 * 简单
 */

/**
 * 389. 找不同
 * 位运算
 * 哈希表
 * 字符串
 * 1+
 */
public class Sol389 {
    public static void main(String[] args) {
        System.out.println(new Solution10().findTheDifference("abdcf", "pabdcf"));


    }

    /**
     * 给定两个字符串 s 和 t，它们只包含小写字母。
     * <p>
     * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
     * <p>
     * 请找出在 t 中被添加的字母
     */
    static class Solution10 {
        public char findTheDifference(String s, String t) {
            int sum = 0;
            int lenth = s.length();
            for (int i = 0; i < lenth + 1; i++)
                sum += (byte) t.charAt(i);
            for (int i = 0; i < lenth; i++)
                sum -= (byte) s.charAt(i);
            return (char) (sum);
        }
    }
}
