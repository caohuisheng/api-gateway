package cn.bugstack.gateway.core.executor;

import cn.bugstack.gateway.core.executor.result.SessionResult;
import cn.bugstack.gateway.core.mapping.HttpStatement;

import java.util.Map;

/**
 * Author: chs
 * Description: 执行器
 * CreateTime: 2024-09-06
 */
public interface Executor {

    SessionResult exec(HttpStatement httpStatement, Map<String,Object> params) throws RuntimeException;

}
