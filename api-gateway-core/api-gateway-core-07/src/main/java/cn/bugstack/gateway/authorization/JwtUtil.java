package cn.bugstack.gateway.authorization;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: chs
 * Description: jwt工具类
 * CreateTime: 2024-09-08
 */
public class JwtUtil {

    private static final String signingKey = "gateway";

    public static String encode(String issuer, long ttlMillis, Map<String, Object> claims){
        if(null == claims){
            claims = new HashMap<>();
        }

        //签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date();

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims) //载荷部分
                .setIssuedAt(now) //签发时间
                .setIssuer(issuer) //签发人
                .setSubject(issuer) //签发人
                .signWith(SignatureAlgorithm.HS256, signingKey); //签名算法和密钥

        //设置过期时间
        if(ttlMillis >= 0){
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate);
        }

        return builder.compact();
    }

    public static Claims decode(String token){
        return Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody();
    }

}
