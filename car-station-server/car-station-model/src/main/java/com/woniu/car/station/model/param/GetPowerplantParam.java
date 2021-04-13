package com.woniu.car.station.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName GetPowerplantParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/9 11:30
 * @Version 1.0
 */
@Data
public class GetPowerplantParam {
    @NotNull
    @ApiModelProperty(value = "接收要查询的电站id",example = "1")
    private Integer powerplantId;
}
