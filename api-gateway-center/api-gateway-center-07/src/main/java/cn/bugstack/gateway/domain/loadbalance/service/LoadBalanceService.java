package cn.bugstack.gateway.domain.loadbalance.service;

import cn.bugstack.gateway.domain.loadbalance.model.aggregate.NginxConfig;
import cn.bugstack.gateway.domain.loadbalance.model.vo.LocationVO;
import cn.bugstack.gateway.domain.loadbalance.model.vo.UpstreamVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-09-28
 */
@Service
public class LoadBalanceService extends AbstractLoadBalanceService{

    private Logger log = LoggerFactory.getLogger(LoadBalanceService.class);

    @Override
    protected void createNginxConfigFile(NginxConfig nginxConfig) {
        try {
            String configFileStr = buildNginxConfig(nginxConfig.getLocalNginxConfigPath(),nginxConfig.getUpstreamList(), nginxConfig.getLocationList());
            log.info("创建Nginx配置文件：\n{}",configFileStr);
            File remoteNginxConfigFile = new File(nginxConfig.getRemoteNginxConfigPath());
            //删除原配置文件，并创建新配置文件
            if(remoteNginxConfigFile.exists()){
                remoteNginxConfigFile.delete();
            }
            boolean success = remoteNginxConfigFile.createNewFile();
            log.info(success?"nginx.conf file create success.":"nginx.conf file create failed.");
            //将配置文件内容写入文件
            FileWriter fw = new FileWriter(remoteNginxConfigFile);
            fw.write(configFileStr);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建Nginx配置文件（字符串）
     * @param upstreamList 负载均衡配置列表
     * @param locationList 反向代理配置列表
     * @return
     */
    private String buildNginxConfig(String localNginxConfigFilePath,List<UpstreamVO> upstreamList, List<LocationVO> locationList){
        try {
            File confTemplateFile = new File(localNginxConfigFilePath);
            String confTemplateFileStr = new String(Files.readAllBytes(confTemplateFile.toPath()));
            //将负载均衡配置列表转为字符串
            StringBuilder upstreamConfStr = new StringBuilder();
            for(UpstreamVO upstreamVO:upstreamList){
                upstreamConfStr.append("\t").append("upstream").append(" ").append(upstreamVO.getName()).append("{\r\n");
                upstreamConfStr.append("\t\t").append(upstreamVO.getStrategy()).append(";\r\n");
                for(String server: upstreamVO.getServers()){
                    upstreamConfStr.append("\t\t").append("server").append(" ").append(server).append(";\r\n");
                }
                upstreamConfStr.append("\t}");
            }
            //将反向代理配置列表转为字符串
            StringBuilder locationConfStr = new StringBuilder();
            for(LocationVO locationVO:locationList){
                locationConfStr.append("\t\tlocation").append(" ").append(locationVO.getName()).append(" {\r\n");
                locationConfStr.append("\t\t\t").append("proxy_pass").append(" ").append(locationVO.getProxyPass()).append(";\r\n");
                locationConfStr.append("\t\t}");
            }
            //将占位符替换为实际的配置
            String confFileStr = confTemplateFileStr.replace("upstream_config_placeholder",upstreamConfStr.toString())
                    .replace("location_config_placeholder",locationConfStr.toString());
            return confFileStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void refreshNginxConfig() {
        try {
            String nginxPath = "D:\\software\\nginx\\nginx-1.18.0\\nginx.exe";
            ProcessBuilder pb = new ProcessBuilder(nginxPath,"-t");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            process.waitFor();
            //如果测试命令正常结束了，重新加载配置文件
            if(process.exitValue() == 0){
                ProcessBuilder pbReload = new ProcessBuilder(nginxPath, "-s", "reload");
                pbReload.redirectErrorStream(true);
                Process processReload = pbReload.start();
                processReload.waitFor();
                log.info("刷新Nginx配置成功");
            }
        } catch (IOException|InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<UpstreamVO> upstreamList = new ArrayList<>();
        upstreamList.add(new UpstreamVO("api01", "least_conn",Arrays.asList("127.0.0.1:8001","127.0.0.1:8002")));
        upstreamList.add(new UpstreamVO("api02","least_conn",Arrays.asList("127.0.0.1:8003")));
        List<LocationVO> locationList = new ArrayList<>();
        locationList.add(new LocationVO("/api01/","http://api01"));
        locationList.add(new LocationVO("/api02/","http://api02"));

        LoadBalanceService loadBalanceService = new LoadBalanceService();
        NginxConfig nginxConfig = new NginxConfig(upstreamList, locationList);
        loadBalanceService.createNginxConfigFile(nginxConfig);
        loadBalanceService.refreshNginxConfig();
    }
}
