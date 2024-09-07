package cn.bugstack.gateway.executor;

import cn.bugstack.gateway.executor.result.GatewayResult;
import cn.bugstack.gateway.mapping.HttpStatement;

import java.util.Map;

/**
 * Author: chs
 * Description: 执行器
 * CreateTime: 2024-09-06
 */
public interface Executor {

    GatewayResult exec(HttpStatement httpStatement, Map<String,Object> params) throws RuntimeException;

}
