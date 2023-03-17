package book.school.bigDecimal;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class test_bigDecimal_01 {

	@Test
	public void test01(){
		System.out.println("0.05+0.01="+(0.05+0.01));
		System.out.println(1.0-0.56);
		System.out.println(4.015*100);
		System.out.println(123.3/100);
	}
	
	@Test
	public void test02(){
		BigDecimal f1 = new BigDecimal("0.05");
		BigDecimal f2 = BigDecimal.valueOf(0.01);
//		String string= "asd";string.length();
//		int[] a = new int[10];int b = a.length;
//		ArrayList<Integer> list1 = new ArrayList<>();list1.size();
		BigDecimal f3 = new BigDecimal(0.05);
		System.out.println( (Double) (f1.add(f2).doubleValue())  );
		System.out.println(f1.subtract(f2));
		System.out.println(f1.multiply(f2));
		System.out.println(f1.divide(f2));
		System.out.println("------------------");
		System.out.println(f3.add(f2));
		System.out.println(f3.subtract(f2));
		System.out.println(f3.multiply(f2));
		System.out.println(f3.divide(f2));
	}
	
	@Test
	public void t03(){
		BigDecimal mBigDecimal = new BigDecimal("1230");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("transAmt", mBigDecimal.setScale(2, BigDecimal.ROUND_HALF_DOWN));
		if(!map.isEmpty()){
			System.out.println(map);
		}
	}
}
