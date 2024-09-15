package cn.bugstack.gateway.core.session.defaults;

import cn.bugstack.gateway.core.datasource.DataSource;
import cn.bugstack.gateway.core.datasource.DataSourceFactory;
import cn.bugstack.gateway.core.datasource.unpooled.UnpooledDataSourceFactory;
import cn.bugstack.gateway.core.executor.Executor;
import cn.bugstack.gateway.core.session.Configuration;
import cn.bugstack.gateway.core.session.GatewaySession;
import cn.bugstack.gateway.core.session.GatewaySessionFactory;

/**
 * Author: chs
 * Description: 默认网关会话工厂
 * CreateTime: 2024-09-01
 */
public class DefaultGatewaySessionFactory implements GatewaySessionFactory {

    private final Configuration configuration;

    public DefaultGatewaySessionFactory(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public GatewaySession openSession(String uri) {
        DataSourceFactory dataSourceFactory = new UnpooledDataSourceFactory();
        dataSourceFactory.setProperties(configuration, uri);
        DataSource dataSource = dataSourceFactory.getDataSource();
        //创建执行器
        Executor executor = configuration.newExecutor(dataSource.getConnection());
        //创建会话
        return new DefaultGatewaySession(configuration, uri, executor);
    }
}
