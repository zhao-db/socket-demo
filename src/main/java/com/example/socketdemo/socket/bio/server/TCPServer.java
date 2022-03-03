package com.example.socketdemo.socket.bio.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/2/8
 * @since 3.0.1
 */
public class TCPServer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputStream inputStream = null;
        ServerSocket socket = null;
        Socket accept = null;
        try {
            socket = new ServerSocket(8999);
            while (true) {
                accept = socket.accept();
                SocketChannel channel = accept.getChannel();
                InetAddress inetAddress = accept.getInetAddress();
                int port = accept.getPort();
                inputStream = accept.getInputStream();
                OutputStream outputStream = accept.getOutputStream();
                byte[] bytes = new byte[1024];
                int read = inputStream.read(bytes);
                for (int i = 0; i < read; i++) {
                    System.out.println(i);
                    System.out.println(bytes[i]);
                }
                outputStream.write(scanner.nextLine().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (accept != null) {
                    accept.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
