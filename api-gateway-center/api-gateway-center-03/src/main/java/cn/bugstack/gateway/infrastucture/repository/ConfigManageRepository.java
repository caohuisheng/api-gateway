package cn.bugstack.gateway.infrastucture.repository;

import cn.bugstack.gateway.domain.manager.model.vo.GatewayServerDetailVO;
import cn.bugstack.gateway.domain.manager.model.vo.GatewayServerVO;
import cn.bugstack.gateway.domain.manager.repository.IConfigManageRepository;
import cn.bugstack.gateway.infrastucture.dao.GatewayServerDao;
import cn.bugstack.gateway.infrastucture.dao.GatewayServerDetailDao;
import cn.bugstack.gateway.infrastucture.po.GatewayServer;
import cn.bugstack.gateway.infrastucture.po.GatewayServerDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: chs
 * Description: 配置管理仓储实现类
 * CreateTime: 2024-09-11
 */
@Component
public class ConfigManageRepository implements IConfigManageRepository {

    @Resource
    private GatewayServerDao gatewayServerDao;
    @Resource
    private GatewayServerDetailDao gatewayServerDetailDao;

    @Override
    public List<GatewayServerVO> queryGatewayServerList() {
        List<GatewayServer> gatewayServers = gatewayServerDao.queryGatewayServerList();
        return gatewayServers.stream().map(gatewayServer -> {
            GatewayServerVO gatewayServerVO = new GatewayServerVO();
            BeanUtils.copyProperties(gatewayServer, gatewayServerVO);
            return gatewayServerVO;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean registryGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress, Integer available) {
        GatewayServerDetail gatewayServerDetail = new GatewayServerDetail();
        gatewayServerDetail.setGroupId(groupId);
        gatewayServerDetail.setGatewayId(gatewayId);
        gatewayServerDetail.setGatewayName(gatewayName);
        gatewayServerDetail.setGatewayAddress(gatewayAddress);
        gatewayServerDetail.setStatus(available);

        gatewayServerDetailDao.insert(gatewayServerDetail);
        return true;
    }

    @Override
    public GatewayServerDetailVO queryGatewayServerDetail(String gatewayId, String gatewayAddress) {
        //构建请求参数
        GatewayServerDetail request = new GatewayServerDetail();
        request.setGatewayId(gatewayId);
        request.setGatewayAddress(gatewayAddress);
        GatewayServerDetail gatewayServerDetail = gatewayServerDetailDao.queryGatewayServerDetail(request);
        if(null == gatewayServerDetail) return null;

        GatewayServerDetailVO gatewayServerDetailVO = new GatewayServerDetailVO();
        BeanUtils.copyProperties(gatewayServerDetail, gatewayServerDetailVO);
        return gatewayServerDetailVO;
    }

    @Override
    public boolean updateGatewayStatus(String gatewayId, String gatewayAddress, Integer available) {
        GatewayServerDetail request = new GatewayServerDetail();
        request.setGatewayId(gatewayId);
        request.setGatewayAddress(gatewayAddress);
        request.setStatus(available);
        return gatewayServerDetailDao.updateGatewayServerStatus(request);
    }

}
