package com.gc.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author join wick
 * @version 1.0.0
 * @description
 * @createDate 2020/12/24 22:50
 * @since 1.0.0
 */
public class WeChatServerIdleHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatServerIdleHandler.class);

    /**
     * 超时事件处理
     *
     * @param ctx ChannelHandlerContext
     * @param evt Object
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if (evt instanceof IdleStateEvent) {
            // 获取 evt 的类型
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            String eventType = "";
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    eventType = "read idle";
                    break;
                case WRITER_IDLE:
                    eventType = "write idle";
                    break;
                case ALL_IDLE:
                    eventType = "all idle";
                    break;
                default:
                    break;
            }
            LOGGER.debug("<客户端>: <{}> 发生了超时事件: <{}>", ctx.channel().remoteAddress(), eventType);
            // 服务器做相应处理,此处检测到空闲则关闭通道
            ctx.channel().close();
        }
    }
}
