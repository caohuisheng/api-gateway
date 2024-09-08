package cn.bugstack.gateway.socket.agreement;

import cn.bugstack.gateway.mapping.HttpStatement;
import io.netty.util.AttributeKey;

/**
 * Author: chs
 * Description: 协议参数
 * CreateTime: 2024-09-08
 */
public class AgreementConstants {

    public static final AttributeKey<HttpStatement> HTTP_STATEMENT = AttributeKey.valueOf("HttpStatement");

    public enum ResponseCode{
        _200("200","成功"),
        _400("400","接收数据的数据类型不匹配"),
        _403("403","服务器拒绝请求"),
        _404("404","服务器找不到请求的网页，输入链接有误"),
        _500("500","服务器遇到错误，无法完成请求"),
        _502("502","服务器作为网关或代理，从上游服务器收到无效响应");

        private String code;
        private String info;

        ResponseCode(String code, String info){
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }
}
