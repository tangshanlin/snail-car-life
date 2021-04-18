package com.woniu.car.shop.client.feign;

import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.marketing.model.dtoVo.GetCouponSourceAndMoneyByIdDtoVo;
import com.woniu.car.marketing.model.paramVo.GetCouponIdParamVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/17/1:35
 * @Description:
 */
@FeignClient("car-marketing-server")
public interface FeignMarketingClient {

    @GetMapping("marketing/coupon/get_coupon_source_and_goods_by_id")
    ResultEntity<GetCouponSourceAndMoneyByIdDtoVo> getCouponSourceAndMoneyByIdDtoVoResultEntity(@SpringQueryMap GetCouponIdParamVo getCouponIdParamVo);
}
