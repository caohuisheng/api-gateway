package cn.bugstack.gateway.domain.loadbalance.model.vo;

/**
 * Author: chs
 * Description: 反向代理
 * CreateTime: 2024-09-28
 */
public class LocationVO {
    // 名称
    private String name;
    // 指向地址
    private String proxyPass;

    public LocationVO(String name, String proxyPass) {
        this.name = name;
        this.proxyPass = proxyPass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProxyPass() {
        return proxyPass;
    }

    public void setProxyPass(String proxyPass) {
        this.proxyPass = proxyPass;
    }
}
