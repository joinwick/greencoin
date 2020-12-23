package com.gc.network;

import com.gc.utils.ConstantUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author join wick
 * @version 1.0.0
 * @description http server
 * @createDate 2020/12/23 21:04
 * @since 1.0.0
 */
public class HttpServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) {
        startServer();
    }

    public static void startServer(){
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_REUSEADDR, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new HttpServerInitializer());
        LOGGER.debug("server start...");
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(ConstantUtils.DEFAULT_SERVER_PORT).sync();
            // 注册监听事件,监听关心的事件
            channelFuture.addListener(future -> {
                if (channelFuture.isSuccess()){
                    LOGGER.debug("watch port <{}> success", ConstantUtils.DEFAULT_SERVER_PORT);
                }
                else {
                    LOGGER.error("watch port <{}> failed", ConstantUtils.DEFAULT_SERVER_PORT);
                }
            });
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOGGER.debug("server start failed with <{}>", e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
