package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "地址的参数")
public class AddressParam {
    @NotEmpty(message = "收件人姓名不能为空")
    @ApiModelProperty(value ="收件人姓名" )
    private String addressContactName;

    @ApiModelProperty(value ="收件人电话" )
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")
    @NotEmpty(message = "手机号码不能为空")
    private String addressContactTel;
    @NotEmpty(message = "邮编不能为空")
    private String addressZip;
    @NotEmpty(message = "省份不能为空")
    private String addressProvince;
    @NotEmpty(message = "城市不能为空")
    private String addressCity;
    @NotEmpty(message = "地区不能为空")
    private String addressDistrict;
    @NotEmpty(message = "街道不能为空")
    private String addressStreet;
    @NotEmpty(message = "详细地址不能为空")
    private String addressDetail;
    @NotEmpty(message = "默认地址不能为空")
    private Integer isDefaultAddress;
}
