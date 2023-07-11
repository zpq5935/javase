package book.fengkuang.unit15_io.nio.netty._5client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * netty 客户端而不是服务端
 */
public class NettyClient {

    public static void main(String[] args) {
        new NettyClient().connect("127.0.0.1", 7397);
    }

    private void connect(String inetHost, int inetPort) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.AUTO_READ, true)
                    .handler(new MyChannelInitializer());
            ChannelFuture channelFuture = bootstrap.connect(inetHost, inetPort).sync();
            channelFuture.sync();
            Channel channel = channelFuture.channel();

            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入发送内容：");
            while (scanner.hasNext()) {
                channel.writeAndFlush(Unpooled.wrappedBuffer(scanner.next().getBytes(StandardCharsets.UTF_8)));
                System.out.println("请输入发送内容：");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

}
