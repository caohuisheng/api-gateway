package cn.bugstack.gateway.test;

import cn.bugstack.gateway.core.mapping.HttpCommandType;
import cn.bugstack.gateway.core.mapping.HttpStatement;
import cn.bugstack.gateway.core.session.Configuration;
import cn.bugstack.gateway.core.session.defaults.DefaultGatewaySessionFactory;
import cn.bugstack.gateway.core.socket.GatewaySocketServer;
import io.netty.channel.Channel;
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
        //1.创建配置信息加载注册
        Configuration configuration = new Configuration();
        configuration.setHostName("127.0.0.1");
        configuration.setPort(7397);
        //2.基于配置构建会话工厂
        DefaultGatewaySessionFactory gatewaySessionFactory = new DefaultGatewaySessionFactory(configuration);

        //3.创建启动网关网络服务
        GatewaySocketServer server = new GatewaySocketServer(configuration, gatewaySessionFactory);
        //获取channel
        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = future.get();
        if(null == channel) throw new RuntimeException("netty server start error channel is null.");
        //等待channel状态为活跃
        while(!channel.isActive()){
            log.info("netty server gateway starting...");
            Thread.sleep(500);
        }
        log.info("netty server gateway start done {}", channel.localAddress());

        //4.注册接口和方法
        configuration.registryConfig("api-gateway-test","zookeeper://116.62.8.243:2181?timeout=600000",
                "cn.bugstack.gateway.rpc.IActivityBooth","1.0.0");
        HttpStatement httpStatement1 = new HttpStatement("api-gateway-test","cn.bugstack.gateway.rpc.IActivityBooth",
                "sayHi","/wg/activity/sayHi","java.lang.String", HttpCommandType.GET,false);
        configuration.addMapper(httpStatement1);
        HttpStatement httpStatement2 = new HttpStatement("api-gateway-test","cn.bugstack.gateway.rpc.IActivityBooth",
                "insert","/wg/activity/insert","cn.bugstack.gateway.rpc.dto.XReq", HttpCommandType.GET,true);
        configuration.addMapper(httpStatement2);

        Thread.sleep(Long.MAX_VALUE);
    }
}
