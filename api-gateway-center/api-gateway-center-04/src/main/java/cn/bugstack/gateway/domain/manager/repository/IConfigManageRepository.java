package cn.bugstack.gateway.domain.manager.repository;

import cn.bugstack.gateway.domain.manager.model.vo.*;

import java.util.List;

/**
 * Author: chs
 * Description: 配置管理仓储接口
 * CreateTime: 2024-09-11
 */
public interface IConfigManageRepository {

    List<GatewayServerVO> queryGatewayServerList();

    boolean registryGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress, Integer available);

    GatewayServerDetailVO queryGatewayServerDetail(String gatewayId, String gatewayAddress);

    boolean updateGatewayStatus(String gatewayId, String gatewayAddress, Integer available);

    List<GatewayDistributionVO> queryGatewayDistributionList(String gatewayId);

    List<ApplicationSystemVO> queryApplicationSystemList(List<String> systemId);

    List<ApplicationInterfaceVO> queryInterfaceListBySystemId(String systemId);

}
