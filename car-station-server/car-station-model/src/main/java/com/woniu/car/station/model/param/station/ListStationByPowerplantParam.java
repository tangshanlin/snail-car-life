package com.woniu.car.station.model.param.station;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

/**
 * @ClassName ListStationByPowerplantParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/12 14:18
 * @Version 1.0
 */
@Data
public class ListStationByPowerplantParam {
    @NotNull
    @ApiModelProperty(value = "关联电站id",example = "1")
    private Integer powerplantId;
}
