package cn.bugstack.gateway.core.authorization.auth;

import cn.bugstack.gateway.core.authorization.GatewayAuthenticationToken;
import cn.bugstack.gateway.core.authorization.IAuth;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Author: chs
 * Description: 认证服务实现
 * CreateTime: 2024-09-08
 */
public class AuthService implements IAuth {

    private Subject subject;

    public AuthService(){
        //获取SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //得到SecurityManager实例并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //得到Subject
        this.subject = SecurityUtils.getSubject();
    }

    @Override
    public boolean validate(String id, String token) {
        try {
            //身份验证
            subject.login(new GatewayAuthenticationToken(id, token));
            //返回结果
            return subject.isAuthenticated();
        }catch(AuthenticationException e){
            e.printStackTrace();
            return false;
        }finally {
            //退出
            subject.logout();
        }
    }


}
