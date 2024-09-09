package cn.bugstack.gateway.infrastucture.repository;

import cn.bugstack.gateway.domain.model.ApiData;
import cn.bugstack.gateway.domain.repository.IApiRepository;
import cn.bugstack.gateway.infrastucture.dao.HttpStatementDao;
import cn.bugstack.gateway.infrastucture.po.HttpStatement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-09-09
 */
@Component
public class ApiRepository implements IApiRepository {

    @Resource
    private HttpStatementDao httpStatementDao;

    @Override
    public List<ApiData> queryHttpStatementList() {
        List<HttpStatement> httpStatements = httpStatementDao.queryHttpStatementList();
        return httpStatements.stream().map(httpStatement -> {
            ApiData apiData = new ApiData();
            BeanUtils.copyProperties(httpStatement, apiData);
            return apiData;
        }).collect(Collectors.toList());
    }

}
