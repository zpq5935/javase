package leetcode._1100;

import leetcode.LeetCodeConstant;



/**
 * <a href="https://leetcode.cn/problems/divisor-game/">1025. 除数博弈</a>
 */

/**
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 *
 * 最初，黑板上有一个数字  n  。在每个玩家的回合，玩家需要执行以下操作：
 *
 * 选出任一  x，满足  0 < x < n  且  n % x == 0  。
 * 用 n - x  替换黑板上的数字  n 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 *
 * 只有在爱丽丝在游戏中取得胜利时才返回  true  。假设两个玩家都以最佳状态参与游戏
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/divisor-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.Array,
        LeetCodeConstant.TagEnum.DynamicPrograming,
})
public class _1025DivisorGame {



    public static void main(String[] args) {
        Solution solution = Factory.getSolution(Policy.Sol1);
        System.out.println(solution.divisorGame(2));
        System.out.println(solution.divisorGame(3));
        System.out.println(solution.divisorGame(4));
        System.out.println(solution.divisorGame(5));
    }



    enum Policy {
        Sol1,
        Math,
        ;
    }

    public static class Sol1 implements Solution {

        /**
         * win：2、4、6
         * lose：3、5、
         */
        @Override
        public boolean divisorGame(int n) {
            if (n == 1)
                return false;
            if (n == 2)
                return true;

            boolean[] flags = new boolean[n + 1];
            flags[1] = false;
            flags[2] = true;

            for (int i = 3; i <= n; i++) {
                for (int j = 1; j < i; j++) {
                    if (flags[j] && i % j == 0)
                        flags[i] = true;
                }
            }

            return flags[n];
        }
    }
    public static class MathSol implements Solution {

        /**
         * 奇数必输，偶数必赢；
         * 1、奇数的因子必为奇数，拿到奇数的人提供给对方的必为偶数
         * 2、偶数的因子必有1，减去1给对方奇数
         * 3、循环往复，最后偶数拿到2给对方1就赢了
         * @param n
         * @return
         */
        @Override
        public boolean divisorGame(int n) {
            return n % 2 == 0;
        }
    }

    interface Solution {

        /**
         * 1 <= n <= 1000
         */
        boolean divisorGame(int n);
    }


    public static class Factory {

        public static Solution getSolution(Policy policy) {
            switch (policy) {
                case Sol1:
                    return new Sol1();
                case Math:
                    return new MathSol();
                default:
                    throw new UnsupportedOperationException();
            }
        }

    }


}
