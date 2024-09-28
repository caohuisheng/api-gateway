package cn.bugstack.gateway.interfaces;

import cn.bugstack.gateway.application.ILoadBalanceService;
import cn.bugstack.gateway.domain.loadbalance.model.aggregate.NginxConfig;
import cn.bugstack.gateway.domain.loadbalance.model.vo.LocationVO;
import cn.bugstack.gateway.domain.loadbalance.model.vo.UpstreamVO;
import cn.bugstack.gateway.domain.loadbalance.service.LoadBalanceService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-09-28
 */
@RestController
@RequestMapping("/wg/admin/load")
public class LoadBalanceManageController {

    private Logger log = LoggerFactory.getLogger(LoadBalanceManageController.class);

    @Resource
    private ILoadBalanceService loadBalanceServicel;

    @RequestMapping(value = "/updateNginxConfig", produces = "application/json;charset=utf-8")
    public void updateNginxConfig(){
        List<UpstreamVO> upstreamList = new ArrayList<>();
        upstreamList.add(new UpstreamVO("api01", "least_conn", Arrays.asList("127.0.0.1:8001","127.0.0.1:8002")));
        upstreamList.add(new UpstreamVO("api02","least_conn",Arrays.asList("127.0.0.1:8003")));
        List<LocationVO> locationList = new ArrayList<>();
        locationList.add(new LocationVO("/api01/","http://api01"));
        locationList.add(new LocationVO("/api02/","http://api02"));
        LoadBalanceService loadBalanceService = new LoadBalanceService();
        NginxConfig nginxConfig = new NginxConfig(upstreamList, locationList);

        try {
            log.info("刷新Nginx配置文件开始 nginxConfig:{}", JSON.toJSONString(nginxConfig));
            loadBalanceService.updateNginxConfig(nginxConfig);
            log.info("刷新Nginx配置文件完成");
        } catch (Exception e) {
            log.error("刷新Nginx配置文件失败",e);
        }
    }

}
