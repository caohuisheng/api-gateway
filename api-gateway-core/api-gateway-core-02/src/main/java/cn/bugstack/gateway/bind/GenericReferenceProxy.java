package cn.bugstack.gateway.bind;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.dubbo.rpc.service.GenericService;

import java.lang.reflect.Method;

/**
 * Author: chs
 * Description: 泛化调用静态代理
 * CreateTime: 2024-08-29
 */
public class GenericReferenceProxy implements MethodInterceptor {

    //RPC泛化调用服务
    private final GenericService genericService;

    //RPC泛化调用方法
    private final String methodName;

    public GenericReferenceProxy(GenericService genericService, String methodName){
        this.genericService = genericService;
        this.methodName = methodName;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Class<?>[] parameterTypes = method.getParameterTypes();
        String[] parameters = new String[parameterTypes.length];
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = parameterTypes[i].getName();
        }

        return genericService.$invoke(methodName, parameters, args);
    }

}
