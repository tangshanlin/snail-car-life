package com.woniu.car.items.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName CarServiceImagsDto
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/16 17:10
 * @Version 1.0
 */
@Data
public class CarServiceImagsDto {
    @ApiModelProperty(value = "id")
    private Integer carServiceId;

    @ApiModelProperty(value = "关联二级分类id")
    private Integer twoClassifyId;

    @ApiModelProperty(value = "关联门店id")
    private Integer shopId;

    @ApiModelProperty(value = "服务名称")
    private String carServiceName;

    @ApiModelProperty(value = "服务价格")
    private BigDecimal carServicePrice;

    @ApiModelProperty(value = "服务图片")
    private String carServiceImage;

    @ApiModelProperty(value = "服务介绍")
    private String carServiceIntroduce;

    @ApiModelProperty(value = "服务详情")
    private List<String> carServiceInfo;

    @ApiModelProperty(value = "使用车型")
    private String carServiceType;

    @ApiModelProperty(value = "服务状态 0待上架 1已上架 2已下架")
    private Integer carServiceStatus;

    @ApiModelProperty(value = "已售数量")
    private Integer carServiceSold;
}
