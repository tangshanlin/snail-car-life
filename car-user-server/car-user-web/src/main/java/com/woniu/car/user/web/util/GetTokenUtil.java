package com.woniu.car.user.web.util;

import com.auth0.jwt.interfaces.Claim;
import com.woniu.car.commons.core.code.ConstDate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
        return token;

    }
    public  static Integer  getUserId(){
        ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String  token = requestAttributes.getRequest().getHeader(ConstDate.REQUEST_HEADER_TOKEN);
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
