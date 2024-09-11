package cn.bugstack.gateway.test;

import cn.bugstack.gateway.application.IConfigManageService;
import cn.bugstack.gateway.domain.manager.model.vo.GatewayServerVO;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void test_queryGatewayServerList(){
        System.out.println("hello,world");
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

}
