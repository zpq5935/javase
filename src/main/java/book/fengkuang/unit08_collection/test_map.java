package book.fengkuang.unit08_collection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

public class test_map {
	@Test
	public void test01(){
		Map<Object, Object> map = new HashMap<>();
		map.put(null, null);
		System.out.println(map);
		map.put(null, null);
		System.out.println(map);
		map.put("a", null);
		System.out.println(map);
		
		System.out.println(map.put("adc", "adc"));
	}
	
	
	
	/**
	 * 比较各种不同方法遍历的效率
	 * 1.keySet() 与 get():										71.0
	 * 2.foreach entrySet() 与 Entry 的getKey() 与 getValue():	38.0
	 * 3.迭代器 Iterator与entrySet结合使用							59.0
	 * 4.通过values()遍历value，但是无法遍历key						60.0
	 */
	@Test
	public void test02(){
		Map<Integer, String> map = new HashMap<>();
		for(int i=0; i<1000; i++)
			map.put(i, String.valueOf(i));
		
		//keySet() 与 get():
//		double timeStart1 = System.currentTimeMillis();
//		for(Integer key:map.keySet()){
//			System.out.println("key:"+key+",value:"+map.get(key));
//		}
//		double timeEnd1 = System.currentTimeMillis();
//		System.out.println("方法1.keySet() 与 get():"+"耗时："+(timeEnd1-timeStart1)+"毫秒");
		
		//foreach entrySet() 与 Entry 的getKey() 与 getValue():
//		double timeStart2 = System.currentTimeMillis();
//		for(Entry<Integer, String> entry:map.entrySet()){
//			System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
//		}
//		double timeEnd2 = System.currentTimeMillis();
//		System.out.println("方法1.foreach entrySet() 与 Entry 的getKey() 与 getValue()::"+"耗时："+(timeEnd2-timeStart2)+"毫秒");
		
		//迭代器 Iterator与entrySet结合使用
//		double timeStart3 = System.currentTimeMillis();
//		Iterator<Entry<Integer, String>> iterator= map.entrySet().iterator();
//		while(iterator.hasNext()){
//			Entry<Integer, String> entry = iterator.next();
//			System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
//		}
//		double timeEnd3 = System.currentTimeMillis();
//		System.out.println("方法3.迭代器 Iterator与entrySet结合使用:"+"耗时："+(timeEnd3-timeStart3)+"毫秒");
		
		//通过values()遍历value，但是无法遍历key
		double timeStart4 = System.currentTimeMillis();
		for(String value:map.values()){
			System.out.println("value:"+value);
		}
		double timeEnd4 = System.currentTimeMillis();
		System.out.println("方法4.通过values()遍历value，但是无法遍历key:"+"耗时："+(timeEnd4-timeStart4)+"毫秒");
	}
	
	
	/**
	 * 测试Properties 读写属性文件
	 */
	@Test
	public void test03(){
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("resource/jdbc.properties"));
			System.out.println(properties.getProperty("jdbcurl"));
			System.out.println(properties);
			properties.setProperty("name", "章佩奇");
			properties.store(new FileOutputStream("resource/jdbc2.properties"), "123");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
