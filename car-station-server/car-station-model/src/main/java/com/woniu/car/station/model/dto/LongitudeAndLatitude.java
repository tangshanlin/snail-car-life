package com.woniu.car.station.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName LongitudeAndLatitude
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/15 12:12
 * @Version 1.0
 */
@Data
public class LongitudeAndLatitude {
    @NotNull
    @ApiModelProperty(value = "精度",example = "17.1254455")
    private BigDecimal longitude;
    @NotNull
    @ApiModelProperty(value = "纬度",example = "18.56456655")
    private BigDecimal latitude;


}
