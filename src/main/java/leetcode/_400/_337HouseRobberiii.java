package leetcode._400;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/house-robber-iii/">337. 打家劫舍 III</a>
 */
@LeetCodeConstant.Tag(tagEnums = {LeetCodeConstant.TagEnum.DynamicPrograming, LeetCodeConstant.TagEnum.Array,})
public class _337HouseRobberiii {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }


    @Test
    public void case0() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);

        Assertions.assertEquals(9, solution.rob(root));
    }

    @Test
    public void case1() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(3);

        Assertions.assertEquals(7, solution.rob(root));
    }


    enum Policy {
        DP,
        ;
    }

    public static class DP implements Solution {

        Map<TreeNode, Integer> selectedMaxProfits = new HashMap<>();
        Map<TreeNode, Integer> unSelectedMaxProfits = new HashMap<>();

        @Override
        public int rob(TreeNode root) {
            return Math.max(max(root, true), max(root, false));
        }

        public int max(TreeNode root, boolean selectable) {
            if (root == null) return 0;
            if (selectable ? selectedMaxProfits.get(root) != null : unSelectedMaxProfits.get(root) != null)
                return selectable ? selectedMaxProfits.get(root) : unSelectedMaxProfits.get(root);

            if (selectable) {
                int maxOfSelected = max(root.left, false) + max(root.right, false) + root.val;
                int maxOfUnSelected = Math.max(max(root.left, true), max(root.left, false)) + Math.max(max(root.right, true), max(root.right, false));
                selectedMaxProfits.put(root, maxOfSelected);
                unSelectedMaxProfits.put(root, maxOfUnSelected);
                if (maxOfSelected > maxOfUnSelected) {
                    return maxOfSelected;
                } else if (maxOfSelected < maxOfUnSelected) {
                    return maxOfUnSelected;
                } else {
                    return maxOfSelected;
                }
            } else {
                int ret = Math.max(max(root.left, false), max(root.left, true)) + Math.max(max(root.right, false), max(root.right, true));
                unSelectedMaxProfits.put(root, ret);
                return ret;
            }
        }
    }


    interface Solution {
        /**
         * <pre>树的节点数在 [1, 10<sup>4</sup>] 范围内</></pre>
         * <pre>0 <= Node.val <= 10<sup>4</sup></pre>
         *
         * @param root
         * @return
         */
        int rob(TreeNode root);
    }

    public class TreeNode {
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

