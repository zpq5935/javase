package book.fengkuang.unit17_netCode.tcp.shutdown;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(3002);
		Socket accept = serverSocket.accept();
		PrintStream printStream = new PrintStream(accept.getOutputStream());
		printStream.println("第一行");
		printStream.println("第2行");
//		accept.shutdownOutput();
		System.out.println(accept.isClosed());
		Scanner scanner = new Scanner(accept.getInputStream());
		while (scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
		scanner.close();
		printStream.close();
		accept.close();
	}
}
