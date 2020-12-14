package com.gc.network;

import com.gc.utils.ConstantUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author join wick
 * @version 1.0.0
 * @className SimpleNettyDiscardServer.java
 * @description simple netty demo
 * @createDate 2020/12/12 14:37
 * @since 1.0.0
 */
public class SimpleNettyDiscardServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleNettyDiscardServer.class);
    private final int serverPort;
    ServerBootstrap serverBootstrap = new ServerBootstrap();

    public SimpleNettyDiscardServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public static void main(String[] args) {
        new SimpleNettyDiscardServer(ConstantUtils.DEFAULT_SERVER_PORT).startServer();
    }

    public void startServer(){
        // 创建反应器线程组
        EventLoopGroup serverLoopGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            // 1.设置反应器线程组
            serverBootstrap.group(serverLoopGroup, workerLoopGroup);
            // 2.设置NIO类型的通道
            serverBootstrap.channel(NioServerSocketChannel.class);
            // 3.设置监听端口
            serverBootstrap.localAddress(serverPort);
            // 4.设置通道的参数
            serverBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            serverBootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            // 5.装配子通道流水线
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                // 有连接到达时会创建一个通道
                @Override
                protected void initChannel(SocketChannel ch) {
                    // 流水线管理子通道中的Handler处理器
                    // 向子通道流水线中添加一个handler处理器
                    ch.pipeline().addLast(new NettyDiscardHandler());
                }
            });
            // 6.开始绑定服务器
            // 通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            LOGGER.debug("服务器启动成功,监听端口: <{}>", channelFuture.channel().localAddress());
            // 7.等待通道关闭的异步任务结束
            // 服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            LOGGER.error("出现异常:<{}>", e.getMessage());
        }finally {
            // 8.关闭EventLoopGroup,释放所有资源(含创建的线程)
            workerLoopGroup.shutdownGracefully();
            serverLoopGroup.shutdownGracefully();
        }
    }
}

class NettyDiscardHandler extends ChannelInboundHandlerAdapter{
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyDiscardHandler.class);
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        try {
            LOGGER.debug("收到信息,丢弃如下...");
            while (byteBuf.isReadable()){
                LOGGER.debug("data = {}", byteBuf.readByte());
            }
        }
        finally {
            ReferenceCountUtil.release(msg);
        }
    }

}
