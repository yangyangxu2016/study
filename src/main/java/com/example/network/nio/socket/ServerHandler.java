package com.example.network.nio.socket;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler implements Runnable {

    public Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @SneakyThrows
    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        String msg = "";
        try {

            InputStream inputStream = socket.getInputStream();
            in = new BufferedReader(new InputStreamReader(inputStream));
            out = new PrintWriter(socket.getOutputStream(), true);

            while ((msg = in.readLine()) != null && msg.length() != 0) {
                System.out.println("service receiver : " + msg);
                out.println("received - \n");
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.close();
            socket.close();
        }

    }
}
