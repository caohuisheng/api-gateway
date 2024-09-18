package cn.bugstack.gateway.sdk.application;

import cn.bugstack.gateway.sdk.GatewayException;
import cn.bugstack.gateway.sdk.annotation.ApiProducerClazz;
import cn.bugstack.gateway.sdk.annotation.ApiProducerMethod;
import cn.bugstack.gateway.sdk.config.GatewaySDKServiceProperties;
import cn.bugstack.gateway.sdk.domain.service.GatewayCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;

/**
 * Author: chs
 * Description: 应用服务注册
 * CreateTime: 2024-09-17
 */
public class GatewaySDKApplication implements BeanPostProcessor {

    private Logger log = LoggerFactory.getLogger(GatewaySDKApplication.class);

    private GatewaySDKServiceProperties properties;
    private GatewayCenterService gatewayCenterService;

    public GatewaySDKApplication(GatewaySDKServiceProperties properties, GatewayCenterService gatewayCenterService){
        this.properties = properties;
        this.gatewayCenterService = gatewayCenterService;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ApiProducerClazz apiProducerClazz = bean.getClass().getAnnotation(ApiProducerClazz.class);
        if(null == apiProducerClazz) return bean;
        //1.系统信息
        log.info("\n【应用注册:系统信息】 \nsystemId:{} \nsystemName:{} \nsystemType：{} \nsystemRegistry:{}",
                properties.getSystemId(),properties.getSystemName(),"RPC", properties.getSystemRegistry());
        gatewayCenterService.doRegisterApplication(properties.getAddress(),properties.getSystemId(),properties.getSystemName(),"RPC",properties.getSystemRegistry());

        //2.接口信息
        Class<?>[] interfaces = bean.getClass().getInterfaces();
        if(interfaces.length != 1){
            throw new GatewayException("num of interface " + bean.getClass().getName() + " isn't equals 2");
        }
        String interfaceId = interfaces[0].getName();
        log.info("\n【应用注册:接口信息】 \nsystemId:{} \ninterfaceId:{} \ninterfaceName:{} \ninterfaceVersion:{}", properties.getSystemId(),interfaceId,apiProducerClazz.interfaceName(),apiProducerClazz.interfaceVersion());
        gatewayCenterService.doRegisterApplicationInterface(properties.getAddress(),
                properties.getSystemId(),
                interfaceId,
                apiProducerClazz.interfaceName(),
                apiProducerClazz.interfaceVersion());

        //3.方法信息
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            ApiProducerMethod apiProducerMethod = method.getAnnotation(ApiProducerMethod.class);
            if(null == apiProducerMethod) continue;
            //解析参数
            Class<?>[] parameterTypes = method.getParameterTypes();
            StringBuilder sb = new StringBuilder();
            for (Class<?> clazz : parameterTypes) {
                sb.append(clazz.getName()).append(",");
            }
            String parameterType = sb.substring(0,sb.length()-1);
            log.info("\n【应用注册：方法信息】 \nsystemId:{} \ninterfaceId:{} \nmethodId:{} \nmethodName:{} \nparameterType:{} \nuri:{} \nhttpCommandType:{} \nauth:{}",
                    properties.getSystemId(),
                    bean.getClass().getName(),
                    method.getName(),
                    apiProducerMethod.methodName(),
                    parameterType,
                    apiProducerMethod.uri(),
                    apiProducerMethod.httpCommandType(),
                    apiProducerMethod.auth());
            gatewayCenterService.doRegisterApplicationInterfaceMethod(properties.getAddress(),
                    properties.getSystemId(),
                    interfaceId,
                    method.getName(),
                    apiProducerMethod.methodName(),
                    parameterType,
                    apiProducerMethod.uri(),
                    apiProducerMethod.httpCommandType(),
                    apiProducerMethod.auth());
        }

        return bean;
    }
}
