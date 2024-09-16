package cn.bugstack.gateway.core.bind;

import cn.bugstack.gateway.core.mapping.HttpStatement;
import cn.bugstack.gateway.core.session.Configuration;
import cn.bugstack.gateway.core.session.GatewaySession;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: chs
 * Description: 泛化调用注册器
 * CreateTime: 2024-09-01
 */
public class MapperRegistry {

    private final Configuration configuration;

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    //泛化调用静态代理工厂
    private final Map<String, MapperProxyFactory> knownMappers = new HashMap<>();

    public IGenericReference getMapper(String uri, GatewaySession gatewaySession){
        final MapperProxyFactory mapperProxyFactory = knownMappers.get(uri);
        if(mapperProxyFactory == null){
            throw new RuntimeException("uri " + uri + " is not known to the MapperRegistry.");
        }
        try{
            return mapperProxyFactory.newInstance(gatewaySession);
        }catch(Exception e){
            throw new RuntimeException("Error getting mapper instance. Cause:" + e);
        }
    }

    public void addMapper(HttpStatement httpStatement){
        String uri = httpStatement.getUri();
        //判断是否重复注册
        if(hasMapper(uri)){
            throw new RuntimeException("Uri " + uri + " is already known to the MapperRegistry.");
        }
        knownMappers.put(uri, new MapperProxyFactory(uri));
        //保存接口映射信息
        configuration.addHttpStatement(httpStatement);
    }

    public <T> boolean hasMapper(String uri){
        return knownMappers.containsKey(uri);
    }

}
