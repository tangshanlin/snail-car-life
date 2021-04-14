package com.woniu.car.marketing.web.mapper;

import com.woniu.car.marketing.model.dtoVo.GetCouponInfoByIdDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetCouponInfoByUserIdDtoVo;
import com.woniu.car.marketing.model.paramVo.GetCouponInfoByUserIdAndSourceParamVo;
import com.woniu.car.marketing.web.domain.CouponInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tsl
 * @since 2021-04-07
 */
public interface CouponInfoMapper extends BaseMapper<CouponInfo> {

    /*
    * @Author TangShanLin
    * @Description TODO 通过用户领取优惠券表主键id查询优惠券信息
    * @Date  9:51
    * @Param [couponInfoId]
    * @return com.woniu.car.marketing.model.dtoVo.GetCouponInfoByIdDtoVo
    **/
    @Select("SELECT ci.coupon_info_id,c.coupon_id,c.coupon_name,c.coupon_money,c.coupon_condition " +
            "FROM t_coupon c " +
            "JOIN t_coupon_info ci " +
            "ON c.coupon_id = ci.coupon_id " +
            "WHERE ci.coupon_info_id = #{couponInfoId}")
    GetCouponInfoByIdDtoVo getCouponInfoById(Integer couponInfoId);


    /*
    * @Author TangShanLin
    * @Description TODO 查询某订单下用户可使用的优惠券
    * @Date  9:51
    * @Param [getCouponInfoByUserIdAndSourceParamVo, nowTime]
    * @return java.util.List<com.woniu.car.marketing.model.dtoVo.GetCouponInfoByIdDtoVo>
    **/
    @Select("SELECT ci.coupon_info_id,c.coupon_id,c.coupon_name,c.coupon_money,c.coupon_condition " +
            "FROM t_coupon c " +
            "JOIN t_coupon_info ci " +
            "ON c.coupon_id = ci.coupon_id " +
            "WHERE c.coupon_goods = 0 " +
            "OR c.coupon_goods = #{getCouponInfoByUserIdAndSourceParamVo.couponGoods} " +
            "AND ci.coupon_info_user_id = #{userId} " +
            "AND c.coupon_begin_time < #{nowTime} " +
            "AND c.coupon_end_time > #{nowTime}")
    List<GetCouponInfoByIdDtoVo> getCouponInfoByUserIdAll(GetCouponInfoByUserIdAndSourceParamVo getCouponInfoByUserIdAndSourceParamVo,Integer userId,Long nowTime);

    /*
    * @Author TangShanLin
    * @Description TODO 根据用户id查询未过期未使用的优惠券信息
    * @Date  12:15
    * @Param [couponInfoUserId, now]
    * @return java.util.List<com.woniu.car.marketing.model.dtoVo.GetCouponInfoByUserIdDtoVo>
    **/
    @Select("SELECT c.coupon_name,c.coupon_info,c.coupon_money,coupon_condition\n" +
            "FROM t_coupon c\n" +
            "JOIN t_coupon_info ci\n" +
            "ON c.coupon_id = ci.coupon_id\n" +
            "WHERE ci.coupon_info_user_id = #{now}\n" +
            "AND c.coupon_end_time > #{couponInfoUserId}\n" +
            "AND ci.coupon_info_state = 0")
    List<GetCouponInfoByUserIdDtoVo> listCouponInfoByUserId(Integer couponInfoUserId, Long now);
}
