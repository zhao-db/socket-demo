package com.example.socketdemo.old.netty;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/2/25
 * @since 3.0.1
 */
@Slf4j
public class TestByteBuffer {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File("data.txt"));
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(10);
        while (true) {
            int read = channel.read(buffer);
            if (read == -1) {
                break;
            }
            buffer.flip(); //切换读模式
            while (buffer.hasRemaining()) {
                byte b = buffer.get();
                log.info("{}", (char) b);
            }
            buffer.compact();
            buffer.clear(); //切换写模式
        }
    }
}
