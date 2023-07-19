package leetcode;

public interface LeetCodeConstant {


    enum TagEnum {
        None,
        Array,
        Hash,
        Recursion,
        Math,
        LinkedList,
        String,
        SlidingWindow,/*滑动窗口*/
        /**
         * 【动态规划 = 递归 + 缓存（记忆性递归）】
         * 1、状态转移方程 [假设可以记忆存储某些数据，基于此进行推导可得最后结果；其中那个假设后续再进行证明]
         * 2、边界条件
         */
        DynamicPrograming,/*动态规划*/
        /**
         * 记忆化搜索
         */
        MemorySearch,
        /**
         * 递归
         */
        Dp,
        /**
         * 深度优先搜索（DepthFirstSearch）
         */
        DFS,
        /**
         * 广度优先搜索（BreadthFirstSearch）
          */
        BFS,
        ;
    }

    @interface Tag {
        TagEnum[] tagEnums() default TagEnum.None ;
    }

}
