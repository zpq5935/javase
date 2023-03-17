package book.fengkuang.unit17_netCode.tcp.nio;
/**
 * JDK 1.4 对Socket提供NIO开发高性能的网络服务器
 * 去除1个劣势：大量请求时不需要开启大量线程
 * 因为原来的accept、readLine均属于阻塞API
 *
 * @author zpq5935
 */

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NioSocketServer {
    private Selector selector;
    public static final Integer PORT = 3004;
    private Charset charset = Charset.forName("utf-8");

    public void init() throws Exception {
        selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(PORT);
        serverSocketChannel.bind(inetSocketAddress);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (!selectionKey.isValid()) return;
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    selectionKey.interestOps(SelectionKey.OP_ACCEPT);
                    System.out.println(
                            "Accepted connection from " + socketChannel);
                }
                if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    String content = "";
                    try {
                        while ((socketChannel.read(buffer)) > 0) {
                            buffer.flip();
                            content += charset.decode(buffer);
                        }
                        if (null == content || content.length() <= 0) {
                            selectionKey.cancel();
                            socketChannel.close();
                        } else {
                            System.out.println("读取到数据:\n" + content);
                        }
                        selectionKey.interestOps(SelectionKey.OP_READ);
                    } catch (Exception e) {
                        selectionKey.cancel();
                        if (selectionKey.channel() != null) {
                            selectionKey.channel().close();
                        }
                    }
                    // 发给其他Channel
                    if (content.length() > 0) {
                        for (SelectionKey key : selector.keys()) {
                            SelectableChannel channel = key.channel();
                            if (channel instanceof SocketChannel) {
                                SocketChannel targetChannel = (SocketChannel) channel;
                                targetChannel.write(charset.encode(content));
                            }
                        }
                    }

                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new NioSocketServer().init();
    }
}
