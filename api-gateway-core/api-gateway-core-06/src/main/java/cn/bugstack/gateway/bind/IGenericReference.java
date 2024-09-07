package cn.bugstack.gateway.bind;

import java.util.Map;

/**
 * Author: chs
 * Description: 统一泛化调用接口
 * CreateTime: 2024-08-29
 */
public interface IGenericReference {

    Object $invoke(Map<String, Object> args);

}
