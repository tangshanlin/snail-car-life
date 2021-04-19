package com.woniu.car.user.param;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Blob;

/**
 * @ClassName AddDriverLicenseParam
 * @Desc 新增驾驶证参数类
 * @Author Administrator
 * @Date 2021/4/9 17:52
 * @Version 1.0
 */
@Data
@ApiModel(value = "新增驾驶证输入参数")
public class AddDriverLicenseParam {


    @ApiModelProperty(value = "证件真实名")
    @NotEmpty(message = "用户的真实姓名")
    private String userRelalName;

    @ApiModelProperty(value = "证件号码",example = "2233132131")
    @NotNull(message = "证件号码不能为空")
    private Integer driverlicensePassportNo;

    @ApiModelProperty(value = "性别")
    @NotNull
    private Integer driverlicenseUserGender;

    @ApiModelProperty(value = "国籍")
    @NotEmpty(message = "住址不能为空")
    private String driverlicenseNation;
    @ApiModelProperty(value = "住址")
    @NotEmpty
    private String driverlicenseAddress;

    @ApiModelProperty(value = "初次领证日期")
    @NotEmpty
    private Long driverlicenseCreate;

    @ApiModelProperty(value = "准驾车型")
    @NotEmpty
    private String driverlicenseCartype;

    @ApiModelProperty(value = "有效期开始时间")
    @NotEmpty
    private Long driverlicenseStarttime;

    @ApiModelProperty(value = "有效结束日期")
    @NotEmpty
    private Long driverlicenseEndtime;



}
