package leetcode._300;

import cn.hutool.core.collection.ListUtil;
import leetcode.LeetCodeConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/different-ways-to-add-parentheses/">241. 为运算表达式设计优先级</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.DynamicPrograming,
        LeetCodeConstant.TagEnum.Array,
})
public class _241DifferentWaysToAddParentheses {


    private Solution solution;

    @BeforeEach
    public void loadSolution() {
        solution = Factory.getSolution(Policy.DP);
    }


    @Test
    public void case0() {
        List<Integer> ret = solution.diffWaysToCompute("2-1-1");
        HashSet<Object> except = new HashSet<>();
        except.add(0);
        except.add(2);
        Assertions.assertEquals(except, new HashSet<>(ret));
    }

    @Test
    public void case1() {
        List<Integer> ret = solution.diffWaysToCompute("2*3-4*5");
        HashSet<Object> except = new HashSet<>();
        // -34,-14,-10,-10,10
        except.add(-34);
        except.add(-14);
        except.add(-10);
        except.add(10);
        Assertions.assertEquals(except, new HashSet<>(ret));
    }






    enum Policy {
        DP,
        ;
    }


    public static class DP implements Solution {



        List<Integer> ret = new ArrayList<>() ;

        @Override
        public List<Integer> diffWaysToCompute(String expression) {
            List<Character> operators = new LinkedList<>();
            List<Integer> operands = new LinkedList<>();
            for (char c : expression.toCharArray()) {
                if (c == '+' || c == '-' || c == '*') {
                    operators.add(c);
                } else {
                    operands.add((int) c-48);
                }
            }

            _do(operators, operands);

            return ret;
        }

        public void _do(List<Character> operators, List<Integer> operands) {
            if (operators.isEmpty()){
                ret.add(operands.get(0));
                return;
            }

            for (int i = 0; i < operators.size(); i++) {
                int oped = op(operands.get(i), operands.get(i + 1), operators.get(i));
                LinkedList<Character> operators2 = new LinkedList<>(operators);
                operators2.remove(i);
                LinkedList<Integer> operands2 = new LinkedList<>(operands);
                operands2.remove(i);
                operands2.remove(i);
                operands2.add(i,oped);
                _do(operators2, operands2);
            }

        }

        private int op(int a, int b, char op) {
            switch (op) {
                case '+':
                    return a + b;
                case '-':
                    return a - b;
                case '*':
                    return a * b;
                default:
                    throw new UnsupportedOperationException();
            }
        }

    }


    interface Solution {
        /**
         * 1 <= expression.length <= 20
         * expression 由数字和算符 '+'、'-' 和 '*' 组成。
         * 输入表达式中的所有整数值在范围 [0, 99]
         *
         * @param expression
         * @return
         */
        List<Integer> diffWaysToCompute(String expression);
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
