package com.woniu.car.marketing.client.feign;

import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.marketing.model.dtoVo.GetCouponBySourceDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetCouponNameDtoVo;
import com.woniu.car.marketing.model.paramVo.AddUserGetCoupon;
import com.woniu.car.marketing.model.paramVo.GetCouponIdParamVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/10/14:55
 * @Description: feign调用促销券服务
 */
@FeignClient(name = "car-marketing-server")
public interface FeignMarketingClient {
    /*
    * @Author TangShanLin
    * @Description TODO 通过优惠券类别id查优惠券名称
    * @Date  15:02
    * @Param [addUserGetCoupon]
    * @return com.woniu.car.commons.core.dto.ResultEntity
    **/
    @GetMapping("/marketing/coupon/get_coupon_name_by_coupon_id")
    ResultEntity<GetCouponNameDtoVo> getCouponNameByCouponId(@SpringQueryMap GetCouponIdParamVo getCouponIdParamVo);

    /*
    * @Author TangShanLin
    * @Description TODO 通过优惠券类别id查询优惠券信息
    * @Date  17:11
    * @Param [getCouponIdParamVo]
    * @return com.woniu.car.commons.core.dto.ResultEntity<com.woniu.car.marketing.model.dtoVo.GetCouponBySourceDtoVo>
    **/
    @GetMapping("/marketing/coupon/get_coupon_by_id")
    ResultEntity<GetCouponBySourceDtoVo> getCouponById(@SpringQueryMap GetCouponIdParamVo getCouponIdParamVo);
}
