package cn.bugstack.gateway.session.defaults;

import cn.bugstack.gateway.bind.GenericReferenceRegistry;
import cn.bugstack.gateway.session.Configuration;
import cn.bugstack.gateway.session.IGenericReferenceSessionFactory;
import cn.bugstack.gateway.session.SessionServer;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Author: chs
 * Description: 泛化调用会话工厂
 * CreateTime: 2024-08-30
 */
public class GenericReferenceSessionFactory implements IGenericReferenceSessionFactory {

    private final Logger log = LoggerFactory.getLogger(GenericReferenceSessionFactory.class);

    private final Configuration configuration;

    public GenericReferenceSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public Future<Channel> openSession() throws ExecutionException, InterruptedException {
        SessionServer server = new SessionServer(configuration);

        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = future.get();

        if(null == channel) throw new RuntimeException("netty server start error, channel is null.");

        while(!channel.isActive()){
            log.info("netty server gateway starting...");
            Thread.sleep(500);
        }
        log.info("netty server gateway start done!{}",channel.localAddress());

        return future;
    }
}
