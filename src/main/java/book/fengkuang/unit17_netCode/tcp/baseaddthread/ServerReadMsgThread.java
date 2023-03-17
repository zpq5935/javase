package book.fengkuang.unit17_netCode.tcp.baseaddthread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerReadMsgThread implements Runnable {
	private Socket socket;
	private BufferedReader reader;

	public ServerReadMsgThread(Socket socket) throws IOException {
		super();
		this.socket = socket;
		this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	@Override
	public void run() {
		try {
			String mString;
			while ((mString = readMsg()) != null) {
				for (Socket socket : ServerMainThread.clientList) {
					Thread.sleep(2000);
					PrintStream writer;
					writer = new PrintStream(socket.getOutputStream());
					writer.println(mString);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String readMsg() {
		String msString = null;
		try {
			msString = reader.readLine();
			System.out.println(Thread.currentThread().getName() + " " + msString);

		} catch (IOException e) {
			e.printStackTrace();
			ServerMainThread.clientList.remove(socket);
		}
		return msString;
	}

}
