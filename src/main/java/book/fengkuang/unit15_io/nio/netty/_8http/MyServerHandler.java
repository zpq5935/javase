package book.fengkuang.unit15_io.nio.netty._8http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;

public class MyServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//
//        if (msg instanceof HttpRequest) {
//            DefaultHttpRequest request = (DefaultHttpRequest) msg;
//            System.out.println("URI:" + request.getUri());
//            System.err.println(msg);
//        }
//
//        if (msg instanceof HttpContent) {
//            LastHttpContent httpContent = (LastHttpContent) msg;
//            ByteBuf byteData = httpContent.content();
//            if (!(byteData instanceof EmptyByteBuf)) {
//                //接收msg消息
//                byte[] msgByte = new byte[byteData.readableBytes()];
//                byteData.readBytes(msgByte);
//                System.out.println(new String(msgByte, StandardCharsets.UTF_8));
//            }
//        }
//
//        String sendMsg = "微信公众号：bugstack虫洞栈，欢迎您的关注&获取源码！不平凡的岁月终究来自你每日不停歇的刻苦拼搏，每一次真正成长都因看清脚下路而抉择出的生活。愿你我；承遇朝霞，年少正恰，整装戎马，刻印风华。\n" +
//                "博客栈：https://bugstack.cn\n" +
//                "内容介绍：本公众号会每天定时推送科技资料；专题、源码、书籍、视频、咨询、面试、环境等方面内容。尤其在技术专题方面会提供更多的原创内容，让更多的程序员可以从最基础开始了解到技术全貌，目前已经对外提供的有；《手写RPC框架》、《用Java实现JVM》、《基于JavaAgent的全链路监控》、《Netty案例》等专题。\n" +
//                "获取源码：\n" +
//                "关注｛bugstack虫洞栈｝公众号获取源码，回复<用Java实现jvm源码>\n" +
//                "关注｛bugstack虫洞栈｝公众号获取源码，回复<netty源码>\n" +
//                "关注｛bugstack虫洞栈｝公众号获取源码，回复<rpc源码>\n" +
//                "关注｛bugstack虫洞栈｝公众号获取源码，回复<基于JavaAgent的全链路监控>";
//
//        FullHttpResponse response = new DefaultFullHttpResponse(
//                HttpVersion.HTTP_1_1,
//                HttpResponseStatus.OK,
//                Unpooled.wrappedBuffer(sendMsg.getBytes(StandardCharsets.UTF_8)));
//        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
//        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
//        response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
//        ctx.write(response);
//        ctx.flush();
//    }

    private void record(HttpRequest httpRequest, ByteBuf bodyByteBuffer) {
        System.out.println("URI:" + httpRequest.getUri());
        System.err.println(httpRequest);

        // 接收msg消息
        byte[] msgByte = new byte[bodyByteBuffer.readableBytes()];
        bodyByteBuffer.readBytes(msgByte);
        System.out.println(new String(msgByte, StandardCharsets.UTF_8));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) throws Exception {
        record(fullHttpRequest, fullHttpRequest.content());

        String sendMsg = "微信公众号：bugstack虫洞栈，欢迎您的关注&获取源码！不平凡的岁月终究来自你每日不停歇的刻苦拼搏，每一次真正成长都因看清脚下路而抉择出的生活。愿你我；承遇朝霞，年少正恰，整装戎马，刻印风华。\n" +
                "博客栈：https://bugstack.cn\n" +
                "内容介绍：本公众号会每天定时推送科技资料；专题、源码、书籍、视频、咨询、面试、环境等方面内容。尤其在技术专题方面会提供更多的原创内容，让更多的程序员可以从最基础开始了解到技术全貌，目前已经对外提供的有；《手写RPC框架》、《用Java实现JVM》、《基于JavaAgent的全链路监控》、《Netty案例》等专题。\n" +
                "获取源码：\n" +
                "关注｛bugstack虫洞栈｝公众号获取源码，回复<用Java实现jvm源码>\n" +
                "关注｛bugstack虫洞栈｝公众号获取源码，回复<netty源码>\n" +
                "关注｛bugstack虫洞栈｝公众号获取源码，回复<rpc源码>\n" +
                "关注｛bugstack虫洞栈｝公众号获取源码，回复<基于JavaAgent的全链路监控>";

        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(sendMsg.getBytes(StandardCharsets.UTF_8)));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        ctx.write(response);
        ctx.flush();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }

}