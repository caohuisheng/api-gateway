package cn.bugstack.gateway.core.session;

import cn.bugstack.gateway.core.authorization.IAuth;
import cn.bugstack.gateway.core.authorization.auth.AuthService;
import cn.bugstack.gateway.core.bind.IGenericReference;
import cn.bugstack.gateway.core.bind.MapperRegistry;
import cn.bugstack.gateway.core.datasource.Connection;
import cn.bugstack.gateway.core.executor.Executor;
import cn.bugstack.gateway.core.executor.SimpleExecutor;
import cn.bugstack.gateway.core.mapping.HttpStatement;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: chs
 * Description: 会话生命周期配置项
 * CreateTime: 2024-08-29
 */
public class Configuration {

    //网关Netty服务地址
    private String hostName = "127.0.0.1";
    //网关Netty服务端口
    private int port = 7397;
    //网关Netty服务线程数配置
    private int bossNThreads = 1;
    private int workNThreads = 4;

    private final MapperRegistry registry = new MapperRegistry(this);

    private Map<String, HttpStatement> httpStatements = new HashMap<>();
    private IAuth authService = new AuthService();

    //RPC 应用服务配置项
    private final Map<String, ApplicationConfig> applicationConfigMap = new HashMap<>();
    //RPC 注册中心配置项
    private final Map<String, RegistryConfig> registryConfigMap = new HashMap<>();
    //RPC 泛化服务配置项
    private final Map<String, ReferenceConfig<GenericService>> referenceConfigMap = new HashMap<>();

    public Configuration(){
    }

    public synchronized void registryConfig(String applicationName, String address, String interfaceName, String version){
        if(applicationConfigMap.get(applicationName) == null){
            ApplicationConfig application = new ApplicationConfig();
            application.setName(applicationName);
            application.setQosEnable(false);
            applicationConfigMap.put(applicationName,application);
        }

        if(registryConfigMap.get(applicationName) == null){
            RegistryConfig registry = new RegistryConfig();
            registry.setAddress(address);
            registry.setRegister(false);
            registryConfigMap.put(applicationName,registry);
        }

        if(referenceConfigMap.get(interfaceName) == null){
            ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
            reference.setInterface(interfaceName);
            reference.setVersion(version);
            reference.setGeneric("true");
            referenceConfigMap.put(interfaceName, reference);
        }
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

    public void addMapper(HttpStatement httpStatement){
        registry.addMapper(httpStatement);
    }

    public IGenericReference getMapper(String uri, GatewaySession gatewaySession){
        return registry.getMapper(uri, gatewaySession);
    }

    public void addHttpStatement(HttpStatement httpStatement){
        httpStatements.put(httpStatement.getUri(), httpStatement);
    }

    public HttpStatement getHttpStatement(String uri){
        return httpStatements.get(uri);
    }

    public Executor newExecutor(Connection connection){
        return new SimpleExecutor(this, connection);
    }

    public boolean authValid(String uid, String token){
        return authService.validate(uid, token);
    }

    public String getHostName() {
        return hostName;
    }

    public int getPort() {
        return port;
    }

    public int getBossNThreads() {
        return bossNThreads;
    }

    public int getWorkNThreads() {
        return workNThreads;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setBossNThreads(int bossNThreads) {
        this.bossNThreads = bossNThreads;
    }

    public void setWorkNThreads(int workNThreads) {
        this.workNThreads = workNThreads;
    }
}
