package cn.bugstack.gateway.datasource;

import cn.bugstack.gateway.session.Configuration;

/**
 * Author: chs
 * Description: 数据源工厂
 * CreateTime: 2024-09-02
 */
public interface DataSourceFactory {

    void setProperties(Configuration configuration, String uri);

    DataSource getDataSource();

}
