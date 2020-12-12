package com.gc.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author join wick
 * @version 1.0.0
 * @className SimpleNettyDiscardServer.java
 * @description simple netty demo
 * @createDate 2020/12/12 14:37
 * @since 1.0.0
 */
public class SimpleNettyDiscardServer {
    private final int serverPort;
    ServerBootstrap serverBootstrap = new ServerBootstrap();

    public SimpleNettyDiscardServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public void startServer(){
        EventLoopGroup serverLoopGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            serverBootstrap.group(serverLoopGroup, workerLoopGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.localAddress(serverPort);
            serverBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            serverBootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new NettyDiscardHandler());
                }
            });
        }

    }
}
