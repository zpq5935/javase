package leetcode.algorithm.period1_100;


/**
 * 简单
 */

import java.util.HashSet;

/**
 * 27. 移除元素
 * 数组
 * 双指针
 * https://leetcode.cn/problems/remove-element
 */
public class Sol27 {
    public static void main(String[] args) {
        //        int[] arr1 = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
//        int[] arr1 = new int[]{3, 2, 2, 3};
        int[] arr1 = new int[]{2};
        int count = new Solution7().removeElement(arr1, 3);
        System.out.println(count);
        for (int i = 0; i < count; i++) {
            System.out.print(arr1[i] + ",");
        }

    }

    /**
     * 移除指定数字
     */
    static class Solution7 {
        public int removeElement(int[] nums, int val) {
            if (nums.length == 0)
                return 0;
            int lIndex = -1, rIndex = nums.length;
            int cnt = 0;
            while (lIndex <= rIndex) {
                while (++lIndex < rIndex && lIndex <= nums.length - 1 && nums[lIndex] != val) {
                    cnt++;
                }
                while (lIndex < --rIndex && nums[rIndex] == val) {
                }
                if (lIndex < rIndex) {
                    nums[lIndex] = nums[rIndex];
                    cnt++;
                }
            }
            return cnt;
        }
    }
}
