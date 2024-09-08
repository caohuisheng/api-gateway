package cn.bugstack.gateway.executor;

import cn.bugstack.gateway.datasource.Connection;
import cn.bugstack.gateway.session.Configuration;

/**
 * Author: chs
 * Description: 简单执行器
 * CreateTime: 2024-09-06
 */
public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration configuration, Connection connection){
        super(configuration, connection);
    }

    @Override
    protected Object doExec(String methodName, String[] parameterTypes, Object[] args) {
        return connection.execute(methodName, parameterTypes, new String[]{"ignore"}, args);
    }
}
