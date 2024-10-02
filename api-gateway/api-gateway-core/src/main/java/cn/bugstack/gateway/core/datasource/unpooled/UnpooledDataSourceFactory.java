package cn.bugstack.gateway.core.datasource.unpooled;

import cn.bugstack.gateway.core.datasource.DataSource;
import cn.bugstack.gateway.core.datasource.DataSourceType;
import cn.bugstack.gateway.core.session.Configuration;
import cn.bugstack.gateway.core.datasource.DataSourceFactory;

/**
 * Author: chs
 * Description: 无池化数据源工厂
 * CreateTime: 2024-09-02
 */
public class UnpooledDataSourceFactory implements DataSourceFactory {

    protected UnpooledDataSource dataSource;

    public UnpooledDataSourceFactory(){
        this.dataSource = new UnpooledDataSource();
    }

    @Override
    public void setProperties(Configuration configuration, String uri) {
        dataSource.setConfiguration(configuration);
        dataSource.setDataSourceType(DataSourceType.Dubbo);
        dataSource.setHttpStatement(configuration.getHttpStatement(uri));
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }
}
