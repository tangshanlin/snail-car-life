package com.woniu.car.order.client.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName OrderShopClient
 * @Desc TODO
 * @Author 21174
 * @Date 2021/4/10 22:30
 * @Version 1.0
 */
@FeignClient("car-shop-server")
public interface OrderShopClient {

}
