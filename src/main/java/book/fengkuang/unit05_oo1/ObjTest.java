package book.fengkuang.unit05_oo1;

import org.junit.Test;

public class ObjTest {
	
	public void test11(int a, String ...strings){
		System.out.println("int:"+a);
		for(String s:strings){
			System.out.print(s);
		}
	}
	
	@Test
	public void test12(){
		test11(100, "我是","zpq,","我。。。");
	}
}
