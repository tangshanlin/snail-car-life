package com.woniu.car.station.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeletePowerplantApplyforParam {
    @NotNull
    @ApiModelProperty(value = "要删除电站申请表的id",example = "1")
    private Integer powerplantApplyforId;
}
