package cn.bugstack.gateway.socket;

import cn.bugstack.gateway.session.Configuration;
import cn.bugstack.gateway.session.defaults.DefaultGatewaySessionFactory;
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

    private NioEventLoopGroup boss = new NioEventLoopGroup();
    private NioEventLoopGroup work = new NioEventLoopGroup();
    private Channel channel;

    public GatewaySocketServer(Configuration configuration,DefaultGatewaySessionFactory gatewaySessionFactory){
        this.configuration = configuration;
        this.gatewaySessionFactory = gatewaySessionFactory;
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
            channelFuture = b.bind(new InetSocketAddress(7397)).syncUninterruptibly();
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
