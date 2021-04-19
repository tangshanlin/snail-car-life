package com.woniu.car.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.Map;

/**
 * Jwt工具类
 * 注意点:
 * 1、生成的token, 是可以通过base64进行解密出明文信息
 * 2、base64进行解密出明文信息，修改再进行编码，则会解密失败
 * 3、无法作废已颁布的token，除非改秘钥
 * @author WTY
 */
@Component
public class JWTUtils {

    /**
     * 密钥
     */
    @Value("${config.jwt.secret}")
    private  String secret;
    /**
     * 过期时间
     */
    @Value("${config.jwt.expire}")
    private  long expire;

    /**
     * subject
     */
    @Value("${config.jwt.subject}")
    private  String subject;
    /**
     * 令牌前缀
     */
    @Value("${config.jwt.tokenPrefix}")
    private  String tokenPrefix;

    /**
     * 根据用户信息，生成令牌
     *
     * @param
     * @return
     */
    public  String geneJsonWebToken(Map<String, Object> maps) {
        System.out.println(secret);
        String token = Jwts.builder()
                .setSubject(subject)
                .setClaims(maps)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
        token = tokenPrefix + token;
        return token;
    }


    /**
     * 校验token的方法
     *
     * @param token
     * @return
     */
    public  Claims checkJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token.replace(tokenPrefix, "")).getBody();
        return claims;
    }


}
