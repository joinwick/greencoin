package com.gc.network;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author join wick
 * @version 1.0.0
 * @description we chat server initializer
 * @createDate 2020/12/24 20:59
 * @since 1.0.0
 */
public class WeChatServerInitializer extends ChannelInitializer<SocketChannel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatServerInitializer.class);

    /**
     *
     * @param ch SocketChannel
     */
    @Override
    protected void initChannel(SocketChannel ch) {
        // 得到管道
        ChannelPipeline channelPipeline = ch.pipeline();
        // 增加一个decoder
        channelPipeline.addLast("weChatServerDecoder", new StringDecoder());
        // 增加一个encoder
        channelPipeline.addLast("weChatServerEncoder", new StringEncoder());
        // 增加一个自定义的handler
        channelPipeline.addLast("weChatServerHandler", new WeChatServerHandler());
        // 增加一个空闲检测handler: readerIdleTime-no read, writerIdleTime-no write, allIdleTime-no read & write
        channelPipeline.addLast(new IdleStateHandler(3, 5, 7, TimeUnit.SECONDS));
        // 增加一个空闲处理的自定义handler,一旦触发会传递给下一个handler的userEventTriggered去处理
        channelPipeline.addLast("weChatServerIdleHandler", new WeChatServerIdleHandler());

        LOGGER.debug("initial handler success");
    }
}
