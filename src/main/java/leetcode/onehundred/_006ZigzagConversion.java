package leetcode.onehundred;

import leetcode.LeetCodeConstant;

/**
 * <a href="https://leetcode.cn/problems/zigzag-conversion/">6. N 字形变换</a>
 */
@LeetCodeConstant.Tag(tagEnums = {
        LeetCodeConstant.TagEnum.String,
})
public class _006ZigzagConversion {

    public static void main(String[] args) {
        String outString = convert(Policy.Sol1, "PAYPALISHIRING", 3);
        System.out.println(outString);
        System.out.println(outString.equals("PAHNAPLSIIGYIR"));
    }


    /**
     * 上下、左右 排列（奇怪排列方式）【指定固定行数】；
     * 排完结束后重新按照 左右上下读取（正常阅读模式）
     */
    /**
     * 1 <= s.length <= 1000
     * s 由英文字母（小写和大写）、',' 和 '.' 组成
     * 1 <= numRows <= 1000
     */
    public static String convert(Policy policy, String s, int numRows) {
        return Factory.getSolution(policy).convert(s, numRows);
    }


    enum Policy {
        Sol1,
        ;
    }

    public static class Sol1 implements Solution {

        @Override
        public String convert(String s, int numRows) {
            char[][] xys = new char[numRows][s.length()];
            int curRow = 0;
            boolean down = true;
            int[] colIndex = new int[numRows];
            for (int i = 0; i < numRows; i++) {
                colIndex[i] = 0;
            }

            for (int i = 0; i < s.length(); i++) {
                xys[curRow][colIndex[curRow]] = s.charAt(i);

                colIndex[curRow]++;
                if (down) {
                    curRow++;
                    if (curRow == numRows) {
                        curRow = numRows >= 2 ? numRows - 2 : 0;
                        down = false;
                    }
                } else {
                    curRow--;
                    if (curRow < 0) {
                        curRow = numRows > 1 ? 1 : 0;
                        down = true;
                    }
                }
            }

            StringBuilder ret = new StringBuilder();
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < colIndex[i]; j++) {
                    ret.append(xys[i][j]);
                }
            }

            return ret.toString();
        }
    }

    interface Solution {
        String convert(String s, int numRows);
    }


    public static class Factory {

        public static Solution getSolution(Policy policy) {
            switch (policy) {
                case Sol1:
                    return new Sol1();
                default:
                    throw new UnsupportedOperationException();
            }
        }

    }


}
