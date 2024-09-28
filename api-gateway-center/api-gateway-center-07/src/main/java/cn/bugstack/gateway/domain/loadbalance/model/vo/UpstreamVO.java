package cn.bugstack.gateway.domain.loadbalance.model.vo;

import java.util.List;

/**
 * Author: chs
 * Description: 负载均衡服务器列表
 * CreateTime: 2024-09-28
 */
public class UpstreamVO {

    //名称
    private String name;
    //负载策略
    private String strategy;
    //服务器列表
    private List<String> servers;

    public UpstreamVO(String name, String strategy, List<String> servers) {
        this.name = name;
        this.strategy = strategy;
        this.servers = servers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public List<String> getServers() {
        return servers;
    }

    public void setServers(List<String> servers) {
        this.servers = servers;
    }
}
