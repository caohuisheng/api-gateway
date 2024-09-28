package cn.bugstack.gateway.application;

import cn.bugstack.gateway.domain.loadbalance.model.aggregate.NginxConfig;

/**
 * Author: chs
 * Description: 负载均衡服务接口
 * CreateTime: 2024-09-28
 */
public interface ILoadBalanceService {

    void updateNginxConfig(NginxConfig nginxConfig);

}
