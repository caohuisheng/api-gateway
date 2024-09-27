package cn.bugstack.gateway.test;

import cn.bugstack.gateway.application.IConfigManageService;
import cn.bugstack.gateway.application.IRegisterManageService;
import cn.bugstack.gateway.domain.manager.model.aggregate.ApplicationSystemRichInfo;
import cn.bugstack.gateway.domain.manager.model.vo.GatewayServerVO;
import cn.bugstack.gateway.domain.register.model.vo.ApplicationInterfaceMethodVO;
import cn.bugstack.gateway.domain.register.model.vo.ApplicationInterfaceVO;
import cn.bugstack.gateway.domain.register.model.vo.ApplicationSystemVO;
import com.alibaba.fastjson.JSON;
import io.netty.handler.stream.ChunkedInput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-09-09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    private Logger log = LoggerFactory.getLogger(ApiTest.class);

    @Resource
    private IConfigManageService configManageService;
    @Resource
    private IRegisterManageService registerManageService;

    @Test
    public void test_queryGatewayServerList(){
        List<GatewayServerVO> gatewayServerVOS = configManageService.queryGatewayServerList();
        log.info("测试结果：{}", JSON.toJSONString(gatewayServerVOS));
    }

    @Test
    public void test_registerGatewayServerNode(){
        boolean flag1 = configManageService.registerGatewayServerNode("10001","api-gateway-g1","电商支付网关","127.0.0.196");
        boolean flag2 = configManageService.registerGatewayServerNode("10001","api-gateway-g2","电商支付网关","127.0.0.197");
        boolean flag3 = configManageService.registerGatewayServerNode("10001","api-gateway-g3","电商配送网关","127.0.0.198");
        System.out.println(flag1 + " " + flag2 + " " + flag3);
    }

    @Test
    public void test_registerApplication(){
        ApplicationSystemVO applicationSystemVO = new ApplicationSystemVO();
        applicationSystemVO.setSystemId("api-gateway-test");
        applicationSystemVO.setSystemName("网关测试系统");
        applicationSystemVO.setSystemType("RPC");
        applicationSystemVO.setSystemRegistry("127.0.0.1");
        registerManageService.registerApplication(applicationSystemVO);
    }

    @Test
    public void test_registerApplicationInterface(){
        ApplicationInterfaceVO applicationInterfaceVO = new ApplicationInterfaceVO();
        applicationInterfaceVO.setSystemId("api-gateway-test");
        applicationInterfaceVO.setInterfaceId("cn.bugstack.gateway.rpc.IActivityBooth");
        applicationInterfaceVO.setInterfaceName("活动平台");
        applicationInterfaceVO.setInterfaceVersion("v1.0.0");
        registerManageService.registerApplicationInterface(applicationInterfaceVO);
    }

    @Test
    public void test_registerApplicationInterfaceMethod(){
        ApplicationInterfaceMethodVO applicationInterfaceMethodVO = new ApplicationInterfaceMethodVO();
        applicationInterfaceMethodVO.setSystemId("api-gateway-test");
        applicationInterfaceMethodVO.setInterfaceId("cn.bugstack.gateway.rpc.IActivityBooth");
        applicationInterfaceMethodVO.setMethodId("sayHi");
        applicationInterfaceMethodVO.setMethodName("测试方法");
        applicationInterfaceMethodVO.setParameterType("java.lang.String");
        applicationInterfaceMethodVO.setUri("/wg/activity/sayHi");
        applicationInterfaceMethodVO.setHttpCommandType("GET");
        applicationInterfaceMethodVO.setAuth(0);
        registerManageService.registerApplicationInterfaceMethod(applicationInterfaceMethodVO);

        applicationInterfaceMethodVO.setSystemId("api-gateway-test");
        applicationInterfaceMethodVO.setInterfaceId("cn.bugstack.gateway.rpc.IActivityBooth");
        applicationInterfaceMethodVO.setMethodId("sayHi");
        applicationInterfaceMethodVO.setMethodName("测试方法");
        applicationInterfaceMethodVO.setParameterType("java.lang.String");
        applicationInterfaceMethodVO.setUri("/wg/activity/sayHi");
        applicationInterfaceMethodVO.setHttpCommandType("GET");
        applicationInterfaceMethodVO.setAuth(0);
        registerManageService.registerApplicationInterfaceMethod(applicationInterfaceMethodVO);
    }

    @Test
    public void test_queryApplicationSystemRichInfo(){
        ApplicationSystemRichInfo applicationSystemRichInfo = configManageService.queryApplicationSystemRichInfo("api-gateway-g1",null);
        log.info("测试结果：{}",JSON.toJSONString(applicationSystemRichInfo));
    }

}
