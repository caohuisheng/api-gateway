package cn.bugstack.gateway.sdk.config;

import cn.bugstack.gateway.sdk.application.GatewaySDKApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: chs
 * Description: 网关SDK配置服务
 * CreateTime: 2024-09-17
 */
@Configuration
@EnableConfigurationProperties(GatewaySDKServiceProperties.class)
public class GatewaySDKAutoConfig {

    private Logger log = LoggerFactory.getLogger(GatewaySDKAutoConfig.class);

    @Bean
    public GatewaySDKApplication gatewaySDKApplication(GatewaySDKServiceProperties properties){
        return new GatewaySDKApplication(properties);
    }

}
