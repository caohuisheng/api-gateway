package cn.bugstack.gateway.interfaces;

import cn.bugstack.gateway.application.IConfigManageService;
import cn.bugstack.gateway.application.IMessageService;
import cn.bugstack.gateway.application.IRegisterManageService;
import cn.bugstack.gateway.domain.register.model.vo.ApplicationInterfaceMethodVO;
import cn.bugstack.gateway.domain.register.model.vo.ApplicationInterfaceVO;
import cn.bugstack.gateway.domain.register.model.vo.ApplicationSystemVO;
import cn.bugstack.gateway.infrastucture.common.Constants;
import cn.bugstack.gateway.infrastucture.common.ResponseCode;
import cn.bugstack.gateway.infrastucture.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Author: chs
 * Description: 接口注册管理
 * CreateTime: 2024-09-12
 */
@RestController
@RequestMapping("/wg/admin/register")
public class RpcRegisterController {

    private Logger log = LoggerFactory.getLogger(RpcRegisterController.class);

    @Resource
    private IRegisterManageService registerManageService;
    @Resource
    private IConfigManageService configManageService;
    @Resource
    private IMessageService messageService;

    @PostMapping(value = "registerApplication", produces = "application/json;charset=utf-8")
    public Result<Boolean> registerApplication(ApplicationSystemVO applicationSystemVO) {
        try {
            log.info("注册应用服务 systemId:{}", applicationSystemVO.getSystemId());
            registerManageService.registerApplication(applicationSystemVO);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), true);
        } catch (DuplicateKeyException e) {
            log.warn("注册应用服务重复 systemId:{}", applicationSystemVO.getSystemId(), e);
            return new Result<>(ResponseCode.INDEX_DUP.getCode(), e.getMessage(), false);
        } catch (Exception e) {
            log.error("注册应用服务失败 systemId:{}", applicationSystemVO.getSystemId(), e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }
    }

    @PostMapping(value = "registerApplicationInterface", produces = "application/json;charset=utf-8")
    public Result<Boolean> registerApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO) {
        String systemId = applicationInterfaceVO.getSystemId();
        String interfaceId = applicationInterfaceVO.getInterfaceId();
        try {
            log.info("注册应用接口 systemId:{} interfaceId:{}", systemId, interfaceId);
            registerManageService.registerApplicationInterface(applicationInterfaceVO);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), true);
        } catch (DuplicateKeyException e) {
            log.warn("注册应用接口重复 systemId:{} interfaceId:{}", systemId, interfaceId);
            return new Result<>(ResponseCode.INDEX_DUP.getCode(), e.getMessage(), false);
        } catch (Exception e) {
            log.error("注册应用接口失败 systemId:{} interfaceId:{}", systemId, interfaceId);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }
    }

    @PostMapping(value = "registerApplicationInterfaceMethod", produces = "application/json;charset=utf-8")
    public Result<Boolean> registerApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO) {
        String systemId = applicationInterfaceMethodVO.getSystemId();
        String interfaceId = applicationInterfaceMethodVO.getInterfaceId();
        String methodId = applicationInterfaceMethodVO.getMethodId();
        try {
            log.info("注册应用接口方法 systemId:{} interfaceId:{} methodId:{}", systemId, interfaceId, methodId);
            registerManageService.registerApplicationInterfaceMethod(applicationInterfaceMethodVO);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), true);
        } catch (DuplicateKeyException e) {
            log.warn("注册应用接口方法重复 systemId:{} interfaceId:{} methodId:{}", systemId, interfaceId, methodId);
            return new Result<>(ResponseCode.INDEX_DUP.getCode(), e.getMessage(), false);
        } catch (Exception e) {
            log.error("注册应用接口方法失败 systemId:{} interfaceId:{} methodId:{}", systemId, interfaceId, methodId);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }
    }

    @PostMapping(value = "registerEvent", produces = "application/json;charset=utf-8")
    public Result<Boolean> registerEvent(@RequestParam String systemId) {
        try {
            log.info("应用信息注册完成通知 systemId:{}", systemId);
            //推送注册信息
            String gatewayId = configManageService.queryGatewayDistribution(systemId);
            messageService.pushMessage(gatewayId, systemId);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), true);
        } catch (Exception e) {
            log.error("应用信息注册完成通知失败 systemId:{}", systemId, e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }
    }
}