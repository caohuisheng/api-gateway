package cn.bugstack.gateway.domain.operation.model.vo;

/**
 * Author: chs
 * Description: 网关分配
 * CreateTime: 2024-09-10
 */
public class GatewayDistributionDataVO {

    // 分组标识
    private String groupId;
    // 网关标识
    private String gatewayId;
    // 系统标识
    private String systemId;
    // 系统名称
    private String systemName;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

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

}