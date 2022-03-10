package com.example.socketdemo.netty;

import java.nio.charset.Charset;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/3/10
 * @since 3.0.1
 */
public class TestRedis {

    public static void main(String[] args) throws InterruptedException {
        byte[] bytes = {13, 10};
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class)
                .group(workerGroup)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                ByteBuf buffer = ctx.alloc().buffer();
                                buffer.writeBytes("*3".getBytes());
                                buffer.writeBytes(bytes);
                                buffer.writeBytes("$3".getBytes());
                                buffer.writeBytes(bytes);
                                buffer.writeBytes("set".getBytes());
                                buffer.writeBytes(bytes);
                                buffer.writeBytes("$4".getBytes());
                                buffer.writeBytes(bytes);
                                buffer.writeBytes("name".getBytes());
                                buffer.writeBytes(bytes);
                                buffer.writeBytes("$11".getBytes());
                                buffer.writeBytes(bytes);
                                buffer.writeBytes("zhaodbnihao".getBytes());
                                buffer.writeBytes(bytes);
                                ctx.writeAndFlush(buffer);
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf buf = (ByteBuf) msg;
                                System.out.println(buf.toString(Charset.defaultCharset()));
                            }
                        });
                    }
                });
        ChannelFuture channelFuture = bootstrap.connect("localhost", 6379).sync();
        channelFuture.channel().closeFuture().sync();


    }

}
