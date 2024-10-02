package cn.bugstack.gateway.core.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Author: chs
 * Description: 数据处理基类
 * CreateTime: 2024-08-19
 */
public abstract class BaseHandler<T> extends SimpleChannelInboundHandler<T> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {
        session(ctx, ctx.channel(), msg);
    }

    protected abstract void session(ChannelHandlerContext ctx, final Channel channel, T request);
}
