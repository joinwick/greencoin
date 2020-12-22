package com.gc.network;

import com.gc.utils.ConstantUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author join wick
 * @version 1.0.0
 * @description simple netty client
 * @createDate 2020/12/22 9:45
 * @since 1.0.0
 */
public class SimpleNettyClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleNettyClient.class);

    public static void main(String[] args) {
        SimpleNettyClient.startClient();
    }

    public static void startClient(){
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, ConstantUtils.DEFAULT_TIME_OUT)
                .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                ch.pipeline().addLast(new SimpleNettyClientHandler());
            }
        });
        LOGGER.debug("client start...");
        try {
            ChannelFuture channelFuture = bootstrap.connect(ConstantUtils.DEFAULT_SERVER_IP, ConstantUtils.DEFAULT_SERVER_PORT).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOGGER.debug("client start failed with <{}>", e.getMessage());
            Thread.currentThread().interrupt();
        }
        finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
