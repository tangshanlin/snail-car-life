package com.woniu.car.station.model.param.station;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateStationTypeParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/9 17:14
 * @Version 1.0
 */
@Data
public class UpdateStationTypeParam {
    @NotNull(message = "stationId不能为空")
    @ApiModelProperty(value = "充电桩id",example = "1")
    private Integer stationId;

    @NotNull(message = "stationType不能为空")
    @ApiModelProperty(value = "电桩的充电类型: 0直流电  1交流电",example = "1")
    private Integer stationType;
}
