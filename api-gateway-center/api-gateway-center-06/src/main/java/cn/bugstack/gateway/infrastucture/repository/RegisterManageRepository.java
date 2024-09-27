package cn.bugstack.gateway.infrastucture.repository;

import cn.bugstack.gateway.domain.register.model.vo.ApplicationInterfaceMethodVO;
import cn.bugstack.gateway.domain.register.model.vo.ApplicationInterfaceVO;
import cn.bugstack.gateway.domain.register.model.vo.ApplicationSystemVO;
import cn.bugstack.gateway.domain.register.repository.IRegisterManageRepository;
import cn.bugstack.gateway.infrastucture.dao.ApplicationInterfaceDao;
import cn.bugstack.gateway.infrastucture.dao.ApplicationInterfaceMethodDao;
import cn.bugstack.gateway.infrastucture.dao.ApplicationSystemDao;
import cn.bugstack.gateway.infrastucture.po.ApplicationInterface;
import cn.bugstack.gateway.infrastucture.po.ApplicationInterfaceMethod;
import cn.bugstack.gateway.infrastucture.po.ApplicationSystem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Author: chs
 * Description: 注册管理仓储
 * CreateTime: 2024-09-12
 */
@Component
public class RegisterManageRepository implements IRegisterManageRepository {

    @Resource
    private ApplicationSystemDao applicationSystemDao;
    @Resource
    private ApplicationInterfaceDao applicationInterfaceDao;
    @Resource
    private ApplicationInterfaceMethodDao applicationInterfaceMethodDao;

    @Override
    public void registerApplication(ApplicationSystemVO applicationSystemVO) {
        ApplicationSystem applicationSystem = new ApplicationSystem();
        BeanUtils.copyProperties(applicationSystemVO, applicationSystem);
        applicationSystemDao.insert(applicationSystem);
    }

    @Override
    public void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO) {
        ApplicationInterface applicationInterface = new ApplicationInterface();
        BeanUtils.copyProperties(applicationInterfaceVO, applicationInterface);
        applicationInterfaceDao.insert(applicationInterface);
    }

    @Override
    public void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO) {
        ApplicationInterfaceMethod applicationInterfaceMethod = new ApplicationInterfaceMethod();
        BeanUtils.copyProperties(applicationInterfaceMethodVO, applicationInterfaceMethod);
        applicationInterfaceMethodDao.insert(applicationInterfaceMethod);
    }
}
