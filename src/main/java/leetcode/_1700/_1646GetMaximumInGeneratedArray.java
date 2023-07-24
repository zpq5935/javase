package leetcode._1700;

import leetcode.LeetCodeConstant;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * <a href="https://leetcode.cn/problems/get-maximum-in-generated-array/">1646. 获取生成数组中的最大值</a>
 */

@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.Array,
        LeetCodeConstant.TagEnum.DynamicPrograming,
})
public class _1646GetMaximumInGeneratedArray {


    private Solution solution;

    @BeforeEach
    public void loadSolution(){
        solution = Factory.getSolution(Policy.Sol1);
    }


    @Test
    public void case1(){
        Assertions.assertEquals(1, solution.getMaximumGenerated(2));
    }
    @Test
    public void case2(){
        Assertions.assertEquals(2, solution.getMaximumGenerated(3));
    }


    enum Policy {
        Sol1,
        Math,
        ;
    }

    public static class Sol1 implements Solution {

        @Override
        public int getMaximumGenerated(int n) {
            if (n < 2)
                return n == 0 ? 0 : 1;

            int[] nums = new int[n + 1];
            nums[0] = 0;
            nums[1] = 1;
            int max = 0;

            for (int i = 2; i <= n; i++) {
                if (i % 2 == 0) {
                    nums[i] = nums[i / 2];
                } else {
                    nums[i] = nums[i / 2] + nums[i / 2 + 1];
                }
                max = Math.max(max, nums[i]);
            }

            return max;
        }
    }

    interface Solution {
        /**
         * 0 <= n <= 100
         * <p>
         * nums[0] = 0
         * nums[1] = 1
         * 当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
         * 当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]
         * <p>
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode.cn/problems/get-maximum-in-generated-array
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */
        int getMaximumGenerated(int n);
    }


    public static class Factory {

        public static Solution getSolution(Policy policy) {
            switch (policy) {
                case Sol1:
                    return new Sol1();
                default:
                    throw new UnsupportedOperationException();
            }
        }

    }


}
