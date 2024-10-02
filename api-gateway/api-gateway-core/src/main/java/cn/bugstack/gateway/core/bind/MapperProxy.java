package cn.bugstack.gateway.core.bind;

import cn.bugstack.gateway.core.session.GatewaySession;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Author: chs
 * Description: 映射代理调用
 * CreateTime: 2024-09-01
 */
public class MapperProxy implements MethodInterceptor {

    private GatewaySession gatewaySession;
    private final String uri;

    public MapperProxy(GatewaySession gatewaySession, String uri) {
        this.gatewaySession = gatewaySession;
        this.uri = uri;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy){
        MapperMethod linkMethod = new MapperMethod(uri, method, gatewaySession.getConfiguration());
        //暂时只获取第0个参数
        return linkMethod.execute(gatewaySession, (Map<String, Object>) args[0]);
    }
}
