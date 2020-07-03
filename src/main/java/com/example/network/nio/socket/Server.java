package com.example.network.nio.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static int port = 2000;


    static ServerSocket server = null;

    public static void start(int port) {
        if (server != null) {
            return;
        }

        try {
            server = new ServerSocket(port);
            while (true) {
                // 当有新的客户端接入时，会执行下面的代码
                long start = System.currentTimeMillis();

                Socket socket = server.accept();
                new Thread(() -> {
                    new ServerHandler(socket);
                }).start();

                long end = System.currentTimeMillis();
                System.out.println("Spend time is " + (end - start));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                System.out.println("server is closed");
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void main(String[] args) {
        new Thread(() -> {
            Server.start(2000);
        }).start();

    }

}
