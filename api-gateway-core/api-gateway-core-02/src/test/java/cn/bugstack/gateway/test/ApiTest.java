package cn.bugstack.gateway.test;

import cn.bugstack.gateway.session.Configuration;
import cn.bugstack.gateway.session.GenericReferenceSessionFactoryBuilder;
import cn.bugstack.gateway.session.IGenericReferenceSessionFactory;
import io.netty.channel.Channel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-08-31
 */
public class ApiTest {

    private final Logger log = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_genericReference() throws Exception {
        Configuration configuration = new Configuration();
        configuration.addGenericReference("api-gateway-test","cn.bugstack.gateway.rpc.IActivityBooth","sayHi");

        GenericReferenceSessionFactoryBuilder builder = new GenericReferenceSessionFactoryBuilder();
        Future<Channel> future = builder.build(configuration);

        log.info("服务启动完成 {}", future.get().id());

        Thread.sleep(Long.MAX_VALUE);
    }
}
