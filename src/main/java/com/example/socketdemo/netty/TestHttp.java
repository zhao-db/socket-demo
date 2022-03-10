package com.example.socketdemo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
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
public class TestHttp {

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup(2);
        ServerBootstrap serverBootstrap = new ServerBootstrap()
                .group(worker, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        ch.pipeline().addLast(new HttpServerCodec());
                        ch.pipeline().addLast(new TestHttpRequestHandler());
                        ch.pipeline().addLast(new TestHttpContentHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(9999).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
            group.shutdownGracefully();
        }


    }

}
