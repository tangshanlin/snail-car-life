package com.woniu.car.marketing.web.service;

import com.woniu.car.marketing.model.dtoVo.GetCouponAllDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetCouponBySourceDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetCouponNameDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetCouponSourceAndMoneyByIdDtoVo;
import com.woniu.car.marketing.model.paramVo.AddCouponParamVo;
import com.woniu.car.marketing.model.paramVo.GetCouponBySourceParamVo;
import com.woniu.car.marketing.model.paramVo.GetCouponIdParamVo;
import com.woniu.car.marketing.model.paramVo.UpdatePaySuccessCouponParamVo;
import com.woniu.car.marketing.web.domain.Coupon;
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
public interface CouponService extends IService<Coupon> {

    void addCoupon(AddCouponParamVo addCouponParamVo);

    List<GetCouponAllDtoVo> listCouponInfoAll();

    void updateCouponByPaySuccess(UpdatePaySuccessCouponParamVo updatePaySuccessCouponParamVo);

    List<GetCouponBySourceDtoVo> getCouponBySource(GetCouponBySourceParamVo getCouponBySourceParamVo);

    GetCouponNameDtoVo getCouponNameByCouponId(GetCouponIdParamVo getCouponIdParamVo);

    GetCouponBySourceDtoVo getCouponById(GetCouponIdParamVo getCouponIdParamVo);

    GetCouponSourceAndMoneyByIdDtoVo getCouponSourceAndMoneyByIdDtoVoResultEntity(GetCouponIdParamVo getCouponIdParamVo);

}
