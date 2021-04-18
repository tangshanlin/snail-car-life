//package com.woniu.car.exception;
//
//import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
//import org.springframework.core.io.buffer.DataBufferFactory;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//@Component
//public class MyGatewayException implements ErrorWebExceptionHandler {
//
//    @Override
//    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
//        serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
//        DataBufferFactory bufferFactory = serverWebExchange.getResponse().bufferFactory();
//        return serverWebExchange.getResponse().writeWith(Flux.just(bufferFactory.wrap(("{\"msg\":\""+throwable.getMessage()+"\"}").getBytes())));
//    }
//
//}
