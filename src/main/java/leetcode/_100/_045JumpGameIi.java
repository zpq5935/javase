package leetcode._100;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/generate-parentheses/">45. 跳跃游戏 II</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
})
public class _045JumpGameIi {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }


    @Test
    public void case1() {
        Assertions.assertEquals(2, solution.jump(new int[]{2, 3, 1, 1, 4}));
    }
    @Test
    public void case2() {
        Assertions.assertEquals(0, solution.jump(new int[]{0}));
    }



    enum Policy {
        DP,
        ;
    }

    public static class DP implements Solution {


        @Override
        public int jump(int[] nums) {
            if (nums.length == 1)
                return 0;
            int step = 0;
            int index =0;
            while (true) {
                if (nums[index] + index >= nums.length - 1){
                    step++;
                    break;
                }

                int nextIndex = nums[index] + index;
                for (int i = index + 1; i <= nums[index] + index; i++) {
                    nextIndex = nums[i] + i > nums[nextIndex] + nextIndex ? i : nextIndex;
                }
                index = nextIndex;
                step++;
            }

            return step;
        }
    }


    interface Solution {
        /**
         * 1 <= nums.length <= 104
         * 0 <= nums[i] <= 1000
         * 题目保证可以到达 nums[n-1]
         * @param nums
         * @return
         */
        int jump(int[] nums);
    }


    public static class Factory {

        private static final Map<Policy, Solution> solutionMap = new HashMap<>();

        static {
            solutionMap.put(Policy.DP, new DP());
        }

        public static Solution getSolution(Policy policy) {
            return solutionMap.get(policy);
        }

    }


}
