package leetcode._400;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/largest-divisible-subset/">368. 最大整除子集</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Array,
})
public class _368LargestDivisibleSubset {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }

    @Test
    public void case0() {

        List<Integer> integers = solution.largestDivisibleSubset(new int[]{1, 2, 4, 8});
        Assertions.assertArrayEquals(new Integer[]{1, 2, 4, 8}, integers.toArray());
    }

    enum Policy {
        DP,
        ;
    }

    public static class Dp implements Solution {

        @Override
        public List<Integer> largestDivisibleSubset(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> dp = new ArrayList<>(nums.length);
            int length = nums.length;
            dp.add(Collections.singletonList(nums[0]));

            List<Integer> ret;
            ret = dp.get(0);
            for (int i = 1; i < length; i++) {
                dp.add(Collections.singletonList(nums[i]));
                for (int j = 0; j < i; j++) {
                    List<Integer> tmps = dp.get(j);
                    if (nums[i] % tmps.get(tmps.size() - 1) == 0 && tmps.size() + 1 > dp.get(i).size()) {
                        ArrayList<Integer> newArr = new ArrayList<>(tmps);
                        newArr.add(nums[i]);
                        dp.set(i, newArr);
                    }
                }
                if (dp.get(i).size() > ret.size())
                    ret = dp.get(i);
            }

            return ret;
        }

    }


    interface Solution {

        /**
         * 1 <= nums.length <= 1000 <br>
         * <pre>1 <= nums[i] <= 2 * 10<sup>9</sup></pre> <br>
         * nums 中的所有整数 互不相同 <br>
         * @param nums
         * @return
         */
        List<Integer> largestDivisibleSubset(int[] nums);
    }


    public static class Factory {

        private static final Map<Policy, Solution> solutionMap = new HashMap<>();

        static {
            solutionMap.put(Policy.DP, new Dp());
        }

        public static Solution getSolution(Policy policy) {
            return solutionMap.get(policy);
        }

    }


}
