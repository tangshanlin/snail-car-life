package com.woniu.car.station.model.param.station;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

/**
 * @ClassName DeleteStationParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/9 17:20
 * @Version 1.0
 */
@Data
public class DeleteStationParam {
    @NotNull(message = "stationId不能为空")
    @ApiModelProperty(value = "充电桩id",example = "1")
    private Integer stationId;
}
