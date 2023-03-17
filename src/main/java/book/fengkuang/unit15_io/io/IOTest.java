package book.fengkuang.unit15_io.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;

public class IOTest {
	/**
	 * unicode编码下的utf-8方案：一个字符可用1到4个字节表示，如'你','好'均用3字节表示
	 * 
	 * @throws IOException
	 */
	@Test
	public void testFileIs() throws IOException {
		FileInputStream fis = new FileInputStream("data.txt");
		byte[] buffer = new byte[1024];
		int hasRead = 0;
		int readTime = 0;
		while ((hasRead = fis.read(buffer)) > 0) {
			readTime++;
			System.out.println(new String(buffer, 0, hasRead));
		}
		System.out.println("读取次数：" + readTime);

		fis.close();
	}

	/**
	 * FileReader 文件-字符输入流
	 * 
	 * @throws IOException
	 */
	@Test
	public void testFileR() throws IOException {
		FileReader reader = new FileReader("src/book/unit15_io/io/IOTest.java");
		char[] buffer = new char[1024];
		int hasRead = 0;
		int readTime = 0;
		while ((hasRead = reader.read(buffer)) > 0) {
			readTime++;
			System.out.println(new String(buffer, 0, hasRead));
		}
		System.out.println("读取次数：" + readTime);

		reader.close();
	}

	/**
	 * FIleWriter-文件字符输出流
	 * 
	 * @throws IOException
	 */
	@Test
	public void testFileW() throws IOException {
		FileWriter writer = new FileWriter("poem.text");
		writer.write("锦瑟 - 李商隐\r\n");
		writer.write("锦瑟无端五十弦，一弦一柱思华年\n");
		writer.write("庄生晓梦迷蝴蝶，望帝春心托杜鹃\r\n");
		writer.write("沧海月明珠有泪，蓝田日暖玉生烟\r\n");
		writer.write("此情可待成追忆，只是当时已惘然\r\n");
		writer.close();
	}

	/**
	 * 字节流转字符流
	 */
	@Test
	public void testBufferedR() {
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader reader2 = new BufferedReader(reader);
		try {
			String line = null;
			int lineNum = 0;
			System.out.println("开始输入：");
			while (!(line = reader2.readLine()).equals("exit")) {

				System.out.println("第" + (lineNum++) + "行：" + line);
			}
			System.out.println("结束输入！");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/***
	 * 重定向-setOut(PrintStream)
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testPrintS() throws FileNotFoundException {
		PrintStream out = new PrintStream(new File("out.txt"));
		System.setOut(out);
		System.out.println("重定向首次测试");
		System.out.println(new IOTest());
	}

	/**
	 * 重定向setIn(InputStream)
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testScanner() throws FileNotFoundException {
		FileInputStream fileInputStream = new FileInputStream("src/test_unit15_IO/IO_Test01.java");
		System.setIn(fileInputStream);
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
	}

}
