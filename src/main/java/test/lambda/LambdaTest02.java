package test.lambda;

import javax.swing.*;

/**
 * java8 java.util.function 包下定义了大量的函数式接口<br></>
 * Lambda 表达式引用
 */
public class LambdaTest02 {
    public static void main(String[] args) {
        Converter converter = Integer::valueOf;
        Index index = "我是zpq"::indexOf;
        SubString subString = String::substring;
        GetJFrame getJFrame = JFrame::new;
    }

}

/**
 * 引用类方法
 */
@FunctionalInterface
interface Converter {
    Integer convert(String from);
}

/**
 * 引用特定对象的实例方法
 */
@FunctionalInterface
interface Index {
    Integer index(String subString);
}

/**
 * 引用某类对象的实例方法
 */
@FunctionalInterface
interface SubString {
    String subString(String oriString, int start, int end);
}

/**
 * 引用构造器
 */
@FunctionalInterface
interface GetJFrame {
    JFrame get(String title);
}