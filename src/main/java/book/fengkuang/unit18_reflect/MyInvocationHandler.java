package book.fengkuang.unit18_reflect;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Person {
	void walk();

	void say(String msg);
}

/**
 * 测试jdk自带的动态代理
 * 
 * @author zcp
 *
 */
public class MyInvocationHandler implements InvocationHandler {
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("代理类：" + proxy.getClass());
		System.out.println("方法：" + method);
		if (args != null) {
			System.out.println("参数：" + args);
		} else {
			System.out.println("无参");
		}
		return null;
	}

	public static void main(String[] args) {
		Class clazz = Proxy.getProxyClass(Person.class.getClassLoader(), new Class[]{Person.class});
		//
		MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
		Person person = (Person) Proxy.newProxyInstance(myInvocationHandler.getClass().getClassLoader(),
				new Class[] { Person.class }, myInvocationHandler);
		person.walk();
		person.say("hey zpq");
	}
}
