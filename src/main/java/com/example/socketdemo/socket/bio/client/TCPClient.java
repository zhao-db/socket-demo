package com.example.socketdemo.socket.bio.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/2/8
 * @since 3.0.1
 */
public class TCPClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputStream in = null;
        OutputStream outputStream = null;
        try {
            Socket socket = new Socket("127.0.0.1", 8999);
            in = socket.getInputStream();
            outputStream = socket.getOutputStream();
            String inputMsg = scanner.nextLine();
            outputStream.write(inputMsg.getBytes());
            byte[] bytes = new byte[1024];
            int read = in.read(bytes);
            String msg = new String(bytes, 0, read);
            System.out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
