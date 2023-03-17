package book.fengkuang.unit09_generic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
/**
 * Java泛型设计的原则是：在编译器不出错则在运行就不会出现ClassCastException
 * @author zcp
 *
 */
public class generc_class {
	
	
	
	@Test
	public void t1(){
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("sb");
		if(arrayList instanceof ArrayList){
			System.out.println("1");
		}else{
			System.out.println("2");
		}
	}
	/*****************/
	public void t2_1(List<?> list){
		System.out.println("长度："+list.size()+"遍历：");
		for(Object o:list){
			System.out.print(o+" ");
		}
	}
	/**
	 * List<String 并不是 List<Object> 的子类，
	 * 但是数组不同，子类的数组仍是父类数组的子类
	 */
	@Test
	public void t2_2(){
		List<Integer> list = new ArrayList<>();
		for(int i=0;i<10;i++){
			list.add(i);
		}
		//List<String> 并不是 List<Object> 的子类
		t2_1(list);
	}
	/*****************/
}
