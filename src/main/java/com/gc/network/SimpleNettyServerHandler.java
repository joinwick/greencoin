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
 * @description simple netty server handler, self define
 * @createDate 2020/12/21 22:23
 * @since 1.0.0
 */
public class SimpleNettyServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleNettyServerHandler.class);

    /**
     * read data
     *
     * @param ctx ChannelHandlerContext[contains pipeline, channel, ip address...]
     * @param msg Object, client data
     * @throws Exception Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.debug("server ctx = <{}>", ctx);
        // convert msg to byteBuf(provided by netty)
        ByteBuf byteBuf = (ByteBuf) msg;
        String message = byteBuf.toString(ConstantUtils.DEFAULT_CHARSET);
        SocketAddress ipAddress = ctx.channel().remoteAddress();
        LOGGER.debug("receive msg <{}> from client <{}>", message, ipAddress);
    }

    /**
     * data read complete
     *
     * @param ctx ChannelHandlerContext
     * @throws Exception Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // write & flush, write data to cache and flush
        // encode data with charset
        String repliedMsg = "hello 客户端";
        ctx.writeAndFlush(Unpooled.copiedBuffer(repliedMsg, ConstantUtils.DEFAULT_CHARSET));
    }

    /**
     * process exception
     *
     * @param ctx   ChannelHandlerContext
     * @param cause Throwable
     * @throws Exception Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.channel().close();
    }
}
