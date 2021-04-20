package com.woniu.car.user.web.util;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.Claim;
import com.woniu.car.commons.core.code.ConstDate;
import com.woniu.car.commons.core.exception.CarException;
import org.apache.shiro.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.sql.rowset.CachedRowSet;

/**
 * @ClassName GetTokenUtil
 * @Desc TODO
 * @Author Administrator
 * @Date 2021/4/9 15:59
 * @Version 1.0
 */
public class GetTokenUtil {

    public  static String  getToken(){
        ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String  token = requestAttributes.getRequest().getHeader(ConstDate.REQUEST_HEADER_TOKEN);
        if(!StringUtils.hasLength(token)){
            throw new CarException("未登录，请先去登陆",500);
        }
        return token;

    }
    public  static Integer  getUserId(){
        ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String  token = requestAttributes.getRequest().getHeader(ConstDate.REQUEST_HEADER_TOKEN);
        if(!StringUtils.hasLength(token)){
            throw new CarException("未登录，请先去登陆",500);
        }
        System.out.println(token);

        Claim userId = JwtUtils.getDecodeToken(token).getClaim("userId");
        Integer userId2 = Integer.valueOf(userId.asString());
        return userId2;

    }
    public  static String  getUserAccount(){
        ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String  token = requestAttributes.getRequest().getHeader(ConstDate.REQUEST_HEADER_TOKEN);
        Claim userAccount = JwtUtils.getDecodeToken(token).getClaim("userAccount");
       String userAccount2 = userAccount.asString();
        return userAccount2;

    }
}
