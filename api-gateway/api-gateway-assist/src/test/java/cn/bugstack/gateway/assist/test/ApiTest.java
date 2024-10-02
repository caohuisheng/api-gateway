package cn.bugstack.gateway.assist.test;

import cn.bugstack.gateway.assist.common.Result;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-09-13
 */
public class ApiTest {

    @Test
    public void test_register_gateway(){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("groupId","10001");
        paramMap.put("gatewayId","api-gateway-g4");
        paramMap.put("gatewayName","电商配送网关");
        paramMap.put("gatewayAddress","127.0.0.1");

        String resultStr = HttpUtil.post("http://localhost:8001/wg/admin/config/registerGateway", paramMap, 350);
        System.out.println(resultStr);

        Result result = JSON.parseObject(resultStr, Result.class);
        System.out.println(result.getCode());
    }

}
