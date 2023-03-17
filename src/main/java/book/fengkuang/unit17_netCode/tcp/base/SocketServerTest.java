package book.fengkuang.unit17_netCode.tcp.base;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerTest {
	public static void main(String[] args) throws Exception {
		System.out.println("服务端开启！");
		ServerSocket serverSocket = new ServerSocket(8080);
		while (true) {
			Socket accept = serverSocket.accept();
			System.out.println("连接+1："+accept);
			PrintStream outputStream = new PrintStream(accept.getOutputStream());
			Thread.sleep(2000);
			outputStream.print("这是来自服务端的问候！你好呀");
			outputStream.close();
			accept.close();
		}
	}
}
