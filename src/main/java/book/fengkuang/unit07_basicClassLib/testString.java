package book.fengkuang.unit07_basicClassLib;

import org.junit.Test;

public class testString {

	@Test
	public void test01(){
		char[] charArr = new char[]{'h','e','l','l','o','你'};
		String s1 = new String(charArr);
		System.out.println(s1);
	}
}
