package cn.bugstack.gateway.bind;

import cn.bugstack.gateway.mapping.HttpCommandType;
import cn.bugstack.gateway.session.Configuration;
import cn.bugstack.gateway.session.GatewaySession;

import java.lang.reflect.Method;

/**
 * Author: chs
 * Description: 绑定调用方法
 * CreateTime: 2024-09-01
 */
public class MapperMethod {

    private String uri;
    private final HttpCommandType command;

    public MapperMethod(String uri, Method method, Configuration configuration) {
        this.uri = uri;
        this.command = configuration.getHttpStatement(uri).getHttpCommandType();
    }

    public Object execute(GatewaySession session, Object args){
        Object result = null;
        switch(command){
            case GET:
                result = session.get(uri, args);
                break;
            case POST:
                break;
            case PUT:
                break;
            case DELETE:
                break;
            default:
                throw new RuntimeException("Unknown execution method for:" + command);
        }
        return result;
    }
}
