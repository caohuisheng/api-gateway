package cn.bugstack.gateway.bind;

import cn.bugstack.gateway.executor.result.SessionResult;

import java.util.Map;

/**
 * Author: chs
 * Description: 统一泛化调用接口
 * CreateTime: 2024-08-29
 */
public interface IGenericReference {

    SessionResult $invoke(Map<String, Object> args);

}
