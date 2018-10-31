package com.example.catalina.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.IOException;

public class GPTomcat {

    public void start(int port) throws IOException, InterruptedException {
//        NIO
//        ServerSocketChannel s = ServerSocketChannel.open();
//        s.bind();

//        BIO
//        ServerSocket s = new ServerSocket();

//        主从
        //Boss线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //Worker线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            //Netty服务
            ServerBootstrap server = new ServerBootstrap();

            server.group(bossGroup, workerGroup)
                    //主线程处理类
                    .channel(NioServerSocketChannel.class)
                    //子线程处理类
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel client) throws Exception {
                            //无锁话串行变成
                            // 编码器
                            client.pipeline().addLast(new HttpResponseEncoder());
                            //解码器
                            client.pipeline().addLast(new HttpRequestDecoder());
                            //业务处理逻辑
                            client.pipeline().addLast(new GPTomcatHandler());
                        }
                    })
                    //配置信息
                    .option(ChannelOption.SO_BACKLOG, 128)//主线程配置
                    .childOption(ChannelOption.SO_KEEPALIVE, true);//子线程配置


            ChannelFuture future = server.bind(port).sync();//主线程阻塞这里，防止挂掉
            System.out.println("HTTP服务tomcat已经启动，正在监听端口号：" + port);

            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            System.out.println("关闭服务");
        }


    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new GPTomcat().start(8081);

    }
}
