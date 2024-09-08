package cn.bugstack.gateway.executor;

import cn.bugstack.gateway.datasource.Connection;
import cn.bugstack.gateway.executor.result.SessionResult;
import cn.bugstack.gateway.mapping.HttpStatement;
import cn.bugstack.gateway.session.Configuration;
import cn.bugstack.gateway.type.SimpleTypeRegistry;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


/**
 * Author: chs
 * Description: 基础执行器
 * CreateTime: 2024-09-06
 */
public abstract class BaseExecutor implements Executor{

    private Logger log = LoggerFactory.getLogger(BaseExecutor.class);

    protected Configuration configuration;
    protected Connection connection;

    public BaseExecutor(Configuration configuration, Connection connection){
        this.configuration = configuration;
        this.connection = connection;
    }

    @Override
    public SessionResult exec(HttpStatement httpStatement, Map<String, Object> params) throws RuntimeException {
        String methodName = httpStatement.getMethodName();
        String parameterType = httpStatement.getParameterType();
        String[] parameterTypes = new String[]{parameterType};
        Object[] args = SimpleTypeRegistry.isSimpleType(parameterType)?params.values().toArray():new Object[]{params};
        log.info("执行调用 method:{}#{}.{}({})",httpStatement.getApplication(),httpStatement.getInterfaceName(),
                JSON.toJSONString(parameterTypes),JSON.toJSONString(params));
        //抽象方法
        try {
            Object data = doExec(methodName, parameterTypes, args);
            return SessionResult.buildSuccess(data);
        } catch (Exception e) {
            e.printStackTrace();
            return SessionResult.buildError(e.getMessage());
        }
    }

    protected abstract Object doExec(String methodName, String[] parameterTypes, Object[] args);
}
