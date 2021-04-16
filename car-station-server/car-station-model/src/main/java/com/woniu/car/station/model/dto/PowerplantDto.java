package com.woniu.car.station.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName PowerplantDto
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/16 14:26
 * @Version 1.0
 */
@Data
public class PowerplantDto {
    @ApiModelProperty(value = "电站id")
    @TableId(value = "powerplant_id", type = IdType.AUTO)
    private Integer powerplantId;

    @ApiModelProperty(value = "关联电站对应账号id")
    private Integer userId;

    @ApiModelProperty(value = "电站名称")
    private String powerplanName;

    @ApiModelProperty(value = "电站图片")
    private String powerplantImage;

    @ApiModelProperty(value = "电站介绍")
    private String powerplantIntroduce;

    @ApiModelProperty(value = "电站地址")
    private String powerplantAddress;

    @ApiModelProperty(value = "电站热线电话")
    private String powerplantPhone;

    @ApiModelProperty(value = "电站直流电电桩数量")
    private Integer powerplantCocurrentNumber;

    @ApiModelProperty(value = "电站交流电电桩数量")
    private Integer powerplantAlternatingNumber;

    @ApiModelProperty(value = "电站经纬度")
    private LongitudeAndLatitude powerplantCoordinate;

    @ApiModelProperty(value = "充电桩价格")
    private BigDecimal stationPrice;

    @ApiModelProperty(value = "电站评分")
    private String poweerplantScore;
}
