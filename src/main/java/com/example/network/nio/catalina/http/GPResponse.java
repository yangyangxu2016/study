package com.example.network.nio.catalina.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.UnsupportedEncodingException;

//import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
//import static io.netty.handler.codec.http.HttpHeaderNames.EXPIRES;
//import static io.netty.handler.codec.rtsp.RtspHeaderNames.CONNECTION;
import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;

public class GPResponse {

    private ChannelHandlerContext ctx;
    private HttpRequest r;

    public GPResponse(ChannelHandlerContext ctx, HttpRequest r) {
        this.ctx = ctx;
        this.r = r;

    }


    public void write(String out) throws UnsupportedEncodingException {

        try {
            if (out == null) {
                System.out.println(out);
                return;
            }

            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(out.getBytes("UTF-8")));


            response.headers().set(CONTENT_TYPE, "text/json");
//            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
//            response.headers().set(EXPIRES, 0);
            if (HttpHeaders.isKeepAlive(r)) {
//                response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            }
            ctx.write(response);
        } finally {
            ctx.flush();
        }
    }


}



