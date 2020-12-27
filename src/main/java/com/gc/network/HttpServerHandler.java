package com.gc.network;

import com.gc.utils.ConstantUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;
import java.net.URI;

/**
 * @author join wick
 * @version 1.0.0
 * @description http server handler
 * @createDate 2020/12/23 21:19
 * @since 1.0.0
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServerHandler.class);

    /**
     * 读取客户端数据
     *
     * @param ctx ChannelHandlerContext
     * @param msg HttpObject 客户端和服务器相互通信的数据被封装成 HttpObject
     * @throws Exception Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 判断msg 是否为请求
        if (msg instanceof HttpRequest){
            SocketAddress socketAddress = ctx.channel().remoteAddress();
            LOGGER.debug("msg type = <{}>, client info = <{}>, pipeline hashcode = <{}>, HttpServerHandler hash = <{}>",
                    msg.getClass(), socketAddress, ctx.pipeline().hashCode(), this.hashCode());

            LOGGER.debug("channel = <{}>, pipeline = <{}>, get channel by pipeline = <{}>, get pipeline by channel = <{}>",
                    ctx.channel(), ctx.pipeline(), ctx.pipeline().channel(), ctx.channel().pipeline());

            LOGGER.debug("current handler = <{}>", ctx.handler());


            // 获取到请求资源的URI
            HttpRequest httpRequest = (HttpRequest) msg;
            // 获取URI,过滤特定的请求资源请求
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())){
                LOGGER.debug("request favicon.ico, no response from server");
                return;
            }

            // 回复消息给浏览器
            ByteBuf repliedMsg = Unpooled.copiedBuffer("hello, this is server", ConstantUtils.DEFAULT_CHARSET);
            // 构造一个http响应
            DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.OK, repliedMsg);
            defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, repliedMsg.readableBytes());

            // 将服务器响应返回
            ctx.writeAndFlush(defaultFullHttpResponse);
        }
        else {
            LOGGER.error("non http request");
        }
    }
}
