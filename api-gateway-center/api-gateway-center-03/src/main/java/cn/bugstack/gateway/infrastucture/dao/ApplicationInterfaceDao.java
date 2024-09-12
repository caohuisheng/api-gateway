package cn.bugstack.gateway.infrastucture.dao;

import cn.bugstack.gateway.infrastucture.po.ApplicationInterface;
import cn.bugstack.gateway.infrastucture.po.ApplicationSystem;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author: chs
 * Description: 应用系统dao
 * CreateTime: 2024-09-11
 */
@Mapper
public interface ApplicationInterfaceDao {

    void insert(ApplicationInterface applicationInterface);

}
