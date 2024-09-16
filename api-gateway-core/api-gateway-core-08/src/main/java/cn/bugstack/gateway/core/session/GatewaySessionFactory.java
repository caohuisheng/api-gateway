package cn.bugstack.gateway.core.session;

/**
 * Author: chs
 * Description: 网关会话工厂
 * CreateTime: 2024-09-01
 */
public interface GatewaySessionFactory {

    GatewaySession openSession(String uri);

}
