package cn.bugstack.gateway.domain.register.service;

import cn.bugstack.gateway.application.IRegisterManageService;
import cn.bugstack.gateway.domain.register.model.vo.ApplicationInterfaceMethodVO;
import cn.bugstack.gateway.domain.register.model.vo.ApplicationInterfaceVO;
import cn.bugstack.gateway.domain.register.model.vo.ApplicationSystemVO;
import cn.bugstack.gateway.domain.register.repository.IRegisterManageRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author: chs
 * Description: 接口注册服务
 * CreateTime: 2024-09-12
 */
@Service
public class RegisterManageServiceImpl implements IRegisterManageService {

    @Resource
    private IRegisterManageRepository registerManageRepository;

    @Override
    public void registerApplication(ApplicationSystemVO applicationSystemVO) {
        registerManageRepository.registerApplication(applicationSystemVO);
    }

    @Override
    public void registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO) {
        registerManageRepository.registerApplicationInterface(applicationInterfaceVO);
    }

    @Override
    public void registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO) {
        registerManageRepository.registerApplicationInterfaceMethod(applicationInterfaceMethodVO);
    }

}
