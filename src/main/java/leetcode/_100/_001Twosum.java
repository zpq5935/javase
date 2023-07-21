package leetcode._100;

import leetcode.LeetCodeConstant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <a href="https://leetcode.cn/problems/two-sum/">第1题 两数求和</a>
 *
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.Array,
        LeetCodeConstant.TagEnum.Hash
})
public class _001Twosum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new _001Twosum().twoSum(new int[]{2,766,7,15},9)));
    }

    // tip 快速查找
    // hash 空间换时间
    /**
     * 2 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * 只会存在一个有效答案
     * 不存在有序、存在负数
     *
     * 可以想出一个时间复杂度小于 O(n2)
     */
    public int[] twoSum(int[] nums, int target) {
        // loop 1
        Map<Integer,Integer> valAndIndex = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            valAndIndex.put(nums[i], i);
        }

        // loop 2
        for (int i = 0; i < nums.length; i++) {
            Integer anotherIndexMayExist = valAndIndex.get(target - nums[i]);
            if (null != anotherIndexMayExist && !Objects.equals(i, anotherIndexMayExist)) {// fix 可能同一个数*2=target
                return new int[]{i, anotherIndexMayExist};
            }
        }

        // never
        return null;
    }
}
