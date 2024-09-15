package cn.bugstack.gateway.core.datasource;

/**
 * Author: chs
 * Description: 数据源接口
 * CreateTime: 2024-09-02
 */
public interface DataSource {

    Connection getConnection();

}
