package com.gc.network;

import com.gc.utils.ConstantUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @author join wick
 * @version 1.0.0
 * @description we chat client
 * @createDate 2020/12/24 21:37
 * @since 1.0.0
 */
public class WeChatClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatClient.class);

    private final String serverIP;
    private final int serverPort;

    public WeChatClient(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public static void main(String[] args) {
        new WeChatClient(ConstantUtils.DEFAULT_SERVER_IP, ConstantUtils.DEFAULT_SERVER_PORT).startClient();
    }

    /**
     * 启动客户端
     */
    public void startClient() {
        EventLoopGroup clientGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(clientGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new WeChatClientInitializer());
        try (Scanner scanner = new Scanner(System.in)) {
            ChannelFuture channelFuture = bootstrap.connect(serverIP, serverPort).sync();
            // 得到channel
            Channel channel = channelFuture.channel();
            LOGGER.debug("channel local address = <{}>", channel.localAddress());
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine() + "\r\n";
                // 通过 channel 发送至服务器
                channel.writeAndFlush(msg);
            }
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.debug("client start failed with <{}>", e.getMessage());
        } finally {
            clientGroup.shutdownGracefully();
        }
    }

}
