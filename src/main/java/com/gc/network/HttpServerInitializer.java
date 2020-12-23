package com.gc.network;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author join wick
 * @version 1.0.0
 * @description http server channel initializer
 * @createDate 2020/12/23 21:12
 * @since 1.0.0
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServerInitializer.class);
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 向管道加入处理器

        // 得到管道
        ChannelPipeline channelPipeline = ch.pipeline();

        // 加入一个netty提供的codec
        channelPipeline.addLast("myHttpServerCodec", new HttpServerCodec());
        // 增加一个自定义的handler
        channelPipeline.addLast("myHttpServerHandler", new HttpServerHandler());

        LOGGER.debug("initial handler ok");
    }
}
