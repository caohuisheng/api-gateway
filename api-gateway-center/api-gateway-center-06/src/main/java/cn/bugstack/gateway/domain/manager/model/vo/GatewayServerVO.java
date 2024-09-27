package cn.bugstack.gateway.domain.manager.model.vo;

/**
 * Author: chs
 * Description: 网关服务配置VO
 * CreateTime: 2024-09-11
 */
public class GatewayServerVO {
    //分组标识
    private String groupId;
    //分组名称
    private String groupName;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
