package com.example.socketdemo.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/2/28
 * @since 3.0.1
 */
@Slf4j
public class ServerSocket {

    public static void main(String[] args) throws IOException {
        //选择器
        Selector selector = Selector.open();
        //服务器channel
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //绑定端口
        ssc.bind(new InetSocketAddress(9999));
        //设置非阻塞
        ssc.configureBlocking(false);
        //注册serverchannel到选择器
        SelectionKey sscKey = ssc.register(selector, 0, null);
        log.info("开启服务端");
        //设置感兴趣时间 服务器设置OP_ACCEPT
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        while (true) {
            //选择器阻塞 有事件才会向下执行 (OP_ACCEPT, OP_READ, OP_WRITE)
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //取到后移除迭代器 否则会一直执行
                iterator.remove();
                //是Accpet事件
                if (key.isAcceptable()) {
                    //有客户端连接 获取到客户端连接channel
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    //连接客户端
                    SocketChannel sc = channel.accept();
                    log.info("有客户端连接：{}", channel);
                    //设置非阻塞
                    sc.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(10);
                    //将btyeBuffer作为附件关联到selectionKey
                    SelectionKey scKey = sc.register(selector, 0, buffer);
                    //设置感兴趣事件
                    scKey.interestOps(SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    //是可读事件
                    SocketChannel channel = (SocketChannel) key.channel();
                    //获取附件
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    log.info("收到客户端发送数据");
                    int read = channel.read(buffer);
                    if (read < 0) {
                        //强制关闭或客户端离线后 会读到-1  此时将选择器中的key取消 防止一直轮询事件
                        key.cancel();
                    }
                    //粘包半包处理
                    split(buffer);
                    //自动扩容Bytebuffer
                    if (buffer.position() == buffer.limit()) {
                        //需要扩容
                        ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                        buffer.flip();
                        newBuffer.put(buffer);
                        key.attach(newBuffer);
                    }
                    //log.info("收到客户端发送数据：{}", Charset.defaultCharset().decode(buffer));
                }
            }
        }
    }

    /**
     * 消息传输粘包半包处理 \n分割 每条消息
     *
     * @param buffer
     */
    private static void split(ByteBuffer buffer) {
        buffer.flip();
        for (int i = 0; i < buffer.limit(); i++) {
            if (buffer.get(i) == '\n') {
                int length = i + 1 - buffer.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                target.clear();
                for (int j = 0; j < target.limit(); j++) {
                    target.put(buffer.get());
                }
                target.flip();
                log.info("收到客户端发送数据：{}", Charset.defaultCharset().decode(target));
            }
        }
        buffer.compact();
    }


}
