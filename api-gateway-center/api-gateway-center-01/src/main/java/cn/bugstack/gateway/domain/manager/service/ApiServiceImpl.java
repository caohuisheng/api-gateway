package cn.bugstack.gateway.domain.manager.service;

import cn.bugstack.gateway.application.IApiService;
import cn.bugstack.gateway.domain.manager.model.ApiData;
import cn.bugstack.gateway.domain.manager.repository.IApiRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author: chs
 * Description: Api服务实现类
 * CreateTime: 2024-09-09
 */
@Service
public class ApiServiceImpl implements IApiService {

    @Resource
    private IApiRepository apiRepository;

    @Override
    public List<ApiData> queryHttpStatementList() {
        return apiRepository.queryHttpStatementList();
    }
}
