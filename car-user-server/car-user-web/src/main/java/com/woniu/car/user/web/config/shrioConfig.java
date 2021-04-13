package com.woniu.car.user.web.config;


import com.woniu.car.user.web.JwtFilter;
import com.woniu.car.user.web.realm.Myrealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class shrioConfig {
@Bean
    public Realm realm(){
        Myrealm myrealm = new Myrealm();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(2048);
        myrealm.setCredentialsMatcher(matcher);
        return myrealm;
    }
@Bean
public DefaultWebSecurityManager defaultWebSecurityManager(){
    DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
    defaultWebSecurityManager.setRealm(realm());
    return defaultWebSecurityManager;

}
public ShiroFilterFactoryBean shiroFilterFactoryBean(){
    ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
    factoryBean.setSecurityManager(defaultWebSecurityManager());
    LinkedHashMap<String, String> filtermap = new LinkedHashMap<>();
//    //设置白名单
//    filtermap.put("/js/**","anon");
//    filtermap.put("/css/**","anon");
//    filtermap.put("/user/login","anon");
//    filtermap.put("/user/register","anon");
//    filtermap.put("/user/logout","logout");

//    //设置黑名单
//    filtermap.put("/**","user");

    // 添加自己的过滤器并且取名为jwt
    Map<String, Filter> filterLinkedHashMap = new LinkedHashMap<>();
    //设置我们自定义的JWT过滤器
    filterLinkedHashMap.put("jwt", new JwtFilter());
    factoryBean.setFilters(filterLinkedHashMap);
    // 所有请求通过我们自己的JWT Filter
    filtermap.put("/**", "jwt");
    filtermap.put("/user/login","anon");//允许匿名访问
    factoryBean.setFilterChainDefinitionMap(filtermap);

    return factoryBean;

}

}
