package cn.bugstack.gateway.application;

import java.util.Map;

/**
 * Author: chs
 * Description: 消息服务接口
 * CreateTime: 2024-09-19
 */
public interface IMessageService {

    Map<String,String> queryRedisConfig();

    void pushMessage(String gatewayId,Object message);

}
