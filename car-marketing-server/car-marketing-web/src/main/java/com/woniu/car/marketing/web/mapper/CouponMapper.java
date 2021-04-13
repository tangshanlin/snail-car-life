package com.woniu.car.marketing.web.mapper;

import com.woniu.car.marketing.model.paramVo.UpdatePaySuccessCouponParamVo;
import com.woniu.car.marketing.web.domain.Coupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tsl
 * @since 2021-04-07
 */
public interface CouponMapper extends BaseMapper<Coupon> {

    /**
     * 订单支付成功后优惠券更新
     * @param updatePaySuccessCouponParamVo
     * @param now
     */
    @Update("UPDATE t_coupon c,t_coupon_info ci " +
            "SET c.coupon_use_number = c.coupon_use_number+1," +
            "c.coupon_no_use_number = c.coupon_no_use_number-1," +
            "ci.coupon_info_state=1," +
            "ci.coupon_info_use_time=#{now} " +
            "WHERE c.coupon_id = #{updatePaySuccessCouponParamVo.couponId} " +
            "AND ci.coupon_info_id = #{updatePaySuccessCouponParamVo.couponInfoId}")
    void updateCouponByPaySuccess(UpdatePaySuccessCouponParamVo updatePaySuccessCouponParamVo,Long now);
}
