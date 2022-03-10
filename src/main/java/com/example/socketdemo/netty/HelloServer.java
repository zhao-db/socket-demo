package com.example.socketdemo.netty;

import java.nio.charset.Charset;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/3/3
 * @since 3.0.1
 */
@Slf4j
public class HelloServer {

    public static void main(String[] args) {
        EventLoopGroup defaultGroup = new DefaultEventLoop();
        new ServerBootstrap()
                //boss  worker
                .group(new NioEventLoopGroup(), new NioEventLoopGroup(2))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast("handler1", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf byteBuf = ((ByteBuf) msg);
                                log.info(byteBuf.toString(Charset.defaultCharset()));
                                ctx.fireChannelRead(msg);
                            }
                        });
                        ch.pipeline().addLast(defaultGroup, "handler2", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf byteBuf = ((ByteBuf) msg);
                                log.info(byteBuf.toString(Charset.defaultCharset()));
                            }
                        });

                    }
                }).bind(8989);
    }

}
