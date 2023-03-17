package leetcode.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * 四则运算
 */
public class Calculate {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expr = in.nextLine();
        int result = calculateSimple(expr);
        System.out.println(result);

    }

    /**
     * 只考虑 + - * /
     * 无 () [] {}
     *
     * @param expr
     * @return
     */
    private static int calculateSimple(String expr) {
        expr = expr.replace(" ", "");
        Stack<Integer> numStack = new Stack();
        char lastOp = '+';
        StringBuffer curNumber = new StringBuffer();
        for (char nC : expr.toCharArray()) {
            if (nC >= '0' && nC <= '9') {
                curNumber.append(nC);
            } else {
                cal(numStack, lastOp, Integer.valueOf(curNumber.toString()));
                lastOp = nC;
                curNumber = new StringBuffer();
            }
        }
        if (curNumber.length() > 0)
            cal(numStack, lastOp, Integer.valueOf(curNumber.toString()));

        int ret = 0;
        while (!numStack.empty())
            ret += numStack.pop();
        return ret;
    }

    public static void cal(Stack<Integer> numbers, char op, int curNum) {
        switch (op) {
            case '+':
                numbers.push(Integer.valueOf(curNum));
                break;
            case '-':
                numbers.push(-Integer.valueOf(curNum));
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

}
