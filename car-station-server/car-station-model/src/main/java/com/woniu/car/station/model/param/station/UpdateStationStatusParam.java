package com.woniu.car.station.model.param.station;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateStationStatusParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/9 17:09
 * @Version 1.0
 */
@Data
public class UpdateStationStatusParam {
    @NotNull
    @ApiModelProperty(value = "充电桩id",example = "1")
    private Integer stationId;

    @NotNull
    @ApiModelProperty(value = "电桩状态0空闲 1充电中",example = "1")
    private Integer stationStatus;
}
