package book.fengkuang.unit17_netCode.tcp.baseaddthread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 客户端打印消息线程
 * 
 * @author zpq5935
 *
 */
public class CLientPrintThread implements Runnable {

	private Socket socket;
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private BufferedReader reader;

	public CLientPrintThread(Socket socket) throws IOException {
		super();
		this.socket = socket;
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	@Override
	public void run() {
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(
						simpleDateFormat.format(new Date()) + " " + Thread.currentThread().getName() + " " + line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
