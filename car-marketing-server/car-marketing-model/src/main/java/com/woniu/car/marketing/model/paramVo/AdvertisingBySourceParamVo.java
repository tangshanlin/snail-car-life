package com.woniu.car.marketing.model.paramVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/10/16:03
 * @Description: 前端传入活动来源id
 */
@Data
@ApiModel(value = "根据活动来源id展示活动信息")
public class AdvertisingBySourceParamVo {

    @ApiModelProperty(value = "发行来源(0平台-其他对应门店id)")
    @Min(value = 0,message = "发布活动的来源id必须大于等于0")
    @NotNull(message = "发行来源不能为空")
    private Integer advertisingSourceId;//发布来源(0平台其他对应门店对应id)
}
