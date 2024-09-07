package cn.bugstack.gateway.session;

import cn.bugstack.gateway.bind.IGenericReference;

import java.util.Map;

/**
 * Author: chs
 * Description: 用户处理网关http请求
 * CreateTime: 2024-09-01
 */
public interface GatewaySession {

    Object get(String uri, Map<String, Object> params);

    IGenericReference getMapper();

    Configuration getConfiguration();

}
