package com.gc.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author join wick
 * @version 1.0.0
 * @description
 * @createDate 2020/12/24 21:51
 * @since 1.0.0
 */
public class WeChatClientHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatClientHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        msg = msg.trim();
        LOGGER.debug("msg = <{}>", msg);

    }
}
