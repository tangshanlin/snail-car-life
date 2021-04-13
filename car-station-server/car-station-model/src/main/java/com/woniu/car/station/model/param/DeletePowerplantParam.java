package com.woniu.car.station.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName DeletePowerplantParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/9 11:52
 * @Version 1.0
 */
@Data
public class DeletePowerplantParam {
    @NotNull
    @ApiModelProperty(value = "电站id",example = "1")
    private Integer powerplantId;
}
