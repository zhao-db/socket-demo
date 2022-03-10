package com.example.socketdemo.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/3/10
 * @since 3.0.1
 */
@Slf4j
public class TestHttpRequestHandler extends SimpleChannelInboundHandler<HttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
        log.info("channel read start msg :{}", msg.getClass());

    }
}
