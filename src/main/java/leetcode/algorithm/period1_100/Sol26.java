package leetcode.algorithm.period1_100;


/**
 * 简单
 */

import java.util.HashSet;

/**
 * 26. 删除有序数组中的重复项
 * 数组
 * 双指针
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array
 */
public class Sol26 {
    public static void main(String[] args) {
        System.out.println(new Solution6().removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
        System.out.println(new Solution6().removeDuplicates2(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
        System.out.println(new Solution6().removeDuplicates3(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));

    }

    /**
     * 删除有序数组的重复项
     */
    static class Solution6 {


        public int removeDuplicates(int[] nums) {
            HashSet set = new HashSet();
            int count = 0;
            int[] numsNew = new int[nums.length];
            for (int i : nums) {
                if (set.add(Integer.valueOf(i))) {
                    numsNew[count++] = i;
                }
            }
            System.arraycopy(numsNew, 0, nums, 0, count);
            return count;
        }

        public int removeDuplicates2(int[] nums) {
            int count = 0;
            int[] index = new int[20000];
            index[10000] = -1;
            int[] numsNew = new int[nums.length];
            for (int i : nums) {
                if (!(index[10000 + i] == i)) {// 新值
                    index[10000 + i] = i;
                    numsNew[count++] = i;
                }
            }
            System.arraycopy(numsNew, 0, nums, 0, count);
            return count;
        }

        public int removeDuplicates3(int[] nums) {
            int count = 0;
            int lastNum = 10001;
            int[] numsNew = new int[nums.length];
            for (int i : nums) {
                if (i != lastNum) {// 新值
                    lastNum = i;
                    numsNew[count++] = i;
                }
            }
            System.arraycopy(numsNew, 0, nums, 0, count);
            return count;
        }
    }
}
