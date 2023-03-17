package leetcode.bit_manipulation;


/**
 * 简单
 */

import java.util.function.Function;

/**
 * 190. 颠倒二进制位
 * 位运算
 * 分治
 * https://leetcode.cn/problems/reverse-bits
 */
public class Sol190 {
    public static void main(String[] args) {
        String bitStr = "00000010100101000001111010011100";
        int input = Integer.parseUnsignedInt(bitStr, 2);
        System.out.println("In " + input);


        System.out.println(sol.apply(input));

    }

    static Function<Integer, String> sol = input -> {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            ret = (ret << 1) + ((input >> i) & 1);
        }
        return String.valueOf(ret);
    };

}
