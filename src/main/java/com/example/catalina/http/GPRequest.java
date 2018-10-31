package com.example.catalina.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

public class GPRequest {

    private ChannelHandlerContext ctx;
    private HttpRequest r;

    public GPRequest(ChannelHandlerContext ctx, HttpRequest r) {
        this.ctx = ctx;
        this.r = r;
    }


    public String getUri() {
        return r.getUri();
    }

    public String getMethod() {
        return r.getMethod().name();
    }

    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(getUri());
        return decoder.parameters();
    }

    public String getParameter(String name) {
        Map<String, List<String>> parameters = getParameters();
        List<String> param = parameters.get(name);
        if (null == param) {
            return null;
        } else {
            return param.get(0);
        }
    }


}
