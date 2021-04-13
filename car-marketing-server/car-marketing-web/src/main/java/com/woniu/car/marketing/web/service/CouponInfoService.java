package com.woniu.car.marketing.web.service;

import com.woniu.car.marketing.model.dtoVo.GetCouponInfoByIdDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetCouponInfoByUserIdDtoVo;
import com.woniu.car.marketing.model.paramVo.AddUserGetCoupon;
import com.woniu.car.marketing.model.paramVo.CouponInfoByUserIdParamVo;
import com.woniu.car.marketing.model.paramVo.GetCouponInfoByIdParamVo;
import com.woniu.car.marketing.model.paramVo.GetCouponInfoByUserIdAndSourceParamVo;
import com.woniu.car.marketing.web.domain.CouponInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tsl
 * @since 2021-04-07
 */
public interface CouponInfoService extends IService<CouponInfo> {

    List<GetCouponInfoByIdDtoVo> getCouponInfoByUserIdAll(GetCouponInfoByUserIdAndSourceParamVo getCouponInfoByUserIdAndSourceParamVo);

    GetCouponInfoByIdDtoVo getCouponInfoById(GetCouponInfoByIdParamVo getCouponInfoByIdParamVo);

    Boolean addUserGetCoupon(AddUserGetCoupon addUserGetCoupon);

    List<GetCouponInfoByUserIdDtoVo> listCouponInfoByUserId(CouponInfoByUserIdParamVo couponInfoByUserIdParamVo);
}
