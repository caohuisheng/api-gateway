package cn.bugstack.gateway.test;

import cn.bugstack.gateway.session.SessionServer;
import io.netty.channel.Channel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-08-27
 */
public class ApiTest {

    private final Logger log = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test() throws ExecutionException, InterruptedException {
        SessionServer server = new SessionServer();

        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = future.get();

        if(null == channel) throw new RuntimeException("netty server start field, channel is null");

        while(!channel.isActive()){
            log.info("NettyServer启动服务...");
            Thread.sleep(500);
        }
        log.info("NettyServer启动服务完成 {}", channel.localAddress());

        Thread.sleep(Long.MAX_VALUE);
    }

}
