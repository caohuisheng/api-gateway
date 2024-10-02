package cn.bugstack.gateway.core.datasource;

import cn.bugstack.gateway.core.session.Configuration;

/**
 * Author: chs
 * Description: 数据源工厂
 * CreateTime: 2024-09-02
 */
public interface DataSourceFactory {

    void setProperties(Configuration configuration, String uri);

    DataSource getDataSource();

}
