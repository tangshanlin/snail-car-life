package com.woniu.car.marketing.model.paramVo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/09/14:13
 * @Description:
 */
@Data
public class AddAdvertising {
    private MultipartFile[] file;//活动图片

    private Integer advertisingSourceId;//发布来源(0平台其他对应门店对应id)

    private Integer couponId;//关联优惠券id

    private String advertisingExplain;//活动说明

    private String advertisingBeginTime;//活动开始时间

    private String advertisingEndTime;//活动结束时间
}
