//package com.woniu.car.filter;
//
//import com.auth0.jwt.interfaces.Claim;
//import com.woniu.car.exception.MyException;
//import com.woniu.car.util.JWTUtils;
//import com.woniu.car.util.PreJwtUtils;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import javax.annotation.Resource;
//import java.security.SignatureException;
//
//@Component
//@Order(1)
//public class MyGlobalFilter implements GlobalFilter {
//    @Resource
//    private JWTUtils jwtUtils;
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        System.out.println("--------------------全局过滤器1--------------------------------");
//        String url = exchange.getRequest().getPath().toString();
//        if(!(url.equals("/auth/back-user/api/login")||url.equals("user/loginByPasswordUsingPOST")||url.equals("user/checkByTelUsingGET"))){
//            String auth_token = exchange.getRequest().getHeaders().getFirst("auth_token");
//            System.out.println(auth_token);
//            if(auth_token==null) throw new MyException("未登录");
//            if(url.contains("/api")){
//                //后端token
//                Claims claims = jwtUtils.checkJWT(auth_token);
//                String username = claims.get("username", String.class);
//                System.out.println(username);
//            }else{
//                //前端token
//                String userId = PreJwtUtils.getDecodeToken(auth_token).getClaim("userId").asString();
//                System.out.println(userId);
//            }
//        }
//        return chain.filter(exchange);
//    }
//}
