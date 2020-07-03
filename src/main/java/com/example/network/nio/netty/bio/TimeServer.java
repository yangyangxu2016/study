package com.example.network.nio.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *Bio的主要问题在于每当有一个新的客户端请求接入时，服务器必须创建一个新的线程，一个线程只能处理一个客户端连接，
 * 当成千上万个客户端并发是，往往造成线程资源耗尽，导致堆栈溢出
 */
public class TimeServer {


    public static void main(String[] args) throws IOException {
        int port = 8081;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                //采用默认的端口号
            }
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("the server is start in port: " + port);
            Socket socket = null;
            while (true) {
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } finally {
            if (server != null) {
                System.out.println("server already close ");
                server.close();
                server = null;
            }

        }

    }
}
