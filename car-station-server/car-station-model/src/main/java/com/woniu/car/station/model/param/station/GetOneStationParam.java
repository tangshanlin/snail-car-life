package com.woniu.car.station.model.param.station;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

/**
 * @ClassName GetOneStationParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/9 16:44
 * @Version 1.0
 */
@Data
public class GetOneStationParam {
    @NotNull
    @ApiModelProperty(value = "要查询电桩的id",example = "1")
    private Integer stationId;
}
