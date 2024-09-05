package cn.bugstack.gateway.session.defaults;

import cn.bugstack.gateway.datasource.DataSource;
import cn.bugstack.gateway.datasource.DataSourceFactory;
import cn.bugstack.gateway.datasource.unpooled.UnpooledDataSourceFactory;
import cn.bugstack.gateway.session.Configuration;
import cn.bugstack.gateway.session.GatewaySession;
import cn.bugstack.gateway.session.GatewaySessionFactory;

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

        return new DefaultGatewaySession(configuration, uri, dataSource);
    }
}
