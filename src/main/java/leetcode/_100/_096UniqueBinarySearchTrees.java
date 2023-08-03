package leetcode._100;

import leetcode.LeetCodeConstant;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/unique-binary-search-trees/">95. 不同的二叉搜索树 II</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Tree,
        LeetCodeConstant.TagEnum.BinarySearchTree,
})
public class _096UniqueBinarySearchTrees {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }


    @Test
    public void case0() {
        Assert.assertEquals(1,solution.numTrees(1));
    }
    @Test
    public void case2() {
        Assert.assertEquals(5,solution.numTrees(3));
    }
    @Test
    public void case3() {
        Assert.assertEquals(1767263190,solution.numTrees(19));
    }





    enum Policy {
        DP,
        ;
    }

    public static class DP implements Solution {

        int[][] cache = new int[20][20];

        public int nodes(int left, int right) {

            if (left >= right)
                return 1;

            if (cache[left][right] != 0)
                return cache[left][right];

            int plans = 0;
            for (int i = left; i <= right; i++) {
                int leftPlans = nodes(left, i - 1);
                int rightPlans = nodes(i + 1, right);


                plans += leftPlans * rightPlans;
            }

            cache[left][right] = plans;
            return plans;
        }


        @Override
        public int numTrees(int n) {
            return nodes(1,n);
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }


    }



    interface Solution {
        int numTrees(int n);
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
