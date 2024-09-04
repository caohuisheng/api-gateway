package cn.bugstack.gateway.datasource.connection;

import cn.bugstack.gateway.bind.IGenericReference;
import cn.bugstack.gateway.datasource.Connection;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * Author: chs
 * Description: Dubbo连接
 * CreateTime: 2024-09-02
 */
public class DubboConnection implements Connection {

    private final GenericService genericService;

    public DubboConnection(ApplicationConfig applicationConfig, RegistryConfig registryConfig, ReferenceConfig<GenericService> reference){
        //连接远程服务
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(applicationConfig).registry(registryConfig).reference(reference).start();
        //获取泛化调用接口
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        genericService = cache.get(reference);
    }

    @Override
    public Object execute(String methodName, String[] parameterTypes, String[] parameterNames, Object[] args) {
        return genericService.$invoke(methodName, parameterTypes, args);
    }
}
