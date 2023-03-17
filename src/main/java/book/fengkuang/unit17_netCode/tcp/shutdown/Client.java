package book.fengkuang.unit17_netCode.tcp.shutdown;

import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost",3002);
		Scanner scanner = new Scanner(socket.getInputStream());
		while (scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
		System.out.println("client over");
	}
}
