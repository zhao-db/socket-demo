package com.example.socketdemo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * <p>
 * 行解码器 可以自动解析 \r\n  或 \n
 * </p>
 *
 * @author zhaodb 2022/3/10
 * @since 3.0.1
 */
public class TestLineBaseFramDecoder {

    public static void main(String[] args) {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(
                new LineBasedFrameDecoder(1024),
                new LoggingHandler(LogLevel.DEBUG)
        );

        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byteBuf.writeBytes("Hello, world! \r\n".getBytes());
        byteBuf.writeBytes("ni hao zbd ! \n".getBytes());
        byteBuf.writeBytes("Hi! \r\n".getBytes());
        embeddedChannel.writeInbound(byteBuf);

    }

}
