package cn.bugstack.gateway.infrastucture.repository;

import cn.bugstack.gateway.domain.operation.model.vo.*;
import cn.bugstack.gateway.domain.operation.repository.IDataOperationManageRepository;
import cn.bugstack.gateway.infrastucture.common.OperationRequest;
import cn.bugstack.gateway.infrastucture.dao.*;
import cn.bugstack.gateway.infrastucture.po.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: chs
 * Description: 网关运营数据管理仓储实现类
 * CreateTime: 2024-09-25
 */
@Component
public class DataOperationManageRepository implements IDataOperationManageRepository {

    @Resource
    private GatewayServerDao gatewayServerDao;
    @Resource
    private GatewayServerDetailDao gatewayServerDetailDao;
    @Resource
    private GatewayDistributionDao gatewayDistributionDao;
    @Resource
    private ApplicationSystemDao applicationSystemDao;
    @Resource
    private ApplicationInterfaceDao applicationInterfaceDao;
    @Resource
    private ApplicationInterfaceMethodDao applicationInterfaceMethodDao;

    @Override
    public List<GatewayServerDataVO> queryGatewayServerListByPage(OperationRequest<String> request) {
        List<GatewayServer> gatewayServerList = gatewayServerDao.queryGatewayServerListByPage(request);
        return gatewayServerList.stream().map(gatewayServer -> {
            GatewayServerDataVO vo = new GatewayServerDataVO();
            BeanUtils.copyProperties(gatewayServer,vo);
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public int queryGatewayServerListCount(OperationRequest<String> request) {
        return gatewayServerDao.queryGatewayServerListCount(request);
    }

    @Override
    public List<GatewayServerDetailDataVO> queryGatewayServerDetailListByPage(OperationRequest<GatewayServerDetailDataVO> request) {
        List<GatewayServerDetail> gatewayServerDetailList = gatewayServerDetailDao.queryGatewayServerDetailListByPage(request);
        return gatewayServerDetailList.stream().map(gatewayServerDetail -> {
            GatewayServerDetailDataVO vo = new GatewayServerDetailDataVO();
            BeanUtils.copyProperties(gatewayServerDetail, vo);
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public int queryGatewayServerDetailListCount(OperationRequest<GatewayServerDetailDataVO> request) {
        return gatewayServerDetailDao.queryGatewayServerDetailListCount(request);
    }

    @Override
    public List<GatewayDistributionDataVO> queryGatewayDistributionListByPage(OperationRequest<GatewayDistributionDataVO> request) {
        List<GatewayDistribution> gatewayDistributionList = gatewayDistributionDao.queryGatewayDistributionListByPage(request);
        return gatewayDistributionList.stream().map(gatewayDistribution -> {
            GatewayDistributionDataVO vo = new GatewayDistributionDataVO();
            BeanUtils.copyProperties(gatewayDistribution, vo);
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public int queryGatewayDistributionListCount(OperationRequest<GatewayDistributionDataVO> request) {
        return gatewayDistributionDao.queryGatewayDistributionListCount(request);
    }

    @Override
    public List<ApplicationSystemDataVO> queryApplicationSystemListByPage(OperationRequest<ApplicationSystemDataVO> request) {
        List<ApplicationSystem> applicationSystemList = applicationSystemDao.queryApplicationSystemListByPage(request);
        return applicationSystemList.stream().map(applicationSystem -> {
            ApplicationSystemDataVO vo = new ApplicationSystemDataVO();
            BeanUtils.copyProperties(applicationSystem,vo);
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public int queryApplicationSystemListCount(OperationRequest<ApplicationSystemDataVO> request) {
        return applicationSystemDao.queryApplicationSystemListCount(request);
    }

    @Override
    public List<ApplicationInterfaceDataVO> queryApplicationInterfaceListByPage(OperationRequest<ApplicationInterfaceDataVO> request) {
        List<ApplicationInterface> applicationInterfaceList = applicationInterfaceDao.queryApplicationInterfaceListByPage(request);
        return applicationInterfaceList.stream().map(applicationInterface -> {
            ApplicationInterfaceDataVO vo = new ApplicationInterfaceDataVO();
            BeanUtils.copyProperties(applicationInterface, vo);
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public int queryApplicationInterfaceListCount(OperationRequest<ApplicationInterfaceDataVO> request) {
        return applicationInterfaceDao.queryApplicationInterfaceListCount(request);
    }

    @Override
    public List<ApplicationInterfaceMethodDataVO> queryApplicationInterfaceMethodListByPage(OperationRequest<ApplicationInterfaceMethodDataVO> request) {
        List<ApplicationInterfaceMethod> applicationInterfaceMethodList = applicationInterfaceMethodDao.queryApplicationInterfaceMethodListByPage(request);
        return applicationInterfaceMethodList.stream().map(applicationInterfaceMethod -> {
            ApplicationInterfaceMethodDataVO vo = new ApplicationInterfaceMethodDataVO();
            BeanUtils.copyProperties(applicationInterfaceMethod, vo);
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public int queryApplicationInterfaceMethodListCount(OperationRequest<ApplicationInterfaceMethodDataVO> request) {
        return applicationInterfaceMethodDao.queryApplicationInterfaceMethodListCount(request);
    }
}
