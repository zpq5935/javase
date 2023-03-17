package jvm.unit07;

import org.junit.Test;

class SuperClass1 {
    static {
        System.out.println("SuperClass1 init");
    }

}

class SuperClass extends SuperClass1 {
    static {
        System.out.println("Super init");
    }

    public static int value = 123;
    public final static int VAL_F = 223;
}

class Sub extends SuperClass {
    static {
        System.out.println("Sub init");
    }
}

public class Test1 {
    /**
     * 对于静态字段，只有定义该字段的类会被初始化，子类不会
     */
    @Test
    public void t1() {
        System.out.println(Sub.value);

    }

    /**
     * 字面量-static+final（编译器存入Test1的class文件的常量池，后续使用其并不会与Sub、Super有联系）
     */
    @Test
    public void t2() {
        System.out.println(Sub.VAL_F);

    }
}
