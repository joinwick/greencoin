package com.gc.network;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author join wick
 * @version 1.0.0
 * @description we chat client initializer
 * @createDate 2020/12/24 21:47
 * @since 1.0.0
 */
public class WeChatClientInitializer extends ChannelInitializer<SocketChannel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatClientInitializer.class);

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("weChatClientDecoder", new StringDecoder());
        pipeline.addLast("weChatClientEncoder", new StringEncoder());

        pipeline.addLast("weChatClientHandler", new WeChatClientHandler());

        LOGGER.debug("initial handler success");
    }
}
