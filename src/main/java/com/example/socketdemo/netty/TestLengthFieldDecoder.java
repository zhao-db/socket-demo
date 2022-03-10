package com.example.socketdemo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * <p>
 *  TCL解码器  长度、报头、内容 解码
 * </p>
 *
 * @author zhaodb 2022/3/10
 * @since 3.0.1
 */
public class TestLengthFieldDecoder {

    public static void main(String[] args) {

        EmbeddedChannel embeddedChannel = new EmbeddedChannel(
                //最大长度，长度字段偏移量，长度字段长度，调整字节长度，从头开始剥离字节数
                new LengthFieldBasedFrameDecoder(1024, 0, 4, 1, 5)
                , new LoggingHandler(LogLevel.DEBUG));
        //4个字节长度  内容
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        send(byteBuf, "Hello, world");
        send(byteBuf, "Hi!");
        send(byteBuf, "ni hao zdb!");
        embeddedChannel.writeInbound(byteBuf);

    }

    private static void send(ByteBuf byteBuf, String msg) {
        //实际内容
        byte[] bytes = msg.getBytes();
        //实际内容长度
        int length = bytes.length;
        byteBuf.writeInt(length);
        byteBuf.writeByte(1);
        byteBuf.writeBytes(bytes);
    }

}
