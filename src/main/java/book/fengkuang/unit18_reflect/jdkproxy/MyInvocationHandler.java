package book.fengkuang.unit18_reflect.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
	private Object target;

	public MyInvocationHandler(Object target) {
		super();
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		PersonUtil.drinkMilk();
		Object result = method.invoke(target, args);
		PersonUtil.getMoney();
		return result;
	}

}
