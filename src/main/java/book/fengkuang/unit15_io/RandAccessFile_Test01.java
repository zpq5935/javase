package book.fengkuang.unit15_io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import org.junit.Test;

public class RandAccessFile_Test01 {

	/**
	 * 测试RandomAccesFile
	 */
	@Test
	public void t1() {
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile("src/test_unit15_IO/RandAccessFile_Test01.java",
					"r");

			System.out.println("文件指针：" + randomAccessFile.getFilePointer());
			randomAccessFile.seek(300);
			byte buffer[] = new byte[30];
			int readNum = 0;
			readNum = randomAccessFile.read(buffer);
			System.out.println("读取到：" + new String(buffer, 0, readNum));

			randomAccessFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 打印当前路径
	 */
	@Test
	public void t2() {
		File file = new File("");
		System.out.println("absolute pathname string of this abstract pathname:	" + file.getAbsolutePath());
		System.out.println("name of the file or directory denoted by this abstract pathname:	" + file.getName());
	}

	/**
	 * 利用RandomAccessFile给文件追加内容
	 */
	@Test
	public void t3() {
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile("out.txt", "rw");
			byte buffer[] = new byte[30];
			int hasRead = 0;
			System.out.println("原文件：");
			while (randomAccessFile.getFilePointer() != randomAccessFile.length()) {
				hasRead = randomAccessFile.read(buffer);
				System.out.println(new String(buffer, 0, hasRead));
			}
			Scanner scanner = new Scanner(System.in);
			String line = null;
			randomAccessFile.write("\r\n".getBytes());
			while (scanner.hasNextLine()) {
				if (!(line = scanner.nextLine()).equals("exit")) {
					System.out.println(line);
					randomAccessFile.seek(randomAccessFile.length());
					randomAccessFile.write(line.getBytes());
				} else
					break;

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
