package com.gc.network;

import com.gc.common.entity.EnumEntity;
import com.gc.common.entity.NodeRecord;
import com.gc.utils.ConstantUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author join wick
 * @version 1.0.0
 * @description
 * @createDate 2020/12/24 21:02
 * @since 1.0.0
 */
public class WeChatServerHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatServerHandler.class);

    // 定义一个channel组，管理所有的channel, GlobalEventExecutor是全局的事件执行器,是一个单例
    private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    // 定义一个hashMap用于管理用户
    private static Map<NodeRecord, Channel> userMap = new HashMap<>();

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ConstantUtils.DEFAULT_DATE_TIME_FORMAT);

    /**
     * 一旦连接建立，第一个被执行，将当前的 channel 加入 channelGroup
     *
     * @param ctx ChannelHandlerContext
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        // 将该客户加入聊天的信息推送给其它在线的客户端
        String signUpMsg = simpleDateFormat.format(new Date()) + ": <客户端>" + ctx.channel().remoteAddress() + "加入聊天\n";
        channelGroup.writeAndFlush(signUpMsg);
        channelGroup.add(channel);

        NodeRecord nodeRecord = new NodeRecord();
        nodeRecord.setNodeIP("");
        nodeRecord.setNodeType(EnumEntity.NodeType.LIGHT);
        userMap.put(nodeRecord, channel);
    }

    /**
     * 表示channel处于有效状态
     *
     * @param ctx ChannelHandlerContext
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        SocketAddress socketAddress = ctx.channel().remoteAddress();
        LOGGER.debug("<{}> 上线了", socketAddress);
    }

    /**
     * 表示channel处于失活状态
     *
     * @param ctx ChannelHandlerContext
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SocketAddress socketAddress = ctx.channel().remoteAddress();
        LOGGER.debug("<{}> 下线了", socketAddress);
    }

    /**
     * 连接断开
     *
     * @param ctx ChannelHandlerContext
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        // 将该客户加入聊天的信息推送给其它在线的客户端
        String signOffMsg = simpleDateFormat.format(new Date()) + ": <客户端>" + ctx.channel().remoteAddress() + "离开聊天\n";
        channelGroup.writeAndFlush(signOffMsg);
        // 无需执行channelGroup的remove操作
        LOGGER.debug("当前channelGroup size = <{}>", channelGroup.size());
    }

    /**
     * 服务器装法消息
     *
     * @param ctx ChannelHandlerContext
     * @param msg String
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        // 获取channel
        Channel channel = ctx.channel();
        // 遍历channelGroup，排除自身
        channelGroup.forEach(ch -> {
            // 非当前channel
            String channelMsg;
            if (channel != ch) {
                channelMsg = simpleDateFormat.format(new Date()) + ": <用户>" + channel.remoteAddress() + "发送了消息" + msg + "\n";
            } else {
                channelMsg = simpleDateFormat.format(new Date()) + ": <自己> 发送了消息" + msg + "\n";
            }
            ch.writeAndFlush(channelMsg);
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 关闭channel
        ctx.close();
    }
}
