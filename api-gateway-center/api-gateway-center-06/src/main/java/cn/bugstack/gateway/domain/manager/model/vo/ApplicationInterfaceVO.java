package cn.bugstack.gateway.domain.manager.model.vo;

import java.util.Date;
import java.util.List;

/**
 * Author: chs
 * Description: 应用接口
 * CreateTime: 2024-09-10
 */
public class ApplicationInterfaceVO {

    // 系统标识
    private String systemId;
    // 接口标识
    private String interfaceId;
    // 接口名称
    private String interfaceName;
    // 接口版本
    private String interfaceVersion;
    // 接口中的方法
    private List<ApplicationInterfaceMethodVO> methodList;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInterfaceVersion() {
        return interfaceVersion;
    }

    public void setInterfaceVersion(String interfaceVersion) {
        this.interfaceVersion = interfaceVersion;
    }

    public List<ApplicationInterfaceMethodVO> getMethodList() {
        return methodList;
    }

    public void setMethodList(List<ApplicationInterfaceMethodVO> methodList) {
        this.methodList = methodList;
    }
}
