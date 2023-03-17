package leetcode.bit_manipulation;


/**
 * 简单
 */

/**
 * 268. 丢失的数字
 * 位运算
 * 数组
 * 哈希表
 * 2+
 * https://leetcode.cn/problems/missing-number
 */
public class Sol268 {
    public static void main(String[] args) {
        System.out.println(new Solution7().missingNumber(new int[]{0}));


    }

    /**
     * 返回丢失的那个数字
     */
    static class Solution7 {
        public int missingNumber(int[] nums) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) sum += nums[i];
            return (nums.length + 1) * nums.length / 2 - sum;
        }
    }
}
