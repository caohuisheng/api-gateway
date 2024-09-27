package cn.bugstack.gateway.domain.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Author: chs
 * Description: 消息发布者
 * CreateTime: 2024-09-19
 */
@Service
public class Publisher {

    private final RedisTemplate<String, Object> redisMessageTemplate;

    @Autowired
    public Publisher(RedisTemplate<String, Object> redisMessageTemplate){
        this.redisMessageTemplate = redisMessageTemplate;
    }

    public void pushMessage(String topic, Object message){
        redisMessageTemplate.convertAndSend(topic, message);
    }

}
