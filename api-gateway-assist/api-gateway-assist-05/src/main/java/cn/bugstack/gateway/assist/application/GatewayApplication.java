package cn.bugstack.gateway.assist.application;

import cn.bugstack.gateway.assist.config.GatewayServiceProperties;
import cn.bugstack.gateway.assist.domain.model.aggregate.ApplicationSystemRichInfo;
import cn.bugstack.gateway.assist.domain.model.vo.ApplicationInterfaceMethodVO;
import cn.bugstack.gateway.assist.domain.model.vo.ApplicationInterfaceVO;
import cn.bugstack.gateway.assist.domain.model.vo.ApplicationSystemVO;
import cn.bugstack.gateway.assist.domain.service.GatewayCenterService;
import cn.bugstack.gateway.core.mapping.HttpCommandType;
import cn.bugstack.gateway.core.mapping.HttpStatement;
import cn.bugstack.gateway.core.session.Configuration;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import java.util.List;

/**
 * Author: chs
 * Description: 网关应用：与Spring链接，调用网关注册和接口拉取
 * CreateTime: 2024-09-13
 */
public class GatewayApplication implements ApplicationContextAware,ApplicationListener<ContextClosedEvent> {

    private Logger log = LoggerFactory.getLogger(GatewayApplication.class);

    private GatewayServiceProperties properties;
    private GatewayCenterService gatewayCenterService;
    private Configuration configuration;
    private Channel gatewaySocketServerChannel;

    public GatewayApplication(GatewayServiceProperties properties,GatewayCenterService gatewayCenterService, Configuration configuration,Channel gatewaySocketServerChannel){
        this.properties = properties;
        this.gatewayCenterService = gatewayCenterService;
        this.configuration = configuration;
        this.gatewaySocketServerChannel = gatewaySocketServerChannel;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try {
            //1.注册网关服务
            gatewayCenterService.doRegister(properties.getAddress(),
                    properties.getGroupId(),
                    properties.getGatewayId(),
                    properties.getGatewayName(),
                    properties.getGatewayAddress());

            addMappers(null);
        } catch (Exception e) {
            log.error("网关服务启动失败，停止服务", e);
        }
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        try {
            if(gatewaySocketServerChannel.isActive()){
                log.info("应用容器关闭，Api网关服务关闭，localAddress:{}",gatewaySocketServerChannel.localAddress());
                gatewaySocketServerChannel.close();
            }
        } catch (Exception e) {
            log.error("应用容器关闭，Api网关服务关闭失败", e);
        }
    }

    public void receiveMessage(Object message){
        log.info("【事件通知】接收注册中心推送消息 message:{}",message);
        String msg = message.toString();
        addMappers(msg.substring(1,msg.length()-1));
    }

    private void addMappers(String systemId){
        //2.拉取网关配置
        ApplicationSystemRichInfo applicationSystemRichInfo = gatewayCenterService.pullApplicationSystemRichInfo(properties.getAddress(), properties.getGatewayId(),systemId);
        List<ApplicationSystemVO> systemList = applicationSystemRichInfo.getSystemList();
        for(ApplicationSystemVO system:systemList){
            List<ApplicationInterfaceVO> interfaceList = system.getInterfaceList();
            for (ApplicationInterfaceVO interfaceVO : interfaceList) {
                //创建配置信息加载注册
                configuration.registryConfig(system.getSystemId(),system.getSystemRegistry(),interfaceVO.getInterfaceId(),interfaceVO.getInterfaceVersion());
                List<ApplicationInterfaceMethodVO> methodList = interfaceVO.getMethodList();
                //注册系统服务接口信息
                for(ApplicationInterfaceMethodVO method:methodList){
                    HttpStatement httpStatement = new HttpStatement(system.getSystemId(),interfaceVO.getInterfaceId(),method.getMethodId(),
                            method.getUri(), method.getParameterType(), HttpCommandType.valueOf(method.getHttpCommandType()),method.isAuth());
                    configuration.addMapper(httpStatement);
                    log.info("网关服务注册映射 systemId：{} interfaceId:{} methodId:{}",system.getSystemId(),interfaceVO.getInterfaceId(),method.getMethodId());
                }
            }
        }
    }

}
