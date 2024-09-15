package cn.bugstack.gateway.application;

import cn.bugstack.gateway.domain.manager.model.aggregate.ApplicationSystemRichInfo;
import cn.bugstack.gateway.domain.manager.model.vo.GatewayServerVO;

import java.util.List;

/**
 * Author: chs
 * Description: 配置管理服务
 * CreateTime: 2024-09-11
 */
public interface IConfigManageService {

    List<GatewayServerVO> queryGatewayServerList();

    boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress);

    ApplicationSystemRichInfo queryApplicationSystemRichInfo(String gatewayId);
}
