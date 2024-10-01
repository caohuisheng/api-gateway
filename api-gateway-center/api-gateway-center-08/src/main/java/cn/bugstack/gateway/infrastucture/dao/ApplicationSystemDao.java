package cn.bugstack.gateway.infrastucture.dao;

import cn.bugstack.gateway.domain.manager.model.vo.ApplicationSystemVO;
import cn.bugstack.gateway.domain.operation.model.vo.ApplicationSystemDataVO;
import cn.bugstack.gateway.infrastucture.common.OperationRequest;
import cn.bugstack.gateway.infrastucture.po.ApplicationSystem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author: chs
 * Description: 应用系统dao
 * CreateTime: 2024-09-11
 */
@Mapper
public interface ApplicationSystemDao {

    void insert(ApplicationSystem applicationSystem);

    List<ApplicationSystemVO> queryApplicationSystemList(List<String> systemIds);

    List<ApplicationSystem> queryApplicationSystemListByPage(OperationRequest<ApplicationSystemDataVO> request);
    int queryApplicationSystemListCount(OperationRequest<ApplicationSystemDataVO> request);

}
