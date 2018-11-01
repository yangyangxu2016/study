package com.example.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
            System.out.println("the server is start in port: "+ port);
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
