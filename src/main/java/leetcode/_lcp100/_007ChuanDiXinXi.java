package leetcode._lcp100;

import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;


/**
 * <a href="https://leetcode.cn/problems/chuan-di-xin-xi/">LCP 07. 传递信息</a>
 */

@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.Array,
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.DFS,
        LeetCodeConstant.TagEnum.BFS,
})
public class _007ChuanDiXinXi {


    private Solution solution;

    @BeforeEach
    public void loadSolution(){
        solution = Factory.getSolution(Policy.DP);
    }


    @Test
    public void case1(){
        int[][] relation = {{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}};
        Assertions.assertEquals(3, solution.numWays(5, relation, 3));
    }

    @Test
    public void case2(){
        int[][] relation = {{0, 2}, {2, 1}};
        Assertions.assertEquals(0, solution.numWays(3, relation, 2));
    }


    @Test
    public void case3(){
        int[][] relation = {{0, 2}, {0, 1}, {1, 2}, {2, 1}, {2, 0}, {1, 0}};
        Assertions.assertEquals(1, solution.numWays(3, relation, 1));
    }


    enum Policy {
        DFS,
        DP,
        ;
    }

    public static class DP implements  Solution{
        @Override
        public int numWays(int n, int[][] relation, int k) {
            int[][] dp = new int[k + 1][n];
            dp[0][0] = 1;
            for (int i = 0; i < k; i++) {
                for (int[] edge : relation) {
                    int src = edge[0], dst = edge[1];
                    dp[i + 1][dst] += dp[i][src];
                }
            }
            return dp[k][n - 1];
        }
    }

    public static class DFS implements Solution {

        @Override
        public int numWays(int n, int[][] relation, int k) {
            Map<Integer, List<Integer>> relationMap = new HashMap<>();
            for (int[] ab : relation) {
                List<Integer> routes = relationMap.getOrDefault(ab[0], new ArrayList<>());
                routes.add(ab[1]);
                relationMap.put(ab[0], routes);
            }

            return _numWays(0, n, relationMap, k);
        }

        private int _numWays(int begin,int n, Map<Integer, List<Integer>> relation, int k) {
            List<Integer> routes = relation.get(begin);
            if (routes == null) {
                return 0;
            }

            if (k == 1) {
                return (int) routes.stream().filter(route -> Objects.equals(route, n - 1)).count();
            }

            int planNums = 0;
            for (Integer route : routes) {
                planNums += _numWays(route, n, relation, k - 1);
            }
            return planNums;
        }
    }

    interface Solution {
        /**
         * 2 <= n <= 10
         * 1 <= k <= 5
         * 1 <= relation.length <= 90, 且 relation[i].length == 2
         * 0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
         * @param n 玩家人数
         * @param relation 单向传递信息组合
         * @param k 轮数
         * @return 方案数
         */
        int numWays(int n, int[][] relation, int k);
    }


    public static class Factory {

        private static final Map<Policy, Solution> solutionMap = new HashMap<>();

        static {
            solutionMap.put(Policy.DFS, new DFS());
            solutionMap.put(Policy.DP, new DP());
        }

        public static Solution getSolution(Policy policy) {
            return solutionMap.get(policy);
        }

    }


}
