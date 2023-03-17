package book.fengkuang.unit18_reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

/**
 * 简单反射使用
 * @author zpq5935
 *
 */
public class ReflectSimpleTest {

	@Test
	public void testMethod() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException, NoSuchMethodException, SecurityException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(10);
		System.out.println(list);
		// 通过反射获取ArrayList集合的字节码对象
		Class clazz = Class.forName("java.util.ArrayList");
		// 通过反射获取add方法
		Method addMethod = clazz.getMethod("add", Object.class);
		// 通过反射调用addMethod方法
		addMethod.invoke(list, "reflect is very good!");
		System.out.println(list);
	}

	/***************************************************************************/
	/************ 测试Field **************/
	/***************************************************************************/
	// 写一个方法，此方法可将obj对象中名为propertyName的属性的值设置为value.
	private String name = "初始化";

	public void setProperty(Object obj, String propertyName, Object value)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Class clazz = obj.getClass();
		Field field = clazz.getDeclaredField(propertyName);
		field.setAccessible(true);
		field.set(obj, value);
	}

	@Test
	public void testSetField()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		ReflectSimpleTest obj = new ReflectSimpleTest();
		System.out.println(obj.name);
		setProperty(obj, "name", "反射后：zpq");
		System.out.println(obj.name);
	}

	@Test
	public void testGetField() {
		Class clazz = TestClass.class;
		Field[] fields = clazz.getFields();
		System.out.println(Arrays.toString(fields));
		Field[] declaredFields = clazz.getDeclaredFields();
		System.out.println(Arrays.toString(declaredFields));
		//
		System.out.println("全限定类名："+clazz.getName());
		System.out.println(clazz.getSimpleName());
		//
	}

	/***************************************************************************/
	/**
	 * 操作数组
	 */
	@Test
	public void testArray() {
		Object array = Array.newInstance(String.class, 10);
		int len = ((String[]) array).length;
		Array.set(array, 0, "zcp");
		Array.set(array, len - 1, "zcp");
		for (String s : (String[]) array) {
			System.out.print(s + " ");
		}
	}
	@Test
	public void testClass() {
		Class clazz = TestClass.class;
		System.out.println("当前类："+clazz);
		System.out.println("外部类："+clazz.getDeclaringClass());
		System.out.println("父类类："+clazz.getSuperclass());
		System.out.println("内部类："+Arrays.toString(clazz.getDeclaredClasses()));
		System.out.println("实现全部接口："+Arrays.toString(clazz.getInterfaces()));
		
	}

	class TestClass extends HashMap implements AutoCloseable {
		public String name1;
		private String name2;
		@Override
		public void close() throws Exception {
			// TODO Auto-generated method stub
			
		}
	}
}
