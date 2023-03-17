package leetcode.bit_manipulation;


/**
 * 简单
 */

import org.junit.Test;

/**
 * 405. 数字转换为十六进制数
 * 位运算
 * 数学
 */
public class Sol405 {
    @Test
    public void toHex() {
//        System.out.println(new Solution11().toHex(11));
        System.out.println(new Solution11().toHex2(11));
    }

    class Solution11 {
        public String toHex(int num) {
            return Integer.toHexString(num);
        }

        public String toHex2(int num) {
            String s = Integer.toUnsignedString(num, 16);
            return Integer.toHexString(num);
        }
    }
}
