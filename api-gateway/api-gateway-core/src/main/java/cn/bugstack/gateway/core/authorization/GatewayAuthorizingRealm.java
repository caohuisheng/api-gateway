package cn.bugstack.gateway.core.authorization;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-09-08
 */
//@Component
public class GatewayAuthorizingRealm extends AuthorizingRealm {

    @Override
    public Class<?> getAuthenticationTokenClass() {
        return GatewayAuthenticationToken.class;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //暂时不需要做授权处理
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        try {
            JwtUtil.decode(((GatewayAuthenticationToken)token).getJwt());
        } catch (Exception e) {
            throw new AuthenticationException("无效令牌");
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(),this.getName());
    }

}
