package cn.bugstack.gateway.domain.operation.service;

import cn.bugstack.gateway.application.IDataOperationManageService;
import cn.bugstack.gateway.domain.operation.model.vo.*;
import cn.bugstack.gateway.domain.operation.repository.IDataOperationManageRepository;
import cn.bugstack.gateway.infrastucture.common.OperationRequest;
import cn.bugstack.gateway.infrastucture.common.OperationResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author: chs
 * Description: 网关运营数据管理服务
 * CreateTime: 2024-09-25
 */
@Service
public class DataOperationManageService implements IDataOperationManageService {

    @Resource
    private IDataOperationManageRepository repository;

    @Override
    public OperationResult<GatewayServerDataVO> queryGatewayServer(OperationRequest<String> request) {
        return new OperationResult<GatewayServerDataVO>(repository.queryGatewayServerListByPage(request),
                repository.queryGatewayServerListCount(request));
    }

    @Override
    public OperationResult<GatewayServerDetailDataVO> queryGatewayServerDetail(OperationRequest<GatewayServerDetailDataVO> request) {
        return new OperationResult<>(repository.queryGatewayServerDetailListByPage(request),
                repository.queryGatewayServerDetailListCount(request));
    }

    @Override
    public OperationResult<GatewayDistributionDataVO> queryGatewayDistribution(OperationRequest<GatewayDistributionDataVO> request) {
        return new OperationResult<>(repository.queryGatewayDistributionListByPage(request),
                repository.queryGatewayDistributionListCount(request));
    }

    @Override
    public OperationResult<ApplicationSystemDataVO> queryApplicationSystem(OperationRequest<ApplicationSystemDataVO> request) {
        return new OperationResult<>(repository.queryApplicationSystemListByPage(request),
                repository.queryApplicationSystemListCount(request));
    }

    @Override
    public OperationResult<ApplicationInterfaceDataVO> queryApplicationInterface(OperationRequest<ApplicationInterfaceDataVO> request) {
        return new OperationResult<>(repository.queryApplicationInterfaceListByPage(request),
                repository.queryApplicationInterfaceListCount(request));
    }

    @Override
    public OperationResult<ApplicationInterfaceMethodDataVO> queryApplicationInterfaceMethod(OperationRequest<ApplicationInterfaceMethodDataVO> request) {
        return new OperationResult<>(repository.queryApplicationInterfaceMethodListByPage(request),
                repository.queryApplicationInterfaceMethodListCount(request));
    }
}
