package cn.bugstack.gateway.core.authorization;

/**
 * Author: chs
 * Description: 认证服务接口
 * CreateTime: 2024-09-08
 */
public interface IAuth {

    boolean validate(String id, String token);

}
