package cn.bugstack.gateway.interfaces;

import cn.bugstack.gateway.application.IDataOperationManageService;
import cn.bugstack.gateway.domain.operation.model.vo.*;
import cn.bugstack.gateway.infrastucture.common.OperationRequest;
import cn.bugstack.gateway.infrastucture.common.OperationResult;
import cn.bugstack.gateway.infrastucture.dao.ApplicationInterfaceDao;
import cn.bugstack.gateway.infrastucture.po.GatewayDistribution;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Author: chs
 * Description: 网关运营数据管理
 * CreateTime: 2024-09-25
 */
@CrossOrigin
@RestController
@RequestMapping("/wg/admin/data")
public class DataOperationManage {

    private Logger log = LoggerFactory.getLogger(DataOperationManage.class);

    @Resource
    private IDataOperationManageService operationManageService;

    @GetMapping(value = "queryGatewayServer", produces = "application/json;charset=utf-8")
    public OperationResult<GatewayServerDataVO> queryGatewayServer(@RequestParam String groupId,
                                                                   @RequestParam String pageIndex,
                                                                   @RequestParam String pageSize){
        try {
            log.info("查询网关服务数据开始 groupId:{}",groupId);
            OperationRequest<String> req = new OperationRequest<String>(pageIndex, pageSize);
            req.setData(groupId);
            OperationResult<GatewayServerDataVO> operationResult = operationManageService.queryGatewayServer(req);
            log.info("查询网关服务数据结束 operationResult:{}", JSON.toJSONString(operationResult));
            return operationResult;
        } catch (Exception e) {
            log.error("查询网关服务数据异常 groupId:{}", groupId, e);
            return new OperationResult<GatewayServerDataVO>(null, 0);
        }
    }

    @GetMapping(value = "queryGatewayServerDetail", produces = "application/json;charset=utf-8")
    public OperationResult<GatewayServerDetailDataVO> queryGatewayServerDetail(@RequestParam String groupId,
                                                                         @RequestParam String gatewayId,
                                                                         @RequestParam String pageIndex,
                                                                         @RequestParam String pageSize){
        try {
            log.info("查询网关服务详情数据开始 groupId:{} gatewayId:{}",groupId,gatewayId);
            OperationRequest<GatewayServerDetailDataVO> req = new OperationRequest<>(pageIndex, pageSize);
            GatewayServerDetailDataVO vo = new GatewayServerDetailDataVO();
            vo.setGroupId(groupId);
            vo.setGatewayId(gatewayId);
            req.setData(vo);

            OperationResult<GatewayServerDetailDataVO> operationResult = operationManageService.queryGatewayServerDetail(req);
            log.info("查询网关服务详情数据结束 operationResult:{}", JSON.toJSONString(operationResult));
            return operationResult;
        } catch (Exception e) {
            log.error("查询网关服务数据异常 groupId:{}", groupId, e);
            return new OperationResult<GatewayServerDetailDataVO>(null, 0);
        }
    }

    @GetMapping(value = "queryGatewayDistribution", produces = "application/json;charset=utf-8")
    public OperationResult<GatewayDistributionDataVO> queryGatewayDistribution(@RequestParam String groupId,
                                                                         @RequestParam String gatewayId,
                                                                         @RequestParam String pageIndex,
                                                                         @RequestParam String pageSize){
        try {
            log.info("查询网关分配数据开始 groupId:{} gatewayId:{}", groupId, gatewayId);
            OperationRequest<GatewayDistributionDataVO> req = new OperationRequest<>(pageIndex, pageSize);
            GatewayDistributionDataVO vo = new GatewayDistributionDataVO();
            vo.setGroupId(groupId);
            vo.setGatewayId(gatewayId);
            req.setData(vo);

            OperationResult<GatewayDistributionDataVO> operationResult = operationManageService.queryGatewayDistribution(req);
            log.info("查询网关分配数据结束 operationResult:{}", operationResult);
            return operationResult;
        } catch (Exception e) {
            log.error("查询网关分配数据异常 groupId:{} gatewayId:{}", groupId, gatewayId, e);
            return new OperationResult<>(null, 0);
        }
    }

