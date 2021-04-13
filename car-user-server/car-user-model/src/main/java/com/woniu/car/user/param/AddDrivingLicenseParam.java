package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName AddDrivingLicenseParam
 * @Desc TODO
 * @Author Administrator
 * @Date 2021/4/10 9:59
 * @Version 1.0
 */
@Data
@ApiModel(value = "添加行驶证参数")
public class AddDrivingLicenseParam {
    @NotNull
    @ApiModelProperty(value = "行驶证编号")
    private String drivinglicenseCode;

    @ApiModelProperty(value = "住址")
    private String drivinglicenseAddress;

    @ApiModelProperty(value = "发证日期")
    private Long drivinglicenseDate;

    @ApiModelProperty(value = "品牌型号")
    private String drivinglicenseBrand;

    @ApiModelProperty(value = "车辆类型")
    private String drivinglicenseCartype;

    @ApiModelProperty(value = "持证人")
    private String userRealName;

    @ApiModelProperty(value = "使用性质")
    private Integer drivinglicenseType;

    @ApiModelProperty(value = "发动机号码")
    private String engineNumber;

    @ApiModelProperty(value = "车牌号码")
    private String carCode;

    @ApiModelProperty(value = "注册日期")
    private Long registerDate;



}
