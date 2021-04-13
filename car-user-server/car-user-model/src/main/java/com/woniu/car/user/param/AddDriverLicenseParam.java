package com.woniu.car.user.param;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class AddDriverLicenseParam {


    @ApiModelProperty(value = "证件真实名")
    @NotNull
    private String userRelalName;

    @ApiModelProperty(value = "证件号码")
    @NotNull
    private Integer driverlicensePassportNo;

    @ApiModelProperty(value = "性别")
    @NotNull
    private Integer driverlicenseUserGender;

    @ApiModelProperty(value = "国籍")
    @NotNull
    private Blob driverlicenseNation;

    @ApiModelProperty(value = "住址")
    @NotNull
    private String driverlicenseAddress;

    @ApiModelProperty(value = "初次领证日期")
    @NotNull
    private Long driverlicenseCreate;

    @ApiModelProperty(value = "准驾车型")
    @NotNull
    private Blob driverlicenseCartype;

    @ApiModelProperty(value = "有效期开始时间")
    @NotNull
    private Long driverlicenseStarttime;

    @ApiModelProperty(value = "有效结束日期")
    @NotNull
    private Long driverlicenseEndtime;



}
