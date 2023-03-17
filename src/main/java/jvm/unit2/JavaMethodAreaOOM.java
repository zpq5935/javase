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
     * -XX:PermSize=10M -XX:MaxPermSize=10M
     * -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M jdk8 永久代
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
