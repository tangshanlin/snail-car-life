package com.woniu.car.commons.web.filter;

import com.woniu.car.commons.core.code.ConstDate;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class TokenFilter implements RequestInterceptor {

    /**
     * @Author Lints
     * @Date 2021/4/6/006 12:20
     * @Description 为每个服务Feign转接之后做的一个登录认证，使其携带token
     * @Param [requestTemplate]
     * @Return void
     * @Since version-1.0
     */

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String auth_token = requestAttributes.getRequest().getHeader(ConstDate.REQUEST_HEADER_TOKEN);
        if (auth_token!=null) {
            requestTemplate.header(ConstDate.REQUEST_HEADER_TOKEN,auth_token);
        }
    }
}
