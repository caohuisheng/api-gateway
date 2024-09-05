package cn.bugstack.gateway.datasource.unpooled;

import cn.bugstack.gateway.datasource.Connection;
import cn.bugstack.gateway.datasource.DataSource;
import cn.bugstack.gateway.datasource.DataSourceType;
import cn.bugstack.gateway.datasource.connection.DubboConnection;
import cn.bugstack.gateway.mapping.HttpStatement;
import cn.bugstack.gateway.session.Configuration;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * Author: chs
 * Description: 无池化的数据源
 * CreateTime: 2024-09-02
 */
public class UnpooledDataSource implements DataSource {

    private Configuration configuration;
    private HttpStatement httpStatement;
    private DataSourceType dataSourceType;

    @Override
    public Connection getConnection() {
        switch(dataSourceType){
            case HTTP:
                //TODO 预留接口
                break;
            case Dubbo:
                String application = httpStatement.getApplication();
                String interfaceName = httpStatement.getInterfaceName();
                ApplicationConfig applicationConfig = configuration.getApplicationConfig(application);
                RegistryConfig registryConfig = configuration.getRegistryConfig(application);
                ReferenceConfig<GenericService> reference = configuration.getReferenceConfig(interfaceName);
                return new DubboConnection(applicationConfig, registryConfig, reference);
            default:
                break;
        }
        throw new RuntimeException("DataSourceType:" + dataSourceType + " 没有对应的数据源");
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setHttpStatement(HttpStatement httpStatement) {
        this.httpStatement = httpStatement;
    }

    public void setDataSourceType(DataSourceType dataSourceType) {
        this.dataSourceType = dataSourceType;
    }
}
