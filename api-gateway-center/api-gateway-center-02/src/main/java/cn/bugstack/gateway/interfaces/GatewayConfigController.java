package cn.bugstack.gateway.interfaces;

import cn.bugstack.gateway.application.IConfigManageService;
import cn.bugstack.gateway.domain.manager.model.vo.GatewayServerVO;
import cn.bugstack.gateway.infrastucture.common.ResponseCode;
import cn.bugstack.gateway.infrastucture.common.Result;
import cn.bugstack.gateway.infrastucture.po.GatewayServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: chs
 * Description: 网关配置管理：服务分组、网关注册、服务发现
 * CreateTime: 2024-09-11
 */
@RestController
@RequestMapping("/wg/admin/config")
public class GatewayConfigController {

    private Logger log = LoggerFactory.getLogger(GatewayConfigController.class);

    @Resource
    private IConfigManageService configManageService;

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
            boolean status = configManageService.registerGatewayServerNode(groupId, gatewayId, gatewayName, gatewayAddress);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), status);
        } catch(Exception e) {
            log.info("注册网关服务节点异常",e);
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

}
