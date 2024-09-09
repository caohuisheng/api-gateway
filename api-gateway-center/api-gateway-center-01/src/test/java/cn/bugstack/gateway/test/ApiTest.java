package cn.bugstack.gateway.test;

import cn.bugstack.gateway.application.IApiService;
import cn.bugstack.gateway.domain.model.ApiData;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
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
    private IApiService apiService;

    @Test
    public void test_apiService(){
        List<ApiData> apiData = apiService.queryHttpStatementList();
        log.info("测试结果：{}", JSON.toJSONString(apiData));
        JSON.toJSONString(apiData);
    }

}
