package cn.bugstack.gateway.domain.message.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Author: chs
 * Description: 消息监听推送配置
 * CreateTime: 2024-09-19
 */
@Configuration
public class PublisherConfig {

    @Bean
    public RedisTemplate<String,Object> redisMessageTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setDefaultSerializer(new FastJsonRedisSerializer<>(Object.class));
        return template;
    }

}
