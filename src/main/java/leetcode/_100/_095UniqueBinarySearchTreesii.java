package leetcode._100;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/unique-binary-search-trees-ii/">95. 不同的二叉搜索树 II</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Tree,
        LeetCodeConstant.TagEnum.BinarySearchTree,
})
public class _095UniqueBinarySearchTreesii {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }


    @Test
    public void case0() {
        List<TreeNode> treeNodes = solution.generateTrees(3);
        treeNodes.forEach(root -> {
            preorderTraversal(root);
            System.out.println();
        });

//        Assertions.assertEquals(2, treeNodes);
    }





    enum Policy {
        DP,
        ;
    }

    public static class DP implements Solution {
        @Override
        public List<TreeNode> generateTrees(int n) {
            return nodes(1, n);
        }


        public List<TreeNode> nodes(int left, int right) {
            List<TreeNode> retTrees = new ArrayList<>();

            if (left > right)
                retTrees.add(null);

            for (int i = left; i <= right; i++) {
                List<TreeNode> leftNodes = nodes(left, i - 1);
                List<TreeNode> rightNodes = nodes(i + 1, right);


                for (int i1 = 0; i1 < leftNodes.size(); i1++) {
                    for (int i2 = 0; i2 < rightNodes.size(); i2++) {
                        retTrees.add(new TreeNode(i, leftNodes.get(i1), rightNodes.get(i2)));
                    }
                }
            }
            return retTrees;
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

    public void preorderTraversal(TreeNode root) {
        if (root != null) {
            System.out.print(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        } else {
            System.out.print("null");
        }
        System.out.print(",");
    }


    interface Solution {

        /**
         * 1 <= n <= 8
         * @param n
         * @return 所有的树
         */
        List<TreeNode> generateTrees(int n);
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
