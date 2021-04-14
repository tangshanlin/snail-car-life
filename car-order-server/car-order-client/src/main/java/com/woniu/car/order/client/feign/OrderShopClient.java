package com.woniu.car.order.client.feign;

import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.shop.model.dtoVo.FindShopInfoVo;
import com.woniu.car.shop.model.paramVo.ShopIdParamVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

/**
 * @ClassName OrderShopClient
 * @Desc TODO
 * @Author 21174
 * @Date 2021/4/10 22:30
 * @Version 1.0
 */
@FeignClient("car-shop-server")
public interface OrderShopClient {
    /**
     * @return com.woniu.car.commons.core.dto.ResultEntity<com.woniu.car.shop.model.dtoVo.FindShopInfoVo>
     * @Author WangPeng
     * @Description TODO 根据门店id查询详细信息接口
     * @Date 14:50
     * @Param [shopId]
     **/
    @GetMapping("shop/shop/get_shop_info")
    public ResultEntity<FindShopInfoVo> findShopInfo(@SpringQueryMap ShopIdParamVo shopId);

}