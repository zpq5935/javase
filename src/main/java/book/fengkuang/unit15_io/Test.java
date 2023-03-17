package book.fengkuang.unit15_io;

import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Test {
	public static void main(String[] args) {
		Test main = new Test();
//		new Thread(() -> {
//			main.testPrintStream();
//		}).start();
		//
		 new Thread(()->{
		 main.testPrintWriter();
		 }).start();
	}

	/**
	 * 
	 * @Description 与PrinterWriter比较，似乎可以直接刷出数据
	 * @author zhangchaopei
	 * @date 2020年6月20日
	 */
	public void testPrintStream() {
		PrintStream stream = new PrintStream(System.out);
		stream.println("testPrintStream");
		int i = 0;

	}

	/**
	 * 
	 * @Description 与PrinterStram比较，似乎需要强制刷出数据
	 * @author zhangchaopei
	 * @date 2020年6月20日
	 */
	public void testPrintWriter() {
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));

		writer.println("testPrintWriter");
		writer.flush();
		int i = 0;
	}
}
