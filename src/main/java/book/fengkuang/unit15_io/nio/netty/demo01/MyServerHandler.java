package book.fengkuang.unit15_io.nio.netty.demo01;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //接收msg消息
        ByteBuf buf = (ByteBuf) msg;
        byte[] msgByte = new byte[buf.readableBytes()];
        buf.readBytes(msgByte);

        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        System.out.printf("[%s][%s]\n\t<<\n\t%s\n",
                LocalDateTime.now(),
                socketAddress.getHostString() + " " + socketAddress.getPort(),
                new String(msgByte, StandardCharsets.UTF_8)
        );
    }

}