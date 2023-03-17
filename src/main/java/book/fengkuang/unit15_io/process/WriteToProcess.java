package book.fengkuang.unit15_io.process;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * 好吧T_T，貌似失败了
 * 
 * @author zcp
 *
 */

public class WriteToProcess {

	public static void main(String[] args) {
		System.out.println("1");
		Process process;
		try {
			process = Runtime.getRuntime().exec("java book.unit15_io.process.AcceptProcess");
			if (process == null) {
				System.out.println("null");
				System.exit(0);
			}
			System.out.println("2");
			PrintStream pStream = new PrintStream(process.getOutputStream());
			pStream.println("字符创AAAaaa");
			pStream.println(new WriteToProcess());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

class AcceptProcess {
	public static void main(String[] args) {
		try {
			System.out.println("Start:");
			Scanner scanner = new Scanner(System.in);
			PrintStream out = new PrintStream("out_ProcessCommunication.txt");
			out.println("开始：");
			int lineNum = 0;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				System.out.println(line);
				out.println("行:" + (lineNum++) + "|" + line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
