package com.gc.network;

import com.gc.utils.ConstantUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author join wick
 * @version 1.0.0
 * @description simple netty handler
 * @createDate 2020/12/22 9:29
 * @since 1.0.0
 */
public class SimpleNettyServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleNettyServerHandler.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(10 * 1000L);
                ctx.writeAndFlush(Unpooled.copiedBuffer("process client business-0", ConstantUtils.DEFAULT_CHARSET));
            } catch (InterruptedException e) {
                LOGGER.debug("exception with <{}>", e.getMessage());
                Thread.currentThread().interrupt();
            }
        });

        ctx.channel().eventLoop().schedule(() -> {
            try {
                Thread.sleep(10 * 1000L);
                ctx.writeAndFlush(Unpooled.copiedBuffer("process client business-1", ConstantUtils.DEFAULT_CHARSET));
            } catch (InterruptedException e) {
                LOGGER.debug("exception with <{}>", e.getMessage());
                Thread.currentThread().interrupt();
            }

        }, 5, TimeUnit.SECONDS);
//        SocketAddress socketAddress = ctx.channel().remoteAddress();
//        ByteBuf byteBuf = (ByteBuf) msg;
//        String data = byteBuf.toString(ConstantUtils.DEFAULT_CHARSET);
//        LOGGER.debug("receive data <{}> from client <{}>", data, socketAddress);
        LOGGER.debug("non-block");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        String repliedMsg = "hello,客户端...";
        ctx.writeAndFlush(Unpooled.copiedBuffer(repliedMsg, ConstantUtils.DEFAULT_CHARSET));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.channel().close();
    }
}
