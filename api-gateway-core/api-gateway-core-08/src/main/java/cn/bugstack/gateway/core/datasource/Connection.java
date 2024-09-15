package cn.bugstack.gateway.core.datasource;

/**
 * Author: chs
 * Description: 连接接口
 * CreateTime: 2024-09-02
 */
public interface Connection {

    Object execute(String methodName, String[] parameterTypes, String[] parameterNames, Object[] args);

}
