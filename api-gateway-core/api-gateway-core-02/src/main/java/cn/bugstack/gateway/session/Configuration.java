package cn.bugstack.gateway.session;

import cn.bugstack.gateway.bind.GenericReferenceRegistry;
import cn.bugstack.gateway.bind.IGenericReference;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.lang.ref.Reference;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: chs
 * Description: 会话生命周期配置项
 * CreateTime: 2024-08-29
 */
public class Configuration {

    private final GenericReferenceRegistry registry = new GenericReferenceRegistry(this);

    //RPC 应用服务配置项
    private final Map<String, ApplicationConfig> applicationConfigMap = new HashMap<>();
    //RPC 注册中心配置项
    private final Map<String, RegistryConfig> registryConfigMap = new HashMap<>();
    //RPC 泛化服务配置项
    private final Map<String, ReferenceConfig<GenericService>> referenceConfigMap = new HashMap<>();

    public Configuration(){
        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-gateway-test");
        application.setQosEnable(false);

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://116.62.8.243:2181?timeout=600000");
        registry.setRegister(false);

        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface("cn.bugstack.gateway.rpc.IActivityBooth");
        reference.setVersion("1.0.0");
        reference.setGeneric("true");

        applicationConfigMap.put("api-gateway-test",application);
        registryConfigMap.put("api-gateway-test",registry);
        referenceConfigMap.put("cn.bugstack.gateway.rpc.IActivityBooth", reference);
    }

    public ApplicationConfig getApplicationConfig(String applicationName){
        return applicationConfigMap.get(applicationName);
    }

    public RegistryConfig getRegistryConfig(String applicationName) {
        return registryConfigMap.get(applicationName);
    }

    public ReferenceConfig<GenericService> getReferenceConfig(String applicationName) {
        return referenceConfigMap.get(applicationName);
    }

    public void addGenericReference(String application, String interfaceName, String methodName){
        registry.addGenericReference(application, interfaceName, methodName);
    }

    public IGenericReference getGenericReference(String methodName){
        return registry.getGenericReference(methodName);
    }
}
