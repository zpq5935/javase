package book.fengkuang.unit17_netCode.tcp.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;

public class NioSocketClient {
    private Selector selector;
    private Charset charset = Charset.forName("utf-8");
    private SocketChannel socketChannel;

    public void init() throws Exception {
        selector = Selector.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(NioSocketServer.PORT);
        socketChannel = SocketChannel.open(inetSocketAddress);
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new ListenMsgThread().start();
        //
        socketChannel.write(charset.encode("this is " + Thread.currentThread().getName()));

//		Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextLine()) {
//            socketChannel.write(charset.encode(scanner.nextLine()));
//        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++)
            new NioSocketClient().init();
    }

    class ListenMsgThread extends Thread {
        @Override
        public void run() {
            try {
                while (selector.select() > 0) {
                    Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                    for (SelectionKey selectionKey : selectionKeySet) {
                        selectionKeySet.remove(selectionKey);

                        if (selectionKey.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            String content = "";
                            try {
                                while ((socketChannel.read(buffer)) > 0) {
                                    buffer.flip();
                                    content += charset.decode(buffer);
                                }
                                System.out.println("读取到数据:\n" + content);
                                selectionKey.interestOps(SelectionKey.OP_READ);
                            } catch (Exception e) {
                                selectionKey.cancel();
                                if (selectionKey.channel() != null) {
                                    selectionKey.channel().close();
                                }
                            }

                        }
                    }
                }
            } catch (Exception e) {
            }

        }
    }
}
