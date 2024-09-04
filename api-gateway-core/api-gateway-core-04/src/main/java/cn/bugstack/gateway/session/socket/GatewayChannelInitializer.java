package cn.bugstack.gateway.session.socket;

import cn.bugstack.gateway.session.GatewaySessionFactory;
import cn.bugstack.gateway.session.socket.handlers.GatewayServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * Author: chs
 * Description: 会话管道初始化类
 * CreateTime: 2024-09-01
 */
public class GatewayChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final GatewaySessionFactory gatewaySessionFactory;

    public GatewayChannelInitializer(GatewaySessionFactory gatewaySessionFactory){
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpRequestDecoder());
        pipeline.addLast(new HttpResponseEncoder());
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        pipeline.addLast(new GatewayServerHandler(gatewaySessionFactory));
    }
}
