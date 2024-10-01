package cn.bugstack.gateway.domain.loadbalance.service;

import cn.bugstack.gateway.application.ILoadBalanceService;
import cn.bugstack.gateway.domain.loadbalance.model.aggregate.NginxConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: chs
 * Description: 抽象负载均衡服务
 * CreateTime: 2024-09-28
 */
public abstract class AbstractLoadBalanceService implements ILoadBalanceService {

    private Logger log = LoggerFactory.getLogger(AbstractLoadBalanceService.class);

    @Override
    public void updateNginxConfig(NginxConfig nginxConfig) {
        //创建Nginx配置文件
        createNginxConfigFile(nginxConfig);
        //刷新Nginx配置文件
        refreshNginxConfig();
    }

    protected abstract void createNginxConfigFile(NginxConfig nginxConfig);

    protected abstract void refreshNginxConfig();
}
