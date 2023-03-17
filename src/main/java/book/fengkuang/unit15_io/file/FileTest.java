package book.fengkuang.unit15_io.file;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class FileTest {
	/**
	 * 
	 * @throws IOException
	 */
	@Test
	public void t1() throws IOException {
		File file = new File("sett.txt");
		File file2 = new File(".");
		if (!file.exists()) {
			System.out.println(file + "文件不存在！");
		} else {
			System.out.println(file.getName());
			System.out.println(file.getPath());
			System.out.println(file.getParent());
			System.out.println(file.isDirectory() + " && " + file.isFile());
			System.out.println(file.isAbsolute());
			System.out.println(file.length());
		}
		if (!file2.exists()) {
			System.out.println(file2 + "文件不存在！");
		} else {
			System.out.println(file2.getAbsolutePath());
			System.out.println(file2.getAbsoluteFile().getParent());
			File temp1 = File.createTempFile("zpqq", null, file2);
			temp1.deleteOnExit();
			//
			String[] files = file2.list();
			System.out.println("-----------当前路径-------------");
			for (String s : files) {
				System.out.println(s);
			}
			File[] rootfiles = File.listRoots();
			System.out.println("-----------根路径-------------");
			for (File f : rootfiles) {
				System.out.println(f);
			}
		}
	}

	/**
	 * createNewFile()方法
	 */
	@Test
	public void t2() {
		File file = new File("zpq.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * FilenameFilter过滤器
	 */
	@Test
	public void testFileNameFilter() {
		File file = new File(".");
		String[] files = file.list((dir, name) -> name.endsWith(".java") || new File(name).isDirectory());// 返回true就返回该文件
		for (String string : files) {
			System.out.println(string);
		}
	}
}
