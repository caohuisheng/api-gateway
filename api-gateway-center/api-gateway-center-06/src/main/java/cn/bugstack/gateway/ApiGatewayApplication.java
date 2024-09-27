package cn.bugstack.gateway;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-09-09
 */
@SpringBootApplication
@Configurable
public class ApiGatewayApplication {

    public static void main(String[] args){
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}
