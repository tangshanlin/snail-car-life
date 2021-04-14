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
 * @Date: 2021/04/09/23:53
 * @Description: 修改活动审核状态要传入的参数
 */
@Data
@ApiModel(value = "修改活动审核状态要传入的参数")
public class UpdateAdvertisingIdParamVo {

    @ApiModelProperty(value = "活动主键id")
    @NotNull(message = "活动主键id不能为空")
    @Min(value = 1,message = "活动主键id必须大于等于1")
    private Integer advertisingId;
}
