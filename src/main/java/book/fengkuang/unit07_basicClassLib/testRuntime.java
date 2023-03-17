package book.fengkuang.unit07_basicClassLib;

import java.io.IOException;

import org.junit.Test;

public class testRuntime {

	@Test
	public void test01() throws IOException{
		Runtime runtime = Runtime.getRuntime();
		System.out.println(runtime.getRuntime());
		System.out.println("freeMemory:"+runtime.freeMemory());
		System.out.println("maxMemory:"+runtime.maxMemory());
		//运行计算器
//		runtime.exec("calc.exe");
	}
}
