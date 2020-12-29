package com.gc.network;

import com.gc.utils.ConstantUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * @author join wick
 * @version 1.0.0
 * @description web socket server
 * @createDate 2020/12/29 21:57
 * @since 1.0.0
 */
public class WebSocketServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);

    private final int serverPort;

    public WebSocketServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public static void main(String[] args) {
        new WebSocketServer(ConstantUtils.DEFAULT_SERVER_PORT).startServer();
    }

    public void startServer() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .option(ChannelOption.SO_REUSEADDR, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new WebSocketServerInitialHandler());

        try {
            ChannelFuture channelFuture = serverBootstrap.bind(serverPort).sync();
            channelFuture.addListener(future -> {
                if (future.isSuccess()) {
                    LOGGER.debug("server watch port <{}> success", serverPort);
                } else {
                    LOGGER.debug("server watch port <{}> failed", serverPort);
                }
            });
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.debug("server start failed with <{}>", e.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

class WebSocketServerInitialHandler extends ChannelInitializer<SocketChannel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServerInitialHandler.class);

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline channelPipeline = ch.pipeline();
        LOGGER.debug("WebSocketServerInitialHandler is called");
        // 基于http协议,使用http编解码器
        channelPipeline.addLast(new HttpServerCodec());
        // 以块的方式写,添加ChunkedWriteHandler处理器
        channelPipeline.addLast(new ChunkedWriteHandler());
        // http数据在传输过程中是分段的(浏览器发送大量数据时，会发出多次http请求)，HttpObjectAggregator就是将多个分段的数据聚合起来
        channelPipeline.addLast(new HttpObjectAggregator(8192));
        // 对于webSocket，数据以帧的形式传递，浏览器请求时，ws://localhost:9081/hello 表示请求URL，
        // WebSocketServerProtocolHandler将http协议升级为ws协议，以保持长连接
        channelPipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
        // 自定义handler处理业务逻辑
        channelPipeline.addLast(new WebSocketServerHandler());
    }
}

class WebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        String clientMsg = msg.text();
        LOGGER.debug("receive msg<{}> from client", clientMsg);
        // 回复信息至客户端
        TextWebSocketFrame repliedMsg = new TextWebSocketFrame("服务器时间" + LocalDateTime.now() + ":" + msg.text());
        ctx.channel().writeAndFlush(repliedMsg);
    }

    /**
     * 感知客户端连接
     *
     * @param ctx ChannelHandlerContext
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        String longText = ctx.channel().id().asLongText();
        String shortText = ctx.channel().id().asShortText();
        LOGGER.debug("handlerAdded <{}> is called", longText);
        LOGGER.debug("handlerAdded <{}> is called", shortText);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        String longText = ctx.channel().id().asLongText();
        String shortText = ctx.channel().id().asShortText();
        LOGGER.debug("handlerRemoved <{}> is called", longText);
        LOGGER.debug("handlerRemoved <{}> is called", shortText);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.debug("exceptionCaught <{}> is called", cause.getMessage());
        ctx.channel().close();
    }
}
