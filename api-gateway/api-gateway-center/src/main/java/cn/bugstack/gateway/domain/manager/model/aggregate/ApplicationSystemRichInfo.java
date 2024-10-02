package cn.bugstack.gateway.domain.manager.model.aggregate;

import cn.bugstack.gateway.domain.manager.model.vo.ApplicationSystemVO;

import java.util.List;

/**
 * 网关算力配置信息
 */
public class ApplicationSystemRichInfo {

    // 网关id
    private String gatewayId;
    // 系统列表
    private List<ApplicationSystemVO> systemList;

    public ApplicationSystemRichInfo(String gatewayId, List<ApplicationSystemVO> systemList) {
        this.gatewayId = gatewayId;
        this.systemList = systemList;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public List<ApplicationSystemVO> getSystemList() {
        return systemList;
    }

    public void setSystemList(List<ApplicationSystemVO> systemList) {
        this.systemList = systemList;
    }
}
