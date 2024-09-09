package cn.bugstack.gateway.infrastucture.dao;

import cn.bugstack.gateway.infrastucture.po.HttpStatement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author: chs
 * Description: 网关接口映射信息表dao
 * CreateTime: 2024-09-09
 */
@Mapper
public interface HttpStatementDao {

    List<HttpStatement> queryHttpStatementList();

}
