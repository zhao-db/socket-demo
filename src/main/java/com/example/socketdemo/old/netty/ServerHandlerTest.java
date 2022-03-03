package com.example.socketdemo.old.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/2/17
 * @since 3.0.1
 */
public class ServerHandlerTest implements ChannelHandler {

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("start handlerAdded");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("start handlerRemoved");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
        System.out.println("start exceptionCaught");
    }
}
