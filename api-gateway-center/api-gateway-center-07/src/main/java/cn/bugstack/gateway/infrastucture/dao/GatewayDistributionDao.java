package cn.bugstack.gateway.infrastucture.dao;

import cn.bugstack.gateway.domain.operation.model.vo.GatewayDistributionDataVO;
import cn.bugstack.gateway.infrastucture.common.OperationRequest;
import cn.bugstack.gateway.infrastucture.po.GatewayDistribution;
import cn.bugstack.gateway.infrastucture.po.GatewayServer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-09-11
 */
@Mapper
public interface GatewayDistributionDao {

    List<GatewayDistribution> queryGatewayDistributionList(String gatewayId);

    String queryGatewayDistribution(String systemId);

    List<GatewayDistribution> queryGatewayDistributionListByPage(OperationRequest<GatewayDistributionDataVO> request);

    int queryGatewayDistributionListCount(OperationRequest<GatewayDistributionDataVO> request);

}
