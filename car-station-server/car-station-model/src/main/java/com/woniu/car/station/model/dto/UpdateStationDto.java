package com.woniu.car.station.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

/**
 * @ClassName UpdateStationDto
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/9 14:37
 * @Version 1.0
 */
@Data
public class UpdateStationDto {

    @ApiModelProperty(value = "充电桩id")
    private Integer stationId;

    @ApiModelProperty(value = "电桩编号")
    private String stationNumeration;

    @ApiModelProperty(value = "电桩品牌")
    private String stationBrand;

    @ApiModelProperty(value = "电桩图片")
    private MultipartFile[] stationImage;

    @ApiModelProperty(value = "电桩每度电的价格")
    private BigDecimal stationPrice;

    @ApiModelProperty(value = "充电最小功率")
    private String stationMinimumPower;

    @ApiModelProperty(value = "充电最大功率")
    private String stationMaximumPower;

    @ApiModelProperty(value = "电桩二维码信息")
    private String stationCode;
}
