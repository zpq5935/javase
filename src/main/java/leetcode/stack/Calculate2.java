package leetcode.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * 四则运算
 */
public class Calculate2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expr = in.nextLine();
        int result = calculateComplex(expr);
        System.out.println(result);

    }


    /**
     * 立马计算
     *
     * @param numbers
     * @param op
     * @param curNum
     */
    public static void cal2(Stack<Integer> numbers, char op, int curNum) {
        switch (op) {
            case '+':
                numbers.push(numbers.pop() + Integer.valueOf(curNum));
                break;
            case '-':
                numbers.push(numbers.pop() - Integer.valueOf(curNum));
                break;
            case '*':
                numbers.push(curNum * numbers.pop());
                break;
            case '/':
                numbers.push(numbers.pop() / curNum);
                break;
            default:
                break;
        }
    }


    /**
     * 包含 + - * / ()
     * 逆波兰表达式 后缀表达式
     *
     * @param expr
     * @return
     */
    private static int calculateComplex(String expr) {
        expr = expr.replace(" ", "");
        expr = transfer(expr);
        String[] split = expr.split(",");
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
//        int length = expr.length();
        for (String nS : split) {
            if (isOp(nS)) cal2(numStack, nS.charAt(0), numStack.pop());
            else numStack.push(Integer.valueOf(nS));
        }
        return numStack.pop();
    }

    private static boolean isOp(String input) {
        return input.matches("[\\+\\-\\*\\/]");
    }

    /**
     * 将中缀表达式转换为后缀表达式（逆波兰表达式）
     *
     * @param express
     * @return
     */
    public static String transfer(String express) {
        Stack<String> stack = new Stack<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < express.length(); i++) {
            if ((express.charAt(i) + "").matches("\\d")) {
                list.add(express.charAt(i) + "");
            } else if ((express.charAt(i) + "").matches("[\\+\\-\\*\\/]")) {
                //如果stack为空
                if (stack.isEmpty()) {
                    stack.push(express.charAt(i) + "");
                    continue;
                }
                //不为空

                //上一个元素不为（，且当前运算符优先级小于上一个元素则，将比这个运算符优先级大的元素全部加入到队列中
                while (!stack.isEmpty() && !stack.lastElement().equals("(") && !comparePriority(express.charAt(i) + "", stack.lastElement())) {
                    list.add(stack.pop());
                }
                stack.push(express.charAt(i) + "");
            } else if (express.charAt(i) == '(') {
                //遇到左小括号无条件加入
                stack.push(express.charAt(i) + "");
            } else if (express.charAt(i) == ')') {
                //遇到右小括号，则寻找上一堆小括号，然后把中间的值全部放入队列中
                while (!("(").equals(stack.lastElement())) {
                    list.add(stack.pop());
                }
                //上述循环停止，这栈顶元素必为"("
                stack.pop();
            }
        }
        //将栈中剩余元素加入到队列中
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        StringBuffer stringBuffer = new StringBuffer();
        //变成字符串
        for (String s : list) {
            stringBuffer.append(s).append(",");
        }
        return stringBuffer.toString();
    }

    /**
     * 比较运算符的优先级
     *
     * @param o1
     * @param o2
     * @return
     */
    public static boolean comparePriority(String o1, String o2) {
        return getPriorityValue(o1) > getPriorityValue(o2);
    }

    /**
     * 获得运算符的优先级
     *
     * @param str
     * @return
     */
    private static int getPriorityValue(String str) {
        switch (str) {
            case "+":
                return 1;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 2;
            default:
                throw new RuntimeException("没有该类型的运算符！");
        }
    }
}
