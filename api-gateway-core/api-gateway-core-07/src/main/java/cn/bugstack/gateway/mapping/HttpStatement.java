package cn.bugstack.gateway.mapping;

/**
 * Author: chs
 * Description: 网关接口映射信息
 * CreateTime: 2024-09-01
 */
public class HttpStatement {

    //应用名称
    private String application;
    //服务接口
    private String interfaceName;
    //服务方法
    private String methodName;
    //网关接口
    private String uri;
    //参数类型
    private String parameterType;
    //接口类型
    private HttpCommandType httpCommandType;
    //是否鉴权
    private boolean auth;

    public HttpStatement(String application, String interfaceName, String methodName, String uri,String parameterType, HttpCommandType httpCommandType, boolean auth) {
        this.application = application;
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.uri = uri;
        this.httpCommandType = httpCommandType;
        this.parameterType = parameterType;
        this.auth = auth;
    }

    public String getApplication() {
        return application;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getUri() {
        return uri;
    }

    public HttpCommandType getHttpCommandType() {
        return httpCommandType;
    }

    public String getParameterType(){
        return parameterType;
    }

    public boolean isAuth(){
        return auth;
    }
}
