package com.woniu.car.message.client;

import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.message.model.feign.ShopIdParamVo;
import com.woniu.car.message.model.feign.ShopIntegralDtoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("car-shop-server")
public interface ShopClient {

    @GetMapping("/shop/shop/get_shop_integral_by_shop_id")
    public ResultEntity<ShopIntegralDtoVo> getShopIntegralByShopId(@SpringQueryMap ShopIdParamVo shopIdParamVo);

    @PutMapping("/shop/shop/update_shop_integral_by_shop_id")
    public ResultEntity updateShopIntegralByShopId(@RequestBody ShopIdParamVo shopIdParamVo);


}
