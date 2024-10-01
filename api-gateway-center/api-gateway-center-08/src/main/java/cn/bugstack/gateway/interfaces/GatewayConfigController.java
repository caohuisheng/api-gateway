package cn.bugstack.gateway.interfaces;

import cn.bugstack.gateway.application.IConfigManageService;
import cn.bugstack.gateway.application.ILoadBalanceService;
import cn.bugstack.gateway.application.IMessageService;
import cn.bugstack.gateway.domain.loadbalance.model.aggregate.NginxConfig;
import cn.bugstack.gateway.domain.loadbalance.model.vo.LocationVO;
import cn.bugstack.gateway.domain.loadbalance.model.vo.UpstreamVO;
import cn.bugstack.gateway.domain.manager.model.aggregate.ApplicationSystemRichInfo;
import cn.bugstack.gateway.domain.manager.model.vo.GatewayServerDetailVO;
import cn.bugstack.gateway.domain.manager.model.vo.GatewayServerVO;
import cn.bugstack.gateway.infrastucture.common.ResponseCode;
import cn.bugstack.gateway.infrastucture.common.Result;
import cn.bugstack.gateway.infrastucture.po.GatewayServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author: chs
 * Description: 网关配置管理：服务分组、网关注册、服务发现
 * CreateTime: 2024-09-11
 */
@CrossOrigin
@RestController
@RequestMapping("/wg/admin/config")
public class GatewayConfigController {

    private Logger log = LoggerFactory.getLogger(GatewayConfigController.class);

    @Resource
    private IConfigManageService configManageService;
    @Resource
    private IMessageService messageService;
    @Resource
    private ILoadBalanceService loadBalanceService;

    @GetMapping(value = "queryServerConfig", produces = "application/json;charset=utf-8")
    public Result<List<GatewayServerVO>> queryServerConfig() {
        log.info("查询网关服务配置项信息");
        try {
            List<GatewayServerVO> gatewayServerVOS = configManageService.queryGatewayServerList();
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), gatewayServerVOS);
        } catch (Exception e) {
            log.error("查询网关服务配置项信息异常",e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
        }
    }

    /**
     * 注册网关服务节点
     * @param groupId 分组id
     * @param gatewayId 网关id
     * @param gatewayName 网关名称
     * @param gatewayAddress 网关地址
     * @return
     */
    @PostMapping(value = "registerGateway")
    public Result<Boolean> registerGateway(@RequestParam String groupId, @RequestParam String gatewayId, @RequestParam String gatewayName,
                                           @RequestParam String gatewayAddress){
        try {
            log.info("注册网关服务节点 gatewayId:{} gatewayName:{} gatewayAddress:{}", gatewayId, gatewayName, gatewayAddress);
            //1.注册/更新网关算力信息
            boolean status = configManageService.registerGatewayServerNode(groupId, gatewayId, gatewayName, gatewayAddress);
            //2.获取最新网关算力数据
            List<GatewayServerDetailVO> gatewayServerDetailVOS = configManageService.queryGatewayServerDetailList();
            //3.组装Nginx网关刷新配置信息
            Map<String, List<GatewayServerDetailVO>> gatewayServerDetailMap = gatewayServerDetailVOS.stream().collect(Collectors.groupingBy(GatewayServerDetailVO::getGroupId));
            Set<String> groupIdList = gatewayServerDetailMap.keySet();
            //3.1 Location信息
            List<LocationVO> locationList = new ArrayList<>();
            for(String name:groupIdList){
                locationList.add(new LocationVO("/" + name + "/", "http://" + name));
            }
            //3.2Upstream信息
            List<UpstreamVO> upstreamList = new ArrayList<>();
            for(String name:groupIdList){
                List<String> servers = gatewayServerDetailMap.get(name).stream().map(GatewayServerDetailVO::getGatewayAddress).collect(Collectors.toList());
                upstreamList.add(new UpstreamVO(name, "least_conn", servers));
            }
            //4.刷新Nginx配置
            loadBalanceService.updateNginxConfig(new NginxConfig(upstreamList, locationList));
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), status);
        } catch(Exception e) {
            log.error("注册网关服务节点异常",e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }
    }

    /**
     * todo: 开发完后续应用注册后，开发本接口
     * @param groupId
     * @param gatewayId
     */
    public void distributionGatewayServerNode(@RequestParam String groupId, @RequestParam String gatewayId){

    }

    @PostMapping(value = "queryApplicationSystemRichInfo", produces = "application/json;charset=utf-8")
    public Result<ApplicationSystemRichInfo> queryApplicationSystemRichInfo(@RequestParam String gatewayId,@RequestParam String systemId){
        try {
            log.info("查询分配到网关下的待注册系统信息 gatewayId:{} systemId:{}",gatewayId, systemId);
            ApplicationSystemRichInfo applicationSystemRichInfo = configManageService.queryApplicationSystemRichInfo(gatewayId,systemId);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), applicationSystemRichInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
        }
    }

    @PostMapping(value = "queryRedisConfig",produces = "application/json;charset=utf-8")
    public Result<Map<String, String>> queryRedisConfig(){
        try {
            log.info("查询配置中心Redis配置信息");
            Map<String, String> redisConfig = messageService.queryRedisConfig();
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), redisConfig);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
        }
    }



}
