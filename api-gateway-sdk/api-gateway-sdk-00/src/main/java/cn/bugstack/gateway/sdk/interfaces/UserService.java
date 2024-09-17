package cn.bugstack.gateway.sdk.interfaces;

import cn.bugstack.gateway.sdk.annotation.ApiProducerClazz;
import cn.bugstack.gateway.sdk.annotation.ApiProducerMethod;
import org.springframework.stereotype.Service;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-09-17
 */
@ApiProducerClazz(interfaceName = "用户服务",interfaceVersion = "1.0.0")
@Service
public class UserService {

    @ApiProducerMethod(methodName = "探测",uri = "/wg/user/hi",httpCommandType = "POST",auth = 1)
    public String hi(String str){
        return "hi " + str + " by api-gateway-sdk";
    }

}
