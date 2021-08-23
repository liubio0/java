package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.commons.codec.binary.StringUtils;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public boolean filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {

        fullRequest.headers().set("mao", "soul");
        String referer = fullRequest.headers().get("referer");
        String a = new String("http://liuzm.com");
        if (referer == null || referer == "" || !a.equals(referer)){
            System.out.println("Please do not hotlinking!");
            return false;
        }
        return true;
    }
}
