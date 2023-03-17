package book.fengkuang.unit18_reflect.cglib;


import book.fengkuang.unit18_reflect.cglib.inner.OOMObject;
import net.sf.cglib.proxy.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 测试方法区 OutOfMemoryError
 */
public class CgLibTest {
    /**
     * -XX:PermSize=10M -XX:MaxPermSize=10M
     *
     * @param args
     */
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OOMObject.class);
        enhancer.setUseCache(false);
        Callback callback1 = new MethodInterceptor() {
            private String name = "callback1";

            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("callback1-before");
                Object result = methodProxy.invokeSuper(o, objects);
                System.out.println("callback1-end");
                return result;
            }
        };
        Callback callback2 = new MethodInterceptor() {
            private String name = "callback2";

            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("callback1-before");
                Object result = methodProxy.invokeSuper(o, objects);
                System.out.println("callback1-end");
                return result;
            }
        };
        enhancer.setCallbacks(new Callback[]{callback1, callback2, NoOp.INSTANCE});
        enhancer.setCallbackFilter((method) ->
                method.getName().length() % 2
        );
        OOMObject oomObject = (OOMObject) enhancer.create();
        Method sayMethod = OOMObject.class.getDeclaredMethod("say");
        Method sayMethod2 = OOMObject.class.getDeclaredMethod("say2");
//        oomObject.say();
        sayMethod.setAccessible(true);
        sayMethod.invoke(oomObject);
        sayMethod2.invoke(oomObject);
    }

}
