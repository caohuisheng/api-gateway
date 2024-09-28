package cn.bugstack.gateway.domain.loadbalance.model.aggregate;

import cn.bugstack.gateway.domain.loadbalance.model.vo.LocationVO;
import cn.bugstack.gateway.domain.loadbalance.model.vo.UpstreamVO;

import java.util.List;

/**
 * Author: chs
 * Description: Nginx配置
 * CreateTime: 2024-09-28
 */
public class NginxConfig {

    private String applicationName;
    private String nginxName;
    private String localNginxConfigPath;
    private String remoteNginxConfigPath;
    //负载均衡列表
    private List<UpstreamVO> upstreamList;
    //反向代理列表
    private List<LocationVO> locationList;

    public NginxConfig(List<UpstreamVO> upstreamList, List<LocationVO> locationList) {
        this.applicationName = "api-gateway-center";
        this.nginxName = "Nginx";
        this.localNginxConfigPath = "api-gateway-center-07/src/main/resources/config/nginx_template.conf";
        this.remoteNginxConfigPath = "D:/software/nginx/nginx-1.18.0/conf/nginx.conf";
        this.upstreamList = upstreamList;
        this.locationList = locationList;
    }

    public String getLocalNginxConfigPath() {
        return localNginxConfigPath;
    }

    public void setLocalNginxConfigPath(String localNginxConfigPath) {
        this.localNginxConfigPath = localNginxConfigPath;
    }

    public String getRemoteNginxConfigPath() {
        return remoteNginxConfigPath;
    }

    public void setRemoteNginxConfigPath(String remoteNginxConfigPath) {
        this.remoteNginxConfigPath = remoteNginxConfigPath;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getNginxName() {
        return nginxName;
    }

    public void setNginxName(String nginxName) {
        this.nginxName = nginxName;
    }


    public List<UpstreamVO> getUpstreamList() {
        return upstreamList;
    }

    public void setUpstreamList(List<UpstreamVO> upstreamList) {
        this.upstreamList = upstreamList;
    }

    public List<LocationVO> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<LocationVO> locationList) {
        this.locationList = locationList;
    }
}
