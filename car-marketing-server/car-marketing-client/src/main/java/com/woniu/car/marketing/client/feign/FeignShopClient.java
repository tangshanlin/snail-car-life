package com.woniu.car.marketing.client.feign;

import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.shop.model.dtoVo.ShopNameDtoVo;
import com.woniu.car.shop.model.paramVo.ShopIdParamVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/10/12:04
 * @Description: feign调用门店服务
 */
@FeignClient(name = "car-shop-server")
public interface FeignShopClient {
    /*
    * @Author TangShanLin
    * @Description TODO 通过门店id查门店名称
    * @Date  15:04
    * @Param [shopIdParamVo]
    * @return com.woniu.car.commons.core.dto.ResultEntity<com.woniu.car.shop.model.dtoVo.ShopNameDtoVo>
    **/
    @GetMapping("/shop/shop/get_shop_name_by_shop_id")
    ResultEntity<ShopNameDtoVo> getShopNameByShopId(@SpringQueryMap ShopIdParamVo shopIdParamVo);
}
