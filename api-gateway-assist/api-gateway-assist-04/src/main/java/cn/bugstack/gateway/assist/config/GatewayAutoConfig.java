package cn.bugstack.gateway.assist.config;

import cn.bugstack.gateway.assist.application.GatewayApplication;
import cn.bugstack.gateway.assist.domain.service.GatewayCenterService;
import cn.bugstack.gateway.core.session.Configuration;
import cn.bugstack.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import cn.bugstack.gateway.core.socket.GatewaySocketServer;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Author: chs
 * Description: 网关服务配置
 * CreateTime: 2024-09-13
 */
@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(GatewayServiceProperties.class)
public class GatewayAutoConfig {

    private Logger log = LoggerFactory.getLogger(GatewayAutoConfig.class);

    @Bean
    public GatewayCenterService registerGatewayService(){
        return new GatewayCenterService();
    }

    @Bean
    public GatewayApplication gatewayApplication(GatewayServiceProperties properties, GatewayCenterService registerGatewayService,
                                                 Configuration configuration, Channel gatewaySocketServerChannel){
        return new GatewayApplication(properties, registerGatewayService,configuration, gatewaySocketServerChannel);
    }

    /**
     * 创建网关配置对象
     * @param properties
     * @return
     */
    @Bean
    public Configuration gatewayCoreConfiguration(GatewayServiceProperties properties){
        Configuration configuration = new Configuration();
        String[] split = properties.getGatewayAddress().split(":");
        configuration.setHostName(split[0]);
        configuration.setPort(Integer.parseInt(split[1]));
        return configuration;
    }

    /**
     * 初始化网关服务
     * @param configuration
     * @return
     */
    @Bean
    public Channel initChannel(Configuration configuration) throws ExecutionException, InterruptedException {
        //基于配置构建会话工厂
        DefaultGatewaySessionFactory gatewaySessionFactory = new DefaultGatewaySessionFactory(configuration);
        //创建启动网关网络服务
        GatewaySocketServer server = new GatewaySocketServer(configuration, gatewaySessionFactory);
        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = future.get();
        if(null == channel) throw new RuntimeException("netty server start error, channel is null");
        while(!channel.isActive()){
            log.info("netty server gateway starting...");
            Thread.sleep(500);
        }
        log.info("netty server gateway start done!{}",channel.localAddress());
        return channel;
    }

}
