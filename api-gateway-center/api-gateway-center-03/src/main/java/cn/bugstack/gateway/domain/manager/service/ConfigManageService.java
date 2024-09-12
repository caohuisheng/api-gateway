package cn.bugstack.gateway.domain.manager.service;

import cn.bugstack.gateway.application.IConfigManageService;
import cn.bugstack.gateway.domain.manager.model.vo.GatewayServerDetailVO;
import cn.bugstack.gateway.domain.manager.model.vo.GatewayServerVO;
import cn.bugstack.gateway.domain.manager.repository.IConfigManageRepository;
import cn.bugstack.gateway.infrastucture.common.Constants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-09-11
 */
@Service
public class ConfigManageService implements IConfigManageService {

    @Resource
    private IConfigManageRepository configManageRepository;

    @Override
    public List<GatewayServerVO> queryGatewayServerList() {
        return configManageRepository.queryGatewayServerList();
    }

    @Override
    public boolean registerGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress) {
        GatewayServerDetailVO gatewayServerDetailVO = configManageRepository.queryGatewayServerDetail(gatewayId, gatewayAddress);
        if(null == gatewayServerDetailVO){
            try {
                return configManageRepository.registryGatewayServerNode(groupId, gatewayId, gatewayName, gatewayAddress, Constants.GatewayStatus.Available);
            } catch (Exception e) {
                return configManageRepository.updateGatewayStatus(gatewayId, gatewayAddress, Constants.GatewayStatus.Available);
            }
        }else{
            return configManageRepository.updateGatewayStatus(gatewayId, gatewayAddress, Constants.GatewayStatus.Available);
        }
    }
}
