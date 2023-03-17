package book.fengkuang.unit08_collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class CollectionsTest {

	/**
	 * 测试Collections为List提供的方法
	 */
	@Test
	public void test_collectionsForList() {
		ArrayList<Integer> arrayList = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 10; i++)
			arrayList.add(random.nextInt(44));
		System.out.println("原生："+arrayList);
		//测试反转
		Collections.reverse(arrayList);
		System.out.println("反转："+arrayList);
		//测试调换
		Collections.swap(arrayList, 1, 2);
		System.out.println("调换："+arrayList);
		//测试旋转
		Collections.rotate(arrayList, -2);
		System.out.println("旋转："+arrayList);
		//测试洗牌
		Collections.shuffle(arrayList);
		System.out.println("水机洗牌："+arrayList);
	}
	
	/**
	 * 
	 */
	@Test
	public void testSynchroizedXxx(){
		Collection<Integer> c = Collections.synchronizedCollection(new ArrayList<>());
		List<String> list = Collections.synchronizedList(new ArrayList<>());
		Set<Double> set = Collections.synchronizedSet(new HashSet<>());
		Map<Integer, Double> map = Collections.synchronizedMap(new HashMap<>());
		System.out.println("OK!");
	}
}
