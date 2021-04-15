package com.woniu.car.order.client.feign;

import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.marketing.model.dtoVo.GetCouponInfoByIdDtoVo;
import com.woniu.car.marketing.model.paramVo.GetCouponInfoByIdParamVo;
import com.woniu.car.marketing.model.paramVo.UpdatePaySuccessCouponParamVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName ShopClient
 * @Desc TODO 促销模块feign接口
 * @Author 21174
 * @Date 2021/4/9 10:37
 * @Version 1.0
 */
@FeignClient("car-marketing-server")
public interface MarketingClient {

    /*
     * @Author WangPeng
     * @Description TODO 查询所有未失效优惠券类别信息接口
     * @Date  2021/4/9
     * @Param [getCouponInfoByIdParamVo]
     * @return com.woniu.car.commons.core.dto.ResultEntity<com.woniu.car.marketing.model.dtoVo.GetCouponInfoByIdDtoVo>
     **/

    @GetMapping("/marketing/coupon-info/get_coupon_info_by_id")
    public ResultEntity<GetCouponInfoByIdDtoVo> getCouponInfoById(@SpringQueryMap GetCouponInfoByIdParamVo getCouponInfoByIdParamVo);

    /*
     * @Author WangPeng
     * @Description TODO 根据优惠券详情id修改优惠券状态
     * @Date  2021/4/9
     * @Param [updatePaySuccessCouponParamVo]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PutMapping("/marketing/coupon/update_coupon_by_pay_success")
    public ResultEntity updateCouponByPaySuccess(@RequestBody UpdatePaySuccessCouponParamVo updatePaySuccessCouponParamVo);

}
