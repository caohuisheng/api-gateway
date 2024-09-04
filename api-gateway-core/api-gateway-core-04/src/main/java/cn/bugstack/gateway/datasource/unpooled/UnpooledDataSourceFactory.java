package cn.bugstack.gateway.datasource.unpooled;

import cn.bugstack.gateway.datasource.DataSource;
import cn.bugstack.gateway.datasource.DataSourceFactory;
import cn.bugstack.gateway.datasource.DataSourceType;
import cn.bugstack.gateway.session.Configuration;
import io.netty.buffer.Unpooled;

/**
 * Author: chs
 * Description: 无池化数据源工厂
 * CreateTime: 2024-09-02
 */
public class UnpooledDataSourceFactory implements DataSourceFactory {

    protected  UnpooledDataSource dataSource;

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
