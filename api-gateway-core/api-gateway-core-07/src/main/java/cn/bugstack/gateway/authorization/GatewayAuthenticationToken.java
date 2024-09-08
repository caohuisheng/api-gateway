package cn.bugstack.gateway.authorization;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Author: chs
 * Description: 验证token
 * CreateTime: 2024-09-08
 */
public class GatewayAuthenticationToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    private String channelId;
    private String jwt;

    public GatewayAuthenticationToken() {
    }

    public GatewayAuthenticationToken(String channelId, String jwt) {
        this.channelId = channelId;
        this.jwt = jwt;
    }

    @Override
    public Object getPrincipal() {
        return channelId;
    }

    @Override
    public Object getCredentials() {
        return jwt;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
