package com.woniu.car.station.model.param.station;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName AddStationParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/9 16:10
 * @Version 1.0
 */
@Data
public class AddStationParam {
    @NotNull(message = "powerplantId不能为空")
    @ApiModelProperty(value = "关联电站id",example = "1")
    private Integer powerplantId;

    @NotNull(message = "stationName不能为空")
    @ApiModelProperty(value = "电桩名称")
    private String stationName;

    @NotNull(message = "stationBrand不能为空")
    @ApiModelProperty(value = "电桩品牌",example = "特斯拉")
    private String stationBrand;

    @NotNull(message = "stationImage不能为空")
    @ApiModelProperty(value = "电桩图片")
    private String stationImage;

    @NotNull(message = "stationPrice不能为空")
    @ApiModelProperty(value = "电桩每度电的价格",example = "1.7")
    private BigDecimal stationPrice;

    @NotNull(message = "stationType不能为空")
    @ApiModelProperty(value = "电桩的充电类型: 0直流电  1交流电",example = "0")
    private Integer stationType;

    @NotNull(message = "stationStatus不能为空")
    @ApiModelProperty(value = "电桩状态0空闲 1充电中",example = "0")
    private Integer stationStatus;

    @NotNull(message = "stationMinimumPower不能为空")
    @ApiModelProperty(value = "充电最小功率",example = "25")
    private String stationMinimumPower;

    @NotNull(message = "stationMaximumPower不能为空")
    @ApiModelProperty(value = "充电最大功率",example = "50")
    private String stationMaximumPower;

}
