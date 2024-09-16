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

    public GatewayResultMessage(String code, String info, Object data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public static GatewayResultMessage buildSuccess(Object data){
        return new GatewayResultMessage(AgreementConstants.ResponseCode._200.getCode(), AgreementConstants.ResponseCode._200.getInfo(),data);
    }

    public static GatewayResultMessage buildError(String code, String info){
        return new GatewayResultMessage(code,info,null);
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
