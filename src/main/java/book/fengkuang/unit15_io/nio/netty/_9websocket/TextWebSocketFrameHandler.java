package book.fengkuang.unit15_io.nio.netty._9websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        if (msg instanceof TextWebSocketFrame) {
            TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame) msg;
            System.err.printf("服务端收到%s\n", textWebSocketFrame.text());
            scheduleSend(ctx, textWebSocketFrame.text());
        }
    }


    private void scheduleSend(ChannelHandlerContext ctx, String msg) {
        ctx.executor().scheduleAtFixedRate(() -> {
            ctx.writeAndFlush(new TextWebSocketFrame(LocalDateTime.now() + msg));
        }, 1, 2, TimeUnit.SECONDS);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            System.err.println("WebSocket握手成功");
        } else {

            super.userEventTriggered(ctx, evt);
        }
    }
}
