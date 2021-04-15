package com.woniu.car.service.web.feign;

import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.web.feign.FeignBaseConfig;
import com.woniu.car.shop.model.paramVo.AddShopServiceEarningsParamVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName ShopFeignClient
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/15 11:23
 * @Version 1.0
 */

@FeignClient(name = "car-shop-server",configuration = FeignBaseConfig.class)
public interface ShopFeignClient {
    @PostMapping("/shop/shop-service-earnings/add_shop_service_earnings")
    public ResultEntity addShopServiceEarnings(@RequestBody AddShopServiceEarningsParamVo addShopServiceEarningsParamVo);
}
