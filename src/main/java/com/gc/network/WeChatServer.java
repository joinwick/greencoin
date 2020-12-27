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
 * @description we chat server
 * @createDate 2020/12/24 20:35
 * @since 1.0.0
 */
public class WeChatServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatServer.class);
    private final int port;

    public static void main(String[] args) {
        new WeChatServer(ConstantUtils.DEFAULT_SERVER_PORT).startServer();
    }

    public WeChatServer(int port) {
        this.port = port;
    }

    public void startServer(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_REUSEADDR, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new WeChatServerInitializer());
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            // 注册监听事件,监听关心的事件
            channelFuture.addListener(future ->{
               if (channelFuture.isSuccess()){
                   LOGGER.debug("server watch port <{}> success", port);
               }
               else {
                   LOGGER.debug("server watch port <{}> failed", port);
               }
            });
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.debug("server start failed with <{}>", e.getMessage());
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
