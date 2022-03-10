/*
package com.example.socketdemo.old.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;

*/
/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/2/17
 * @since 3.0.1
 *//*

public class ChannelHandlerDemo implements ChannelInboundHandler {
    @Override
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("channelRegistered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("channelUnregistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("channelUnregistered");
    }

    @Override
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("channelInactive");
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("channelRead");
        Channel channel = channelHandlerContext.channel();
        ByteBuf byteBuf = (ByteBuf) o;
        int i = byteBuf.readableBytes();
        byte[] bytes = new byte[2];
        byteBuf.readBytes(bytes, 0, 2);
        String msg = new String(bytes, "UTF-8");
        System.out.println("msg : 【" + msg + "】");
        byte[] bytes1 = new byte[2];
        byteBuf.readBytes(bytes1, 0, 2);
        String msg1 = new String(bytes1, "UTF-8");
        System.out.println("msg1 : 【" + msg1 + "】");
        byte[] bytes2 = new byte[4];
        byteBuf.readBytes(bytes2, 0, 4);
        String msg2 = new String(bytes2, "UTF-8");
        System.out.println("msg2 : 【" + msg2 + "】");
        System.out.println("channelRead end");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("channelReadComplete");
        Channel channel = channelHandlerContext.channel();
        Channel read = channel.read();
        ByteBufAllocator alloc = read.alloc();
        System.out.println(JSONUtil.toJSONString(alloc));
        System.out.println("channelReadComplete end");

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("userEventTriggered");
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("channelWritabilityChanged");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("handlerAdded");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("handlerRemoved");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
        System.out.println("exceptionCaught");
    }
}
*/
