package book.fengkuang.unit17_netCode.tcp.baseaddthread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.time.LocalTime;

public class ClientMainThread {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    startOne();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }).start();
            ;
        }

        //
        startOne();
    }

    public static void startOne() throws Exception {
        System.out.println("starting client...");
//		Socket socket = new Socket("localhost", 30000);
        Socket socket = new Socket("localhost", 3004);
        new Thread(new CLientPrintThread(socket)).start();
        PrintStream printStream = new PrintStream(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            printStream.println(line);
//        }
        while (true) {
            Thread.sleep(1000);
            printStream.println(Thread.currentThread().getName() + LocalTime.now().toString());
        }
    }
}
