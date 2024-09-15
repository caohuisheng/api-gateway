package cn.bugstack.gateway.test;

import cn.bugstack.gateway.core.authorization.IAuth;
import cn.bugstack.gateway.core.authorization.JwtUtil;
import cn.bugstack.gateway.core.authorization.auth.AuthService;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: chs
 * Description:
 * CreateTime: 2024-09-08
 */
public class ShiroTest {

    @Test
    public void test_auth_service(){
        IAuth auth = new AuthService();
        boolean validate = auth.validate("chs", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaHMiLCJuYW1lIjoiY2hzIiwiaXNzIjoiY2hzIiwiZXhwIjoxNzI2MzkxNDgyLCJpYXQiOjE3MjU3ODY2ODIsImFnZSI6MTh9.wzMhL9DwPOe2jzs_rFb7tQaFJR2OexBw0hHxMeu3xBI");
        System.out.println(validate ? "验证成功":"验证失败");
    }

    @Test
    public void test_jwt(){
        String issuer = "chs";
        long ttlMillis = 7*24*60*60*1000L;
        Map<String, Object> claims = new HashMap<>();
        claims.put("key","chs");

        //编码
        String token = JwtUtil.encode(issuer, ttlMillis, claims);
        System.out.println(token);

        //解码
        Claims parser = JwtUtil.decode(token);
        System.out.println(parser.getSubject());
    }

    @Test
    public void test_shiro(){
        //获取SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:test-shiro.ini");
        //得到SecurityManager实例，并绑定该SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //得到Subject
        Subject subject = SecurityUtils.getSubject();
        //默认提供的验证方式
        UsernamePasswordToken token = new UsernamePasswordToken("xiaofuge", "123");

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println("身份验证失败");
        }

        System.out.println(subject.isAuthenticated() ? "验证成功":"验证失败");
        //退出
        subject.logout();
    }
}
