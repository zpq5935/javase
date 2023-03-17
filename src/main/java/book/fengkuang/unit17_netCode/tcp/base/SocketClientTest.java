package book.fengkuang.unit17_netCode.tcp.base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.junit.Test;

public class SocketClientTest {

    @Test
    public void t1() throws Exception {
        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                try {
                    testSocket();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        while (Thread.activeCount() > 1) ;
    }

    public void testSocket() throws Exception {
        System.out.println("客户端 start");
        Socket socket = new Socket("localhost", 8080);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("GET /index.html HTTP/1.1\n" +
				"Host: localhost:8080\n" +
				"Connection: keep-alive\n" +
				"Pragma: no-cache\n" +
				"Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7,zh-TW;q=0.6\n");
        printWriter.flush();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
        socket.close();
        System.out.println("客户端 end");

    }
}
