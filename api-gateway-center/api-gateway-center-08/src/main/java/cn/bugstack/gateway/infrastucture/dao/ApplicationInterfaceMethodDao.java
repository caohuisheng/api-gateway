package cn.bugstack.gateway.infrastucture.dao;

import cn.bugstack.gateway.domain.operation.model.vo.ApplicationInterfaceMethodDataVO;
import cn.bugstack.gateway.infrastucture.common.OperationRequest;
import cn.bugstack.gateway.infrastucture.po.ApplicationInterfaceMethod;
import cn.bugstack.gateway.infrastucture.po.ApplicationSystem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author: chs
 * Description: 应用系统dao
 * CreateTime: 2024-09-11
 */
@Mapper
public interface ApplicationInterfaceMethodDao {

    void insert(ApplicationInterfaceMethod applicationInterfaceMethod);

    List<ApplicationInterfaceMethod> queryApplicationInterfaceMethodListByPage(OperationRequest<ApplicationInterfaceMethodDataVO> request);
    int queryApplicationInterfaceMethodListCount(OperationRequest<ApplicationInterfaceMethodDataVO> request);

}
