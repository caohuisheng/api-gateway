package cn.bugstack.gateway.application;

import cn.bugstack.gateway.domain.model.ApiData;

import java.util.List;

/**
 * Author: chs
 * Description: API服务
 * CreateTime: 2024-09-09
 */
public interface IApiService {

    List<ApiData> queryHttpStatementList();

}
