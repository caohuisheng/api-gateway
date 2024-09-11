package cn.bugstack.gateway.interfaces;

import cn.bugstack.gateway.application.IApiService;
import cn.bugstack.gateway.domain.manager.model.ApiData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: chs
 * Description: 网关接口服务
 * CreateTime: 2024-09-09
 */
@RestController
@RequestMapping("/api")
public class ApiGatewayController {

    private Logger log = LoggerFactory.getLogger(ApiGatewayController.class);

    @Resource
    private IApiService apiService;

    @GetMapping(value = "list", produces = "application/json;charset=utf-8")
    public List<ApiData> getAnswerMap(){
        return apiService.queryHttpStatementList();
    }

}
