package leetcode._200;

import cn.hutool.core.collection.ListUtil;
import leetcode.LeetCodeConstant;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <a href="https://leetcode.cn/problems/triangle/">120. 三角形最小路径和</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Array,
})
public class _120Triangle {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }


    @Test
    public void case0() {
        Assertions.assertEquals(11, solution.minimumTotal(
                ListUtil.of(
                        ListUtil.of(2),
                        ListUtil.of(3, 4),
                        ListUtil.of(6, 5, 7),
                        ListUtil.of(4, 1, 8, 3)
                )
        ));
    }
    @Test
    public void case1() {
        Assertions.assertEquals(-10, solution.minimumTotal(
                ListUtil.of(
                        ListUtil.of(-10)
                )
        ));
    }





    enum Policy {
        DP,
        ;
    }

    public static class DP implements Solution {

        @Override
        public int minimumTotal(List<List<Integer>> triangle) {
            int m = triangle.size();
            int[] preCosts = new int[m];
            int[] costs = new int[m];
            costs[0] = preCosts[0] = triangle.get(0).get(0);

            for (int i = 1; i < m; i++) {
                for (int j = 0; j <= i; j++) {// refactor 对循环边界处理下，可以把内部的判断外移
                    Integer nCost = triangle.get(i).get(j);
                    if (j == 0) costs[j] = preCosts[j] + nCost;
                    else if (j == i) costs[j] = preCosts[j - 1] + nCost;
                    else costs[j] = Math.min(preCosts[j] + nCost, preCosts[j - 1] + nCost);
                }
                if (i < m - 1) {
                    for (int k = 0; k <= i; k++) {// refactor 换种取上一个组的思路，可省去转移成本
                        preCosts[k] = costs[k];
                    }
                }
            }

            int minCost = costs[0];
            for (int i = 0; i < m ; i++) {
                minCost = Math.min(minCost, costs[i]);
            }
            return minCost;
        }
    }


    interface Solution {
        /**
         * 1 <= triangle.length <= 200
         * triangle[0].length == 1
         * triangle[i].length == triangle[i - 1].length + 1
         * -104 <= triangle[i][j] <= 104
         * @param triangle
         * @return
         */
        int minimumTotal(List<List<Integer>> triangle);
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
