package leetcode.bit_manipulation;


/**
 * 简单
 */

/**
 * 476. 数字的补数
 * 位运算
 * https://leetcode.cn/problems/number-complement
 */
public class Sol476 {
    public static void main(String[] args) {
        System.out.println(new Solution().findComplement(5));
        System.out.println(new Solution().findComplement(2));
        System.out.println(new Solution().findComplement(2147483647));
        System.out.println(~2147483647);

    }

    static class Solution {
        /**
         * https://leetcode-cn.com/problems/number-complement/
         * 取补数
         *
         * @param num
         * @return
         */
        public int findComplement(int num) {
            int highbit = 0;
            for (int i = 1; i <= 30; ++i) {
                if (num >= 1 << i) {
                    highbit = i;
                } else {
                    break;
                }
            }
            int mask = highbit == 30 ? 0x7fffffff : (1 << (highbit + 1)) - 1;
            return num ^ mask;
        }
    }
}
