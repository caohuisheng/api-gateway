package cn.bugstack.gateway.domain.manager.repository;

import cn.bugstack.gateway.domain.manager.model.ApiData;

import java.util.List;

/**
 * Author: chs
 * Description: HttpStatement仓储
 * CreateTime: 2024-09-09
 */
public interface IApiRepository {

    List<ApiData> queryHttpStatementList();

}
