package cn.bugstack.gateway.assist;

/**
 * Author: chs
 * Description: 网关异常
 * CreateTime: 2024-09-12
 */
public class GatewayException extends RuntimeException {

    public GatewayException(String msg){
        super(msg);
    }

    public GatewayException(String msg, Throwable throwable){
        super(msg, throwable);
    }

}
