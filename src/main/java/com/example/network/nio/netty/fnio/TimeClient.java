package com.example.network.nio.netty.fnio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {

    public static void main(String[] args) {
        int port = 8082;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                //采用默认值
            }
        }

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            socket = new Socket("127.0.0.1", port);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("QUERY CURRENT TIME");

            System.out.println("scend query to server");

            String resp = in.readLine();

            System.out.println("now is "+ resp);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (out != null) {
                out.close();
                out = null;
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;

            }

        }

    }
}
