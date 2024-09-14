package cn.bugstack.gateway.assist.application;

import cn.bugstack.gateway.assist.config.GatewayServiceProperties;
import cn.bugstack.gateway.assist.service.RegisterGatewayService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: chs
 * Description: 网关应用：与Spring链接，调用网关注册和接口拉取
 * CreateTime: 2024-09-13
 */
public class GatewayApplication implements ApplicationListener<ContextRefreshedEvent> {

    private GatewayServiceProperties properties;
    private RegisterGatewayService registerGatewayService;

    public GatewayApplication(GatewayServiceProperties properties, RegisterGatewayService registerGatewayService){
        this.properties = properties;
        this.registerGatewayService = registerGatewayService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //1.注册网关服务
        registerGatewayService.doRegister(properties.getAddress(),
                properties.getGroupId(),
                properties.getGatewayId(),
                properties.getGatewayName(),
                properties.getGatewayAddress());
    }

}
