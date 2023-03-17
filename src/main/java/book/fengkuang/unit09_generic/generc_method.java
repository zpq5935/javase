package book.fengkuang.unit09_generic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class generc_method {

	/**
	 * 这种方法签名上使用泛型是不好的，无论是 List<Object>:
	 * List<Object>并不是List<String>等的父类，故不能转型只能实参也为List<Object>型的 List<?>:
	 * 类型没指定，故不能给容器添加元素 都有一定缺陷
	 * 
	 * @param list
	 */
	public void t1_0(List<?> list) {
		// list.add(new Integer(12));//出错，类型没指定，故不能给容器添加元素
	}

	/**
	 * 正确的泛型方法签名
	 */
	public <T> void t1_1(List<T> list, T[] ts) {
		System.out.println("原始数据：");
		for (T o : list) {
			System.out.print(o + " ");
		}
		System.out.println("\n添加数据后：");
		for (T o : ts) {
			list.add(o);
		}
		for (T o : list) {
			System.out.print(o + " ");
		}
	}

	@Test
	public void t1() {
		List<String> list = new ArrayList<>();
		list.add("zpq");
		list.add("章朝佩");
		list.add("照片墙");
		String[] strings = { "wee", "大三大四" };
		t1_1(list, strings);
	}

	/************************************普通方法中使用*************************************************/
	/**
	 * 泛型方法与类型统配符的区别： 1.当方法的返回值或参数中的类型形参依赖于另一个类型形参时，不应当使用类型通配符 而 应该使用泛型方法。
	 * 2.当某一类型形参S只使用一次而其他均不依赖它时就可以使用通配符 3.通配符可使用在定义变量时
	 */
	@Test
	public void t2() {
		List<? extends Number> list = new ArrayList<>();
		List<Double> list2 = new ArrayList<>();
		list2.add(1.23);
		list2.add(123.23);
		list2.add(1.244453);
		list = list2;
		System.out.println(list2);
		System.out.println(list);
	}
	/***************************************构造器中使用**********************************************/
	public <T> generc_method(T t){
		System.out.println(t);
	}
	/********泛型与数组，乱七八糟的看不懂***********/
}
