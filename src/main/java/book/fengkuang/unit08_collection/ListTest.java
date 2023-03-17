package book.fengkuang.unit08_collection;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

public class ListTest {
	
	/**
	 * 测试Lambda 表达式下的
	 * replaceAll 方法与sort 方法
	 */
	@Test
	public void test01(){
		List<String> list = new ArrayList<>();
		list.add("疯狂Java讲义");
		list.add("C++程序设计");
		list.add("线性代数");
		System.out.println(list);
		//
		list.sort((o1,o2)->
			((String)o1).length() - ((String)o2).length() );
		System.out.println(list);
		//
		list.replaceAll((ele)->
			String.valueOf(ele.length()));
		System.out.println(list);
	}
	
	/**
	 * 测试ListIterator
	 */
	@Test
	public void test02(){
		List<String> list = new ArrayList<>();
		list.add("疯狂Java讲义");
		list.add("C++程序设计");
		list.add("线性代数");
		ListIterator<String> listiterator = list.listIterator();
		while(listiterator.hasNext())
			System.out.println(listiterator.next());
		System.out.println("-------反向遍历-------");
		while(listiterator.hasPrevious())
			System.out.println(listiterator.previous());
	}
	
	/**
	 * 测试Arrays.ArrayList
	 */
	@Test
	public void test03(){
		
	}
}
