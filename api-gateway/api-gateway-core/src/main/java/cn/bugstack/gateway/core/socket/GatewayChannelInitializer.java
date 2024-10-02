package cn.bugstack.gateway.core.socket;

import cn.bugstack.gateway.core.session.Configuration;
import cn.bugstack.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import cn.bugstack.gateway.core.socket.handlers.AuthorizationHandler;
import cn.bugstack.gateway.core.socket.handlers.GatewayServerHandler;
import cn.bugstack.gateway.core.socket.handlers.ProtocolDataHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
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

    private final Configuration configuration;
    private final DefaultGatewaySessionFactory gatewaySessionFactory;

    public GatewayChannelInitializer(Configuration configuration, DefaultGatewaySessionFactory gatewaySessionFactory){
        this.configuration = configuration;
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpRequestDecoder());
        pipeline.addLast(new HttpResponseEncoder());
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        pipeline.addLast(new GatewayServerHandler(configuration));
        pipeline.addLast(new AuthorizationHandler(configuration));
        pipeline.addLast(new ProtocolDataHandler(gatewaySessionFactory));
    }
}
