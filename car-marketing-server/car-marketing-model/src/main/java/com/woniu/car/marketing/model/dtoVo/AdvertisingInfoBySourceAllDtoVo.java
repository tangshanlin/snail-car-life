package com.woniu.car.marketing.model.dtoVo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/10/16:06
 * @Description: 用于前端展示已审核通过的活动信息
 */
@Data
public class AdvertisingInfoBySourceAllDtoVo {
    private String advertisingImage;//活动图片

    private Integer couponId;//关联优惠券id

    private String couponName;//优惠券名称

    private String couponInfo;//优惠券说明

    private BigDecimal couponMoney;//优惠券面额(元)

    private String advertisingExplain;//活动说明

    private Long advertisingBeginTime;//活动开始时间

    private Long advertisingEndTime;//活动结束时间
}
