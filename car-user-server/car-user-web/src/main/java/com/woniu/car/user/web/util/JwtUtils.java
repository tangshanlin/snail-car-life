package com.woniu.car.user.web.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    //设置过期时间7天
    private static final long EXPIRE_TIME=7*60*24*60*1000;
    //设置密钥
    private static final String SALT="wekjdekjfkjkjvgnjbvng276387498940-gft094494ititjreoioivg";

    //生成jwttoken
    public static String careatToken(Map<String,String> map){
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        builder.withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_TIME));
        return builder.sign(Algorithm.HMAC256(SALT)).toString();

    }

    //解析token
    public static DecodedJWT getDecodeToken(String token){
        System.out.println();
        return JWT.require(Algorithm.HMAC256(SALT)).build().verify(token);
    }
}
