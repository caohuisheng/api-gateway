package cn.bugstack.gateway.core.session.defaults;

import cn.bugstack.gateway.core.bind.IGenericReference;
import cn.bugstack.gateway.core.session.Configuration;
import cn.bugstack.gateway.core.session.GatewaySession;
import cn.bugstack.gateway.core.executor.Executor;
import cn.bugstack.gateway.core.mapping.HttpStatement;

import java.util.Map;

/**
 * Author: chs
 * Description: 默认网关会话实现类
 * CreateTime: 2024-09-01
 */
public class DefaultGatewaySession implements GatewaySession {

    private Configuration configuration;
    private String uri;
    private Executor executor;

    public DefaultGatewaySession(Configuration configuration, String uri, Executor executor){
        this.configuration = configuration;
        this.uri = uri;
        this.executor = executor;
    }

    @Override
    public Object get(String methodName, Map<String, Object> params) {
        try {
            HttpStatement httpStatement = configuration.getHttpStatement(uri);
            return executor.exec(httpStatement, params);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error exec get method", e);
        }
    }

    @Override
    public IGenericReference getMapper() {
        return configuration.getMapper(uri,this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

}
