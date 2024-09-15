package cn.bugstack.gateway.assist.application;

import cn.bugstack.gateway.assist.config.GatewayServiceProperties;
import cn.bugstack.gateway.assist.domain.service.GatewayCenterService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Author: chs
 * Description: 网关应用：与Spring链接，调用网关注册和接口拉取
 * CreateTime: 2024-09-13
 */
public class GatewayApplication implements ApplicationListener<ContextRefreshedEvent> {

    private GatewayServiceProperties properties;
    private GatewayCenterService gatewayCenterService;

    public GatewayApplication(GatewayServiceProperties properties,GatewayCenterService gatewayCenterService){
        this.properties = properties;
        this.gatewayCenterService = gatewayCenterService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //1.注册网关服务
        gatewayCenterService.doRegister(properties.getAddress(),
                properties.getGroupId(),
                properties.getGatewayId(),
                properties.getGatewayName(),
                properties.getGatewayAddress());

        //2.拉取网关配置
        gatewayCenterService.pullApplicationSystemRichInfo(properties.getAddress(), properties.getGatewayId());
    }

}
