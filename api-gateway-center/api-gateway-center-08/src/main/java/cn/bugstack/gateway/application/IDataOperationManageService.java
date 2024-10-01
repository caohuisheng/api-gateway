package cn.bugstack.gateway.application;

import cn.bugstack.gateway.domain.operation.model.vo.*;
import cn.bugstack.gateway.infrastucture.common.OperationRequest;
import cn.bugstack.gateway.infrastucture.common.OperationResult;

/**
 * Author: chs
 * Description: 网关运营数据管理服务接口
 * CreateTime: 2024-09-25
 */
public interface IDataOperationManageService {

    OperationResult<GatewayServerDataVO> queryGatewayServer(OperationRequest<String> request);
    OperationResult<GatewayServerDetailDataVO> queryGatewayServerDetail(OperationRequest<GatewayServerDetailDataVO> request);
    OperationResult<GatewayDistributionDataVO> queryGatewayDistribution(OperationRequest<GatewayDistributionDataVO> request);
    OperationResult<ApplicationSystemDataVO> queryApplicationSystem(OperationRequest<ApplicationSystemDataVO> request);
    OperationResult<ApplicationInterfaceDataVO> queryApplicationInterface(OperationRequest<ApplicationInterfaceDataVO> request);
    OperationResult<ApplicationInterfaceMethodDataVO> queryApplicationInterfaceMethod(OperationRequest<ApplicationInterfaceMethodDataVO> request);

}
