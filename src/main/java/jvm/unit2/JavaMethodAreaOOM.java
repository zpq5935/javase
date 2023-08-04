package jvm.unit2;


import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试 方法区 OutOfMemoryError
 */
public class JavaMethodAreaOOM {
    /**
     * HotSpot虚拟机在jdk8前使用永久代（堆内，使得可以像管理堆一样来管理方法区）来实现方法区，jdk8开始使用元空间Metaspace（堆外内存）
     * 字符串常量池也是，自jdk7起，之前在永久代内会受到 PermSize 限制，后面直接放到堆里面了
     * -XX:PermSize=10M -XX:MaxPermSize=10M
     * -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
     *
     * @param args
     */
    public static void main(String[] args) {
        while (true) {
//            System.out.println(1);
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            Callback callback1 = new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invoke(o, objects);
                }
            };
            enhancer.setCallback(callback1);
            enhancer.create();
        }
    }

    static class OOMObject {
    }

    /**
     * -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
     */
    public static void main2(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true)
            list.add(String.valueOf(i++).intern());
    }
}
