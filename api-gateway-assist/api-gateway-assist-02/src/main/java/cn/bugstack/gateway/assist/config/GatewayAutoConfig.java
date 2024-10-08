package cn.bugstack.gateway.assist.config;

import cn.bugstack.gateway.assist.application.GatewayApplication;
import cn.bugstack.gateway.assist.domain.service.GatewayCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: chs
 * Description: 网关服务配置
 * CreateTime: 2024-09-13
 */
@Configuration
@EnableConfigurationProperties(GatewayServiceProperties.class)
public class GatewayAutoConfig {

    private Logger log = LoggerFactory.getLogger(GatewayAutoConfig.class);

    @Bean
    public GatewayCenterService registerGatewayService(){
        return new GatewayCenterService();
    }

    @Bean
    public GatewayApplication gatewayApplication(GatewayServiceProperties properties, GatewayCenterService registerGatewayService){
        return new GatewayApplication(properties, registerGatewayService);
    }
}
