package book.fengkuang.unit06_oo2;

import org.junit.Test;

public class FinalTest {
    public static String name;

    @Test
    public void test01() {
        String s1 = "疯狂Java";
        String s2 = "疯狂" + "Java";
        String s3 = s1 + s2;
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        //
        String address = "大萨达多";
//        address = ""; // 注释掉会出错
        Inner inner = new Inner() {
            @Override
            public void doSomeThing() {
                System.out.println(address);
            }
        };
        inner.doSomeThing();
    }

    public abstract class Inner {
        public String name = "zpq";

        public void doSomeThing() {
        };
    }
}
