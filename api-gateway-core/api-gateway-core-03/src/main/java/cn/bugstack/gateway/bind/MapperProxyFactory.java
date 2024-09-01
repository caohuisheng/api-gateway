package cn.bugstack.gateway.bind;

import cn.bugstack.gateway.mapping.HttpStatement;
import cn.bugstack.gateway.session.GatewaySession;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import org.objectweb.asm.Type;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: chs
 * Description: 泛化调用静态代理工厂
 * CreateTime: 2024-09-01
 */
public class MapperProxyFactory {

    private String uri;

    public MapperProxyFactory(String uri){
        this.uri = uri;
    }

    private final Map<String, IGenericReference> genericReferenceCache = new ConcurrentHashMap<>();

    public IGenericReference newInstance(GatewaySession gatewaySession){
        return genericReferenceCache.computeIfAbsent(uri, k -> {
            HttpStatement httpStatement = gatewaySession.getConfiguration().getHttpStatement(uri);
            //泛化调用
            MapperProxy genericReferenceProxy = new MapperProxy(gatewaySession, uri);
            //创建接口
            InterfaceMaker interfaceMaker = new InterfaceMaker();
            interfaceMaker.add(new Signature(httpStatement.getMethodName(), Type.getType(String.class),new Type[]{Type.getType(String.class)}),null);
            Class<?> interfaceClass = interfaceMaker.create();
            //代理对象
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Object.class);
            enhancer.setInterfaces(new Class[]{IGenericReference.class,interfaceClass});
            enhancer.setCallback(genericReferenceProxy);
            return (IGenericReference) enhancer.create();
        });
    }
;
}
