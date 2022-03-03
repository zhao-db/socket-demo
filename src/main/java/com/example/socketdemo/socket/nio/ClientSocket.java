package com.example.socketdemo.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/2/28
 * @since 3.0.1
 */
public class ClientSocket {

    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress(9991));
        channel.write(Charset.defaultCharset().encode("123456789abcdefghijklmnopq\n"));
        System.in.read();
    }

}
