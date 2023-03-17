package leetcode.bit_manipulation;


/**
 * 简单
 */

/**
 * 67. 二进制求和
 * 位运算
 * 数学
 * 字符串
 * 1+
 * https://leetcode.cn/problems/add-binary
 */
public class Sol67 {
    public static void main(String[] args) {
        System.out.println(new Solution2().addBinary("101", "111"));


    }

    static class Solution2 {
        public String addBinary(String a, String b) {
            return Integer.toBinaryString(Integer.valueOf(a, 2) + Integer.valueOf(b, 2));// 未考虑边界
        }
    }
}
