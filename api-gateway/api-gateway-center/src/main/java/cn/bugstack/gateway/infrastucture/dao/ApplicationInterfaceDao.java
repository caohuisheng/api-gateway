package cn.bugstack.gateway.infrastucture.dao;

import cn.bugstack.gateway.domain.manager.model.vo.ApplicationInterfaceVO;
import cn.bugstack.gateway.domain.operation.model.vo.ApplicationInterfaceDataVO;
import cn.bugstack.gateway.infrastucture.common.OperationRequest;
import cn.bugstack.gateway.infrastucture.po.ApplicationInterface;
import cn.bugstack.gateway.infrastucture.po.ApplicationSystem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author: chs
 * Description: 应用系统dao
 * CreateTime: 2024-09-11
 */
@Mapper
public interface ApplicationInterfaceDao {

    void insert(ApplicationInterface applicationInterface);

    List<ApplicationInterfaceVO> queryApplicationInterfaceList(String systemId);

    List<ApplicationInterface> queryApplicationInterfaceListByPage(OperationRequest<ApplicationInterfaceDataVO> request);
    int queryApplicationInterfaceListCount(OperationRequest<ApplicationInterfaceDataVO> request);

}
