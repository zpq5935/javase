package book.fengkuang.unit15_io.nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class NettyTest {

    @NoArgsConstructor@Data@AllArgsConstructor
    public static class LoginForm {
        private String name;
        private String password;
    }

    @Test
    public void testEmbeddedChannel(){
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new StringPrinter());
        embeddedChannel.writeInbound(3);
        embeddedChannel.writeInbound("GET");
        String param = "name=zcp";
        embeddedChannel.writeInbound(param.length());
        embeddedChannel.writeInbound(param);
        embeddedChannel.writeInbound(new LoginForm("zhangchapp","asdasd"));
        embeddedChannel.flush();
        embeddedChannel.close();
    }

    @Test
    public void testLineBasedFrameDecoder(){
        String delimiter = "\r\n";
        byte[] delimiterBytes = delimiter.getBytes(StandardCharsets.UTF_8);

        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new StringPrinter());
            }
        });

        for (int i = 0; i < 10; i++) {
            ByteBuf buffer = Unpooled.buffer();
            buffer
                    .writeBytes(("第" + i).getBytes(StandardCharsets.UTF_8))
                    .writeBytes(delimiterBytes)
                    .writeBytes((  "次发送").getBytes(StandardCharsets.UTF_8))
                    .writeBytes(delimiterBytes)
            ;
            embeddedChannel.writeInbound(buffer);
        }

    }

    @Test
    public void testDelimiterBasedFrameDecoder(){
        String delimiter = "\t";
        byte[] delimiterBytes = delimiter.getBytes(StandardCharsets.UTF_8);

        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, true, Unpooled.copiedBuffer(delimiter.getBytes(StandardCharsets.UTF_8))));
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new StringPrinter());
            }
        });

        for (int i = 0; i < 10; i++) {
            ByteBuf buffer = Unpooled.buffer();
            buffer
                    .writeBytes(("第" + i).getBytes(StandardCharsets.UTF_8))
                    .writeBytes(delimiterBytes)
                    .writeBytes((  "次发送").getBytes(StandardCharsets.UTF_8))
                    .writeBytes(delimiterBytes)
            ;
            embeddedChannel.writeInbound(buffer);
        }

    }


    @Test
    public void testLengthFieldBasedFrameDecoder(){
        String method = "POST";
        String path = "/escort/user/page";
        String version = "HTTP/1.1";

        String content = "{\"t\":1688968615656,\"current\":1,\"size\":10,\"asc\":true,\"escortTitle\":\"name1\"}";

        LengthFieldBasedFrameDecoder decoder = new LengthFieldBasedFrameDecoder(
                1024,
                method.getBytes().length + path.getBytes().length + version.getBytes().length,
                4,
                0,
                content.getBytes().length
                );

        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(decoder);
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new StringPrinter());
            }
        });


        for (int i = 0; i < 10; i++) {
            ByteBuf buffer = Unpooled.buffer();
            buffer
                    .writeBytes(method.getBytes(StandardCharsets.UTF_8))
                    .writeBytes(path.getBytes(StandardCharsets.UTF_8))
                    .writeBytes(version.getBytes(StandardCharsets.UTF_8))

                    .writeInt(content.getBytes().length)

                    .writeBytes(content.getBytes(StandardCharsets.UTF_8))
            ;
            embeddedChannel.writeInbound(buffer);
        }

    }


    public static class StringPrinter extends ChannelInboundHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.err.println("read msg : " + msg);

            super.channelRead(ctx, msg);
        }
    }



}
