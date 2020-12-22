package com.gc.network;

import com.gc.utils.ConstantUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;

/**
 * @author join wick
 * @version 1.0.0
 * @description simple netty client handler
 * @createDate 2020/12/22 10:01
 * @since 1.0.0
 */
public class SimpleNettyClientHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleNettyClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String msg = "hello 服务端";
        ctx.writeAndFlush(Unpooled.copiedBuffer(msg, ConstantUtils.DEFAULT_CHARSET));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        SocketAddress socketAddress = ctx.channel().remoteAddress();
        ByteBuf byteBuf = (ByteBuf) msg;
        String data = byteBuf.toString(ConstantUtils.DEFAULT_CHARSET);
        LOGGER.debug("receive data <{}> from server <{}>", data, socketAddress);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.channel().close();
    }
}