    @GetMapping(value = "queryApplicationSystem", produces = "application/json;charset=utf-8")
    public OperationResult<ApplicationSystemDataVO> queryApplicationSystem(@RequestParam String systemId,
                                                                           @RequestParam String systemName,
                                                                           @RequestParam String pageIndex,
                                                                           @RequestParam String pageSize){
        try {
            log.info("查询应用系统信息开始 systemId:{} systemName:{}", systemId, systemName);
            OperationRequest<ApplicationSystemDataVO> req = new OperationRequest<>(pageIndex, pageSize);
            ApplicationSystemDataVO vo = new ApplicationSystemDataVO();
            vo.setSystemId(systemId);
            vo.setSystemName(systemName);
            req.setData(vo);

            OperationResult<ApplicationSystemDataVO> operationResult = operationManageService.queryApplicationSystem(req);
            log.info("查询应用系统信息结束 operationResult:{}", JSON.toJSONString(operationResult));
            return operationResult;
        } catch (Exception e) {
            log.error("查询应用系统信息异常 systemId:{} systemName:{}", systemId, systemName, e);
            return new OperationResult<>(null, 0);
        }
    }

    @GetMapping(value = "queryApplicationInterface", produces = "application/json;charset=utf-8")
    public OperationResult<ApplicationInterfaceDataVO> queryApplicationInterface(@RequestParam String systemId,
                                                                                 @RequestParam String interfaceId,
                                                                                 @RequestParam String pageIndex,
                                                                                 @RequestParam String pageSize){
        try {
            log.info("查询应用接口信息开始 systemId:{}, interfaceId:{}", systemId, interfaceId);
            OperationRequest<ApplicationInterfaceDataVO> req = new OperationRequest<>(pageIndex, pageSize);
            ApplicationInterfaceDataVO vo = new ApplicationInterfaceDataVO();
            vo.setSystemId(systemId);
            vo.setInterfaceId(interfaceId);
            req.setData(vo);

            OperationResult<ApplicationInterfaceDataVO> operationResult = operationManageService.queryApplicationInterface(req);
            log.info("查询应用接口信息结束 operationResult:{}", JSON.toJSONString(operationResult));
            return operationResult;
        } catch (Exception e) {
            log.error("查询应用接口信息异常 systemId:{}, interfaceId:{}", systemId, interfaceId);
            return new OperationResult<>(null, 0);
        }
    }

    @GetMapping(value = "queryApplicationInterfaceMethod", produces = "application/json;charset=utf-8")
    public OperationResult<ApplicationInterfaceMethodDataVO> queryApplicationInterfaceMethod(@RequestParam String systemId,
                                                                                             @RequestParam String methodId,
                                                                                             @RequestParam String pageIndex,
                                                                                             @RequestParam String pageSize){
        try {
            log.info("查询应用接口方法信息开始 systemId:{}, methodId:{}", systemId, methodId);
            OperationRequest<ApplicationInterfaceMethodDataVO> req = new OperationRequest<>(pageIndex, pageSize);
            ApplicationInterfaceMethodDataVO vo = new ApplicationInterfaceMethodDataVO();
            vo.setSystemId(systemId);
            vo.setMethodId(methodId);
            req.setData(vo);

            OperationResult<ApplicationInterfaceMethodDataVO> operationResult = operationManageService.queryApplicationInterfaceMethod(req);
            log.info("查询应用接口方法信息结束 operationResult:{}", JSON.toJSONString(operationResult));
            return operationResult;
        } catch (Exception e) {
            log.error("查询应用接口方法信息异常 systemId:{}, methodId:{}", systemId, methodId, e);
            return new OperationResult<>(null, 0);
        }
    }

}
