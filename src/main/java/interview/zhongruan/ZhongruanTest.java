package interview.zhongruan;

import java.util.Arrays;

public class ZhongruanTest {
    public static void main(String[] args) {
        ZhongruanTest main = new ZhongruanTest();
        main.solve1("i am zpq");
        //
        main.solve2("I like beijing.");
        //
        main.solve3("I like beijing.");
    }

    /* 1.输入一个字符串，长度小于等于200，然后将输出按字符顺序升序排序后的字符串。 */
    public void solve1(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars)
            System.out.print(c + ":" + (int) c + " ");
        Arrays.sort(chars);
        System.out.println(chars);
    }

    /* 2.将一句话的单词进行倒置，标点不倒置。比如 I like beijing. 经过函数后变为：beijing. like I */
    public void solve2(String str) {
        String[] words = str.split(" ");
        StringBuffer buffer = new StringBuffer();
        for (int i = words.length - 1; i >= 0; i--) {
            buffer.append(words[i]);
            if (i != 0)
                buffer.append(" ");
        }
        System.out.println(buffer.toString());
    }

    /*
     * 3.将一个字符串str的内容颠倒过来，并输出。str的长度不超过100个字符。 如：输入“I am a student”，输出“tneduts a
     * ma I”。
     */
    public void solve3(String str) {
        System.out.println(new StringBuffer(str).reverse().toString());
    }

    /* 4.写出一个程序，接受一个字符串，然后输出该字符串反转后的字符串。 */
    public void solve4() {
    }

    /*
     * 5.输入一个整数，将这个整数以字符串的形式逆序输出
     * 
     * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
     */
    public void solve5() {
    }
}
