package com.gc.network;

import com.gc.utils.ConstantUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author join wick
 * @version 1.0.0
 * @description simple netty server
 * @createDate 2020/12/22 9:20
 * @since 1.0.0
 */
public class SimpleNettyServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleNettyServer.class);

    public static void main(String[] args) {
        SimpleNettyServer.startServer();
    }

    public static void startServer(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                // 设置SO_REUSEADDR为true,意味着地址可以复用,比如如下场景
                // 某个进程占用了80端口,然后重启进程,原来的socket1处于TIME-WAIT状态,进程启动后,使用一个新的socket2,要占用80端口,
                // 如果这个时候不设置SO_REUSEADDR=true,那么启动的过程中会报端口已被占用的异常
                .option(ChannelOption.SO_REUSEADDR, true)
                // SO_KEEPALIVE=true,是利用TCP的SO_KEEPALIVE属性,当SO_KEEPALIVE=true的时候,服务端可以探测客户端的连接是否还存活着,
                // 如果客户端因为断电或者网络问题或者客户端挂掉了等,那么服务端的连接可以关闭掉,释放资源
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // 如果TCP_NODELAY没有设置为true,那么底层的TCP为了能减少交互次数,会将网络数据积累到一定的数量后,服务器端才发送出去,
                // 会造成一定的延迟。在互联网应用中,通常希望服务是低延迟的,建议将TCP_NODELAY设置为true
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                ch.pipeline().addLast(new SimpleNettyServerHandler());
            }
        });
        LOGGER.debug("server is ready, waiting for client ...");
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(ConstantUtils.DEFAULT_SERVER_PORT).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOGGER.debug("server start failed with <{}>", e.getMessage());
            Thread.currentThread().interrupt();
        }
        finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
