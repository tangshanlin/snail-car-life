package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.apache.logging.log4j.message.Message;

import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "行驶证编号不能为空")
    @ApiModelProperty(value = "行驶证编号")
    private String drivinglicenseCode;

    @ApiModelProperty(value = "住址")
    @NotEmpty(message = "住址不能为空")
    private String drivinglicenseAddress;

    @ApiModelProperty(value = "发证日期")
    @NotEmpty(message = "发证日期不能为空")
    private Long drivinglicenseDate;

    @ApiModelProperty(value = "品牌型号")
    @NotEmpty(message = "品牌型号不能为空")
    private String drivinglicenseBrand;

    @ApiModelProperty(value = "车辆类型")
    @NotEmpty(message = "车辆类型不能为空")
    private String drivinglicenseCartype;

    @ApiModelProperty(value = "持证人")
    @NotEmpty(message = "持证人不能为空")
    private String userRealName;

    @ApiModelProperty(value = "使用性质")
    @NotNull(message = "使用性质不能为空")
    private Integer drivinglicenseType;

    @ApiModelProperty(value = "发动机号码")
    @NotEmpty(message = "发动机号不能为空")
    private String engineNumber;

    @ApiModelProperty(value = "车牌号码")
    @NotEmpty(message = "车牌号不能为空")
    private String carCode;

    @ApiModelProperty(value = "注册日期")
    @NotEmpty(message = "注册日期不能为空")
    private Long registerDate;



}
