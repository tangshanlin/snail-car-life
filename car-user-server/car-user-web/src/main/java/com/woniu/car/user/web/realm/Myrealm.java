package com.woniu.car.user.web.realm;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.woniu.car.user.web.token.JwtToken;
import com.woniu.car.user.web.util.JwtUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.ObjectUtils;

public class Myrealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("开始认证-----------------------");
        String token = (String) authenticationToken.getPrincipal();
        //解析token
        DecodedJWT decodeToken = JwtUtils.getDecodeToken(token);
        String username = decodeToken.getClaim("username").asString();
        if (ObjectUtils.isEmpty(username)){
            throw new AuthenticationException("认证失败！");
        }


        return new SimpleAuthenticationInfo(token,token,this.getName());
    }
}
