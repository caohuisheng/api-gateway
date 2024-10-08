package cn.bugstack.gateway.assist.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Author: chs
 * Description: 网关服务注册配置
 * CreateTime: 2024-09-12
 */
@ConfigurationProperties("api-gateway")
public class GatewayServiceProperties {

    //注册中心地址
    private String address;
    //分组id
    private String groupId;
    //网关id
    private String gatewayId;
    //网关名称
    private String gatewayName;
    //网关地址
    private String gatewayAddress;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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
}
