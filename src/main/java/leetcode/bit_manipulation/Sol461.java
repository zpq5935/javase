package leetcode.bit_manipulation;


/**
 * 简单
 */

import org.junit.Test;

/**
 * 461. 汉明距离
 * 位运算
 */
public class Sol461 {
    @Test
    public void toHex() {
        System.out.println(new Solution12().hammingDistance(11, 562));
    }

    /**
     * 汉明举例，及位不同的个数
     */
    class Solution12 {
        public int hammingDistance(int x, int y) {
            return Integer.bitCount(x ^ y);
        }
    }
}
