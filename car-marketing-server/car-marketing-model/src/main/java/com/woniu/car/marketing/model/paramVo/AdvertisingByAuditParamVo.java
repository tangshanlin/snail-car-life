package com.woniu.car.marketing.model.paramVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/10/11:47
 * @Description: 传入审核状态参数查活动信息
 */
@Data
@ApiModel(value = "根据审核状态参数查活动信息")
public class AdvertisingByAuditParamVo {

    @ApiModelProperty(value = "审核状态（0未审核1已审核2拒绝）",example = "1")
    @NotNull(message = "审核状态不能为空")
    @Size(max=0,min=2,message = "审核状态必须在0-2之间")
    private Integer advertisingAudit;//审核状态（0未审核1已审核2拒绝）
}
