package cn.bugstack.gateway.sdk;

/**
 * Author: chs
 * Description: 网关异常
 * CreateTime: 2024-09-18
 */
public class GatewayException extends RuntimeException{

    public GatewayException(String msg){
        super(msg);
    }

    public GatewayException(String msg, Throwable throwable){
        super(msg, throwable);
    }

}
