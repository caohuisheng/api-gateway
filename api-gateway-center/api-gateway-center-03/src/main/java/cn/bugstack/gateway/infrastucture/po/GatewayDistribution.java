package cn.bugstack.gateway.infrastucture.po;

/**
 * Author: chs
 * Description: 网关分配
 * CreateTime: 2024-09-10
 */
public class GatewayDistribution {

    // 自增主键
    private String id;
    // 分组标识
    private String groupId;
    // 网关标识
    private String gateway_id;
    // 系统标识
    private String systemId;
    // 系统名称
    private String systemName;
    // 创建时间
    private String createTime;
    // 更新时间
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGateway_id() {
        return gateway_id;
    }

    public void setGateway_id(String gateway_id) {
        this.gateway_id = gateway_id;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
