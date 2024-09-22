package cn.bugstack.gateway.assist.domain.service;

import cn.bugstack.gateway.assist.GatewayException;
import cn.bugstack.gateway.assist.common.Result;
import cn.bugstack.gateway.assist.domain.model.aggregate.ApplicationSystemRichInfo;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: chs
 * Description: 网关注册服务
 * CreateTime: 2024-09-15
 */
public class GatewayCenterService {

    private Logger log = LoggerFactory.getLogger(GatewayCenterService.class);



    public void doRegister(String address, String groupId, String gatewayId, String gatewayName, String gatewayAddress){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("groupId", groupId);
        paramMap.put("gatewayId",gatewayId);
        paramMap.put("gatewayName",gatewayName);
        paramMap.put("gatewayAddress",gatewayAddress);
        String resultStr = null;
        try {
            resultStr = HttpUtil.post(address + "/wg/admin/config/registerGateway", paramMap, 5000);
        } catch (Exception e) {
            log.error("网关服务注册异常，链接资源不可用：{}", address + "/wg/admin/config/registerGateway");
            throw e;
        }
        Result result = JSON.parseObject(resultStr, Result.class);
        log.info("向网关中心注册网关算例服务 gatewayId:{} gatewayName:{} gatewayAddress:{} 注册结果：{}", gatewayId, gatewayName, gatewayAddress, resultStr);
        if(!"0000".equals(result.getCode())){
            throw new GatewayException("网关服务注册异常，gatewayId:" + gatewayId + " gatewayAddress:" + gatewayAddress);
        }
    }

    public ApplicationSystemRichInfo pullApplicationSystemRichInfo(String address, String gatewayId, String systemId){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gatewayId", gatewayId);
        paramMap.put("systemId","");
        String resultStr = HttpUtil.post(address + "/wg/admin/config/queryApplicationSystemRichInfo", paramMap, 5000);
        Result<ApplicationSystemRichInfo> result = JSON.parseObject(resultStr, new TypeReference<Result<ApplicationSystemRichInfo>>(){});
        log.info("从网关中心拉取应用服务和接口的配置信息到本地完成注册，gatewayId:{}",gatewayId);
        if(!"0000".equals(result.getCode())){
            throw new GatewayException("从网关中心拉取应用服务和接口的配置信息到本地完成注册异常 gatewayId:" + gatewayId);
        }
        return result.getData();
    }

    public Map<String,String> queryRedisConfig(String address){
        String resultStr = HttpUtil.post(address + "/wg/admin/config/queryRedisConfig", "", 2500);
        Result<Map<String, String>> result = JSON.parseObject(resultStr, new TypeReference<Result<Map<String, String>>>() {
        });
        log.info("从网关中心查询redis配置信息成功, result:{}",resultStr);
        if(!"0000".equals(result.getCode())){
            throw new GatewayException("从网关中心查询redis配置信息异常");
        }
        return result.getData();
    }
}
