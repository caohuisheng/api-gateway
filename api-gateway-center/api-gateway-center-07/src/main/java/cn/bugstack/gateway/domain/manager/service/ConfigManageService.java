package cn.bugstack.gateway.domain.manager.service;

import cn.bugstack.gateway.application.IConfigManageService;
import cn.bugstack.gateway.domain.manager.model.aggregate.ApplicationSystemRichInfo;
import cn.bugstack.gateway.domain.manager.model.vo.*;
import cn.bugstack.gateway.domain.manager.repository.IConfigManageRepository;
import cn.bugstack.gateway.infrastucture.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ApplicationSystemRichInfo queryApplicationSystemRichInfo(String gatewayId,String systemId) {
        //根据网关id查询所有的网关分配
        List<GatewayDistributionVO> gatewayDistributionVOS = configManageRepository.queryGatewayDistributionList(gatewayId);
        List<String> systemIds = gatewayDistributionVOS.stream().map(gatewayDistributionVO -> gatewayDistributionVO.getSystemId()).collect(Collectors.toList());
        if(StringUtils.isNotBlank(systemId)) systemIds.add(systemId);
        //根据系统id列表查询所有网关系统
        List<ApplicationSystemVO> applicationSystemVOS = configManageRepository.queryApplicationSystemList(systemIds);
        //查询每个系统下的所有接口
        applicationSystemVOS.forEach(system -> {
            List<ApplicationInterfaceVO> interfaceList = configManageRepository.queryInterfaceListBySystemId(system.getSystemId());
            system.setInterfaceList(interfaceList);
        });
        return new ApplicationSystemRichInfo(gatewayId, applicationSystemVOS);
    }

    @Override
    public String queryGatewayDistribution(String systemId) {
        return configManageRepository.queryGatewayDistribution(systemId);
    }
}
