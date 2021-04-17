package com.woniu.car.station.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetOnePowerplantApplyforParam {
    @NotNull(message = "powerplantApplyforId不能为空")
    @ApiModelProperty(value = "需要查询具体电站申请表的id",example = "1")
    private Integer powerplantApplyforId;
}
