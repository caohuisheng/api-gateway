package cn.bugstack.gateway.core.socket;

import cn.bugstack.gateway.core.session.Configuration;
import cn.bugstack.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Callable;

/**
 * Author: chs
 * Description: 网关会话服务
 * CreateTime: 2024-09-01
 */
public class GatewaySocketServer implements Callable<Channel> {

    private Logger log = LoggerFactory.getLogger(GatewaySocketServer.class);

    private Configuration configuration;
    private DefaultGatewaySessionFactory gatewaySessionFactory;

    private NioEventLoopGroup boss;
    private NioEventLoopGroup work;
    private Channel channel;

    public GatewaySocketServer(Configuration configuration,DefaultGatewaySessionFactory gatewaySessionFactory){
        this.configuration = configuration;
        this.gatewaySessionFactory = gatewaySessionFactory;
        this.boss = new NioEventLoopGroup(configuration.getBossNThreads());
        this.work = new NioEventLoopGroup(configuration.getWorkNThreads());
    }

    @Override
    public Channel call() throws Exception {
        ChannelFuture channelFuture = null;
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new GatewayChannelInitializer(configuration, gatewaySessionFactory));
            channelFuture = b.bind(new InetSocketAddress(configuration.getHostName(), configuration.getPort())).syncUninterruptibly();
            this.channel = channelFuture.channel();
        } catch (Exception e) {
            log.error("socket server start error.", e);
            e.printStackTrace();
        }finally{
            if(channelFuture != null && channelFuture.isSuccess()){
                log.info("socket server start done.");
            }else{
                log.error("socket server start failed.");
            }
        }
        return channel;
    }
}
