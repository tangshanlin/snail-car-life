package com.woniu.car.station.model.param.station;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName UpdateStationInfoParam
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/9 16:59
 * @Version 1.0
 */
@Data
public class UpdateStationInfoParam {

    @NotNull(message = "stationID不能为空")
    @ApiModelProperty(value = "充电桩id",example = "1")
    private Integer stationId;


    @ApiModelProperty(value = "电桩名称")
    private String stationName;


    @ApiModelProperty(value = "电桩品牌",example = "拉斯特")
    private String stationBrand;

    @ApiModelProperty(value = "电桩图片,可以为空")
    private MultipartFile[] stationImage;


    @ApiModelProperty(value = "电桩每度电的价格",example = "1.5")
    private BigDecimal stationPrice;


    @ApiModelProperty(value = "充电最小功率",example = "20")
    private String stationMinimumPower;


    @ApiModelProperty(value = "充电最大功率",example = "40")
    private String stationMaximumPower;

}
