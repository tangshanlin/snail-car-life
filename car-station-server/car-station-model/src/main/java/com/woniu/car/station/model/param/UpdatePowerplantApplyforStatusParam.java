package com.woniu.car.station.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdatePowerplantApplyforStatusParam {
    @NotNull(message = "powerplantApplyforId不能为空")
    @ApiModelProperty(value = "要修改审核状态的电站申请表id",example = "1")
    private Integer powerplantApplyforId;

    @NotNull(message = "powerplantApplyforStatus不能为空")
    @ApiModelProperty(value = "要修改的审核状态",example = "2")
    private Integer powerplantApplyforStatus;
}
