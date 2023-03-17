package book.fengkuang.unit18_reflect.jdkproxy;

import java.lang.reflect.Proxy;

public class ProxyFactory {
	public static Object getProxyObject(Object target) {
		MyInvocationHandler myInvocationHandler = new MyInvocationHandler(target);
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                myInvocationHandler);
	}
}
