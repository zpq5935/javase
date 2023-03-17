package book.fengkuang.unit17_netCode.tcp.baseaddthread;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerMainThread {
	public static List<Socket> clientList = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(30000);
		while (true) {
			Socket socket = serverSocket.accept();
			clientList.add(socket);
			new Thread(new ServerReadMsgThread(socket)).start();
		}
	}
}
