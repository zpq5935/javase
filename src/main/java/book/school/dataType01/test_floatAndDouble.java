package book.school.dataType01;

import org.junit.Test;

public class test_floatAndDouble {
	
	@Test
	public void test01(){
		float f1 = 5.23455556f;
		System.out.println(f1);
		System.out.println(Double.NEGATIVE_INFINITY);
	}
	
	@Test
	public void test02(){
		String str1 = "";
		for(int i=0; i<5; i++){
			int intVal = (int)(Math.random()*26+97);
			str1 += (char)intVal;
		}
		System.out.println(str1);
	}
	
	
	@Test
	public void test03(){
		System.out.println('a'+7+"hello");
		System.out.println(-5.0 % 0.0);
	}
}
