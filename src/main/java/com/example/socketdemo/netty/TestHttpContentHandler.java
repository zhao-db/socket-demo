package com.example.socketdemo.netty;

import java.nio.charset.StandardCharsets;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpHeaders.CONTENT_LENGTH;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/3/10
 * @since 3.0.1
 */
@Slf4j
public class TestHttpContentHandler extends SimpleChannelInboundHandler<HttpContent> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpContent msg) throws Exception {
        log.info("channel read start msg :{}", msg.getClass());
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        byte[] respMsg = "<h1> Hell world! </h1>".getBytes(StandardCharsets.UTF_8);
        response.headers().setInt(CONTENT_LENGTH, respMsg.length);
        response.content().writeBytes(respMsg);
        ctx.writeAndFlush(response);
    }
}
