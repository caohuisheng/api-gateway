package cn.bugstack.gateway.domain.operation.model.vo;

/**
 * Author: chs
 * Description: 网关服务器详细信息
 * CreateTime: 2024-09-10
 */
public class GatewayServerDetailDataVO {

    // 分组标识
    private String groupId;
    // 网关标识
    private String gatewayId;
    // 网关名称
    private String gatewayName;
    // 网关地址
    private String gatewayAddress;
    // 服务状态：0不可用、1可使用
    private Integer status;

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

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public String getGatewayAddress() {
        return gatewayAddress;
    }

    public void setGatewayAddress(String gatewayAddress) {
        this.gatewayAddress = gatewayAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
