package com.gc.network;

import com.gc.utils.ConstantUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author join wick
 * @version 1.0.0
 * @description simple netty server
 * @createDate 2020/12/21 21:59
 * @since 1.0.0
 */
public class SimpleNettyServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleNettyServer.class);

    public static void main(String[] args) {
        // init server
        SimpleNettyServer.startServer();
    }

    public static void startServer(){
        // create loop group, boss: process accept, worker: process i/o
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        // create ServerBootstrap, NioServerSocketChannel for boss
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
            // setting pipeline, get pipeline from socket channel, SocketChannel for worker
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                // add handler to process io
                ch.pipeline().addLast(new SimpleNettyServerHandler());
            }
        });
        LOGGER.debug("server is ready, waiting for client...");
        try {
            // bind port to sync, create ChannelFuture
            ChannelFuture channelFuture = serverBootstrap.bind(ConstantUtils.DEFAULT_SERVER_PORT).sync();
            // watch close channel event
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOGGER.error("server start error with <{}>", e.getMessage());
            Thread.currentThread().interrupt();
        }
        // release resource
        finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


}
