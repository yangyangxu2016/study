package com.example.netty.fnio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *伪异步通信IO采用了线程池实现，避免了为每一个请求都创建一个线程造成线程资源耗尽问题，但是由于底层依然采用阻塞IO，因此无法从根本上解决问题
 */
public class TimeServer {


    public static void main(String[] args) throws IOException {
        int port = 8082;
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
            TimeServerHandlerExecutePool singleExecute = new TimeServerHandlerExecutePool(50, 10000);//创建IO线程池
            while (true) {
                socket = server.accept();
                singleExecute.executor(new Thread(new TimeServerHandler(socket)));
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
