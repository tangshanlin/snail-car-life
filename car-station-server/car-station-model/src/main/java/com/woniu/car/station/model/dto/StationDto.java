package com.woniu.car.station.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

/**
 * @ClassName StationDto
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/9 14:14
 * @Version 1.0
 */
@Data
public class StationDto {
    @ApiModelProperty(value = "关联电站id")
    private Integer powerplantId;

    @ApiModelProperty(value = "电桩品牌")
    private String stationBrand;

    @ApiModelProperty(value = "电桩图片")
    private MultipartFile[] stationImage;

    @ApiModelProperty(value = "电桩每度电的价格")
    private BigDecimal stationPrice;

    @ApiModelProperty(value = "电桩的充电类型: 0直流电  1交流电")
    private Integer stationType;

    @ApiModelProperty(value = "电桩状态0空闲 1充电中")
    private Integer stationStatus;

    @ApiModelProperty(value = "充电最小功率")
    private String stationMinimumPower;

    @ApiModelProperty(value = "充电最大功率")
    private String stationMaximumPower;


}
