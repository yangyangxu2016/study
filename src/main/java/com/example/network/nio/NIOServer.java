package com.example.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {


    private int port = 8080;
    private InetSocketAddress address = null;

    private Selector selector;

    public NIOServer(int port) {
        this.port = port;
        this.address = new InetSocketAddress(this.port);
        try {

            //高速公路修起来
            ServerSocketChannel server = ServerSocketChannel.open();
            server.bind(address);
            //默认阻塞，手动设置为阻塞
            server.configureBlocking(false);

            //大管家开始工作
            this.selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("服务器准备就绪，监听端口是=" + this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        要想富，先修路
    }

    public static void main(String[] args) {
        new NIOServer(8080).listen();
    }

    private void listen() {
        try {
            //轮询
            while (true) {

                int wait = this.selector.select();
                if (wait == 0) continue;

                Set<SelectionKey> keys = this.selector.selectedKeys();
                Iterator<SelectionKey> i = keys.iterator();
                while (i.hasNext()) {
                    SelectionKey key = i.next();
                    process(key);
                    i.remove();
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void process(SelectionKey key) throws IOException {


        ByteBuffer buffer = ByteBuffer.allocate(1024);

        if (key.isConnectable()) {

            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();//拿到客户端
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);//注册事件，可以读取了，进入下一轮轮询

        } else if (key.isReadable()) {
            SocketChannel client = (SocketChannel) key.channel();
            int len = client.read(buffer);
            if (len > 0) {
                buffer.flip();
                String content = new String(buffer.array(), 0, len);
                System.out.println(content);
                client.register(selector, SelectionKey.OP_WRITE);
            }
            buffer.clear();

        } else if (key.isWritable()) {
            SocketChannel client = (SocketChannel) key.channel();
            client.write(ByteBuffer.wrap("Hello World".getBytes()));//删除了两次会报错
        }
    }


}
