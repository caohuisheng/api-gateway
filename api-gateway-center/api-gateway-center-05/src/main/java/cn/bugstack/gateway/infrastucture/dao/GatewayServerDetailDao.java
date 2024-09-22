package cn.bugstack.gateway.infrastucture.dao;

import cn.bugstack.gateway.infrastucture.po.GatewayServerDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-09-10
 */
@Mapper
public interface GatewayServerDetailDao {

    void insert(GatewayServerDetail gatewayServerDetail);

    GatewayServerDetail queryGatewayServerDetail(GatewayServerDetail gatewayServerDetail);

    boolean updateGatewayServerStatus(GatewayServerDetail gatewayServerDetail);

}
