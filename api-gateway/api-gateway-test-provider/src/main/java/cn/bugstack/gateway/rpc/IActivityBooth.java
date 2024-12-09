package cn.bugstack.gateway.rpc;

import cn.bugstack.gateway.rpc.dto.XReq;

public interface IActivityBooth {

    String sayHi(String str);

    String insert(XReq req);

    String test(String str, XReq req);

}
