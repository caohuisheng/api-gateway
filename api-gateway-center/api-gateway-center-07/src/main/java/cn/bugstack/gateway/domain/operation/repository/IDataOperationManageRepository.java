package cn.bugstack.gateway.domain.operation.repository;

import cn.bugstack.gateway.domain.operation.model.vo.*;
import cn.bugstack.gateway.infrastucture.common.OperationRequest;

import java.util.List;

/**
 * Author: chs
 * Description: 网关运营数据管理仓储接口
 * CreateTime: 2024-09-25
 */
public interface IDataOperationManageRepository {

    List<GatewayServerDataVO> queryGatewayServerListByPage(OperationRequest<String> request);
    int queryGatewayServerListCount(OperationRequest<String> request);

    List<GatewayServerDetailDataVO> queryGatewayServerDetailListByPage(OperationRequest<GatewayServerDetailDataVO> request);
    int queryGatewayServerDetailListCount(OperationRequest<GatewayServerDetailDataVO> request);

    List<GatewayDistributionDataVO> queryGatewayDistributionListByPage(OperationRequest<GatewayDistributionDataVO> request);
    int queryGatewayDistributionListCount(OperationRequest<GatewayDistributionDataVO> request);

    List<ApplicationSystemDataVO> queryApplicationSystemListByPage(OperationRequest<ApplicationSystemDataVO> request);
    int queryApplicationSystemListCount(OperationRequest<ApplicationSystemDataVO> request);

    List<ApplicationInterfaceDataVO> queryApplicationInterfaceListByPage(OperationRequest<ApplicationInterfaceDataVO> request);
    int queryApplicationInterfaceListCount(OperationRequest<ApplicationInterfaceDataVO> request);

    List<ApplicationInterfaceMethodDataVO> queryApplicationInterfaceMethodListByPage(OperationRequest<ApplicationInterfaceMethodDataVO> request);
    int queryApplicationInterfaceMethodListCount(OperationRequest<ApplicationInterfaceMethodDataVO> request);

}
