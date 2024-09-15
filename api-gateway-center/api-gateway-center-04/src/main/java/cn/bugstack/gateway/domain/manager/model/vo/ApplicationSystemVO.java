package cn.bugstack.gateway.domain.manager.model.vo;

import java.util.Date;
import java.util.List;

/**
 * Author: chs
 * Description: 应用系统
 * CreateTime: 2024-09-10
 */
public class ApplicationSystemVO {

    // 系统标识
    private String systemId;
    // 系统名称
    private String systemName;
    // 系统类型；RPC、HTTP
    private String systemType;
    // 注册中心
    private String systemRegistry;
    // 系统中的接口
    private List<ApplicationInterfaceVO> interfaceList;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getSystemRegistry() {
        return systemRegistry;
    }

    public void setSystemRegistry(String systemRegistry) {
        this.systemRegistry = systemRegistry;
    }

    public List<ApplicationInterfaceVO> getInterfaceList() {
        return interfaceList;
    }

    public void setInterfaceList(List<ApplicationInterfaceVO> interfaceList) {
        this.interfaceList = interfaceList;
    }
}
