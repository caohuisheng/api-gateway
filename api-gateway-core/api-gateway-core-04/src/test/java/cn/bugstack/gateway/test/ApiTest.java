package cn.bugstack.gateway.test;

import cn.bugstack.gateway.mapping.HttpCommandType;
import cn.bugstack.gateway.mapping.HttpStatement;
import cn.bugstack.gateway.session.Configuration;
import cn.bugstack.gateway.session.defaults.DefaultGatewaySessionFactory;
import cn.bugstack.gateway.session.socket.GatewaySocketServer;
import io.netty.channel.Channel;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-08-31
 */
public class ApiTest {

    private final Logger log = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_gateway() throws InterruptedException, ExecutionException {
        //创建配置信息加载注册
        Configuration configuration = new Configuration();
        HttpStatement httpStatement = new HttpStatement("api-gateway-test","cn.bugstack.gateway.rpc.IActivityBooth",
                "sayHi","/wg/activity/sayHi", HttpCommandType.GET);
        configuration.addMapper(httpStatement);

        //基于配置构建会话工厂
        DefaultGatewaySessionFactory gatewaySessionFactory = new DefaultGatewaySessionFactory(configuration);

        //创建启动网关网络服务
        GatewaySocketServer server = new GatewaySocketServer(gatewaySessionFactory);

        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = future.get();

        if(null == channel) throw new RuntimeException("netty server start error channel is null.");

        while(!channel.isActive()){
            log.info("netty server gateway starting...");
            Thread.sleep(500);
        }
        log.info("netty server gateway start done {}", channel.localAddress());

        Thread.sleep(Long.MAX_VALUE);
    }
}
