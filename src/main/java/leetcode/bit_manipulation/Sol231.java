package leetcode.bit_manipulation;


/**
 * 简单
 */

/**
 * 231. 2 的幂
 * 位运算
 * 递归
 * 数学
 * https://leetcode.cn/problems/power-of-two
 */
public class Sol231 {
    public static void main(String[] args) {
        System.out.println(new Solution6().isPowerOfTwo(0));


    }

    /**
     * 判断一个数是否是 2 的幂
     */
    static class Solution6 {
        public boolean isPowerOfTwo(int n) {
//        if (n <= 0) return false;
            return n > 0 && (n & (n - 1)) == 0;
        }
    }
}
