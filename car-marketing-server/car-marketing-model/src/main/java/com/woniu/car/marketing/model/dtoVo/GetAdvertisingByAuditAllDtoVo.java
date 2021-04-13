package com.woniu.car.marketing.model.dtoVo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/10/0:13
 * @Description:
 */
@Data
public class GetAdvertisingByAuditAllDtoVo {
    private Integer advertisingId;//活动主键id

    private String advertisingImage;//活动图片

    private String shopName;//门店名称

    private String couponName;//优惠券名称

    private String advertisingExplain;//活动说明

    private Long advertisingBeginTime;//活动开始时间

    private Long advertisingEndTime;//活动结束时间

}
