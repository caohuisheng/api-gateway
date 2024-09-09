package cn.bugstack.gateway.infrastucture.po;

import java.util.Date;

/**
 * Author: chs
 * Description: 网关接口映射信息表
 * CreateTime: 2024-09-09
 */
public class HttpStatement {

    // 自增ID
    private String id;
    // 应用名称
    private String application;
    // 服务接口
    private String interfaceName;
    // 服务方法
    private String methodName;
    // 参数类型
    private String parameterType;
    // 网关接口
    private String uri;
    // 接口类型
    private String httpCommandType;
    // 是否需要校验
    private Integer auth;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHttpCommandType() {
        return httpCommandType;
    }

    public void setHttpCommandType(String httpCommandType) {
        this.httpCommandType = httpCommandType;
    }

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
