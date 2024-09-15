package cn.bugstack.gateway.infrastucture.dao;

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

}
