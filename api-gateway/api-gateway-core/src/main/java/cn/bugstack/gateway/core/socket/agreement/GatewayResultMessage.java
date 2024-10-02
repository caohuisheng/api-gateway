package cn.bugstack.gateway.core.socket.agreement;

/**
 * Author: chs
 * Description: 网关返回结果
 * CreateTime: 2024-09-08
 */
public class GatewayResultMessage {

    private String code;
    private String info;
    private Object data;
    private String node;

    public GatewayResultMessage(String code, String info, Object data, String node) {
        this.code = code;
        this.info = info;
        this.data = data;
        this.node = node;
    }

    public static GatewayResultMessage buildSuccess(Object data, String node){
        return new GatewayResultMessage(AgreementConstants.ResponseCode._200.getCode(), AgreementConstants.ResponseCode._200.getInfo(),data,node);
    }

    public static GatewayResultMessage buildError(String code, String info, String node){
        return new GatewayResultMessage(code,info,null,node);
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public Object getData() {
        return data;
    }
}
