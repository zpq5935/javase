package leetcode.bit_manipulation;


/**
 * 简单
 */

/**
 * 191. 位1的个数
 * 位运算
 * https://leetcode.cn/problems/number-of-1-bits
 */
public class Sol191 {
    public static void main(String[] args) {
        String bitStr = "11111111111111111111111111111101";
        System.out.println("In " + Integer.parseUnsignedInt(bitStr, 2));
        System.out.println(new Solution5().hammingWeight(Integer.parseUnsignedInt(bitStr, 2)));
        System.out.println(new Solution5().hammingWeight2(Integer.parseUnsignedInt(bitStr, 2)));

    }

    /**
     * 返回 1 的个数
     */
    static class Solution5 {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            String unsignedString = Integer.toUnsignedString(n, 2);
            int count = 0;
            for (int i = 0; i < unsignedString.length(); i++) {
                if (unsignedString.charAt(i) == '1') count++;
            }
            return count;
        }

        public int hammingWeight2(int n) {
            int count = 0;
            while (n != 0) {
                if ((n & 1) == 1) count++;
                n = n >>> 1;
//            System.out.println(Integer.toBinaryString(n));
            }
            return count;
        }
    }
}
