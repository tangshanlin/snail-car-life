package com.woniu.car.message.web.util;

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

    //解析token
    public static DecodedJWT getDecodeToken(String token){
        System.out.println();
        return JWT.require(Algorithm.HMAC256(SALT)).build().verify(token);
    }
}
