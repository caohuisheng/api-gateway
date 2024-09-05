package cn.bugstack.gateway.session.defaults;

import cn.bugstack.gateway.bind.IGenericReference;
import cn.bugstack.gateway.datasource.Connection;
import cn.bugstack.gateway.datasource.DataSource;
import cn.bugstack.gateway.mapping.HttpStatement;
import cn.bugstack.gateway.session.Configuration;
import cn.bugstack.gateway.session.GatewaySession;
import cn.bugstack.gateway.type.SimpleTypeRegistry;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.Map;

/**
 * Author: chs
 * Description: 默认网关会话实现类
 * CreateTime: 2024-09-01
 */
public class DefaultGatewaySession implements GatewaySession {

    private Configuration configuration;
    private String uri;
    private DataSource dataSource;

    public DefaultGatewaySession(Configuration configuration, String uri, DataSource dataSource){
        this.configuration = configuration;
        this.uri = uri;
        this.dataSource = dataSource;
    }

    @Override
    public Object get(String methodName, Map<String, Object> params) {
        Connection connection = dataSource.getConnection();
        HttpStatement httpStatement = configuration.getHttpStatement(uri);
        String parameterType = httpStatement.getParameterType();

        return connection.execute(methodName,
                new String[]{parameterType},
                new String[]{"ignore"},
                SimpleTypeRegistry.isSimpleType(parameterType) ? params.values().toArray() : new Object[]{params});
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
