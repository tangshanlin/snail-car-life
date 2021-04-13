package com.woniu.car.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddressParam {
    @NotNull
    @ApiModelProperty(value ="收件人姓名" )
    private String addressContactName;
    @NotNull
    @Size(min = 11,max = 11)
    @ApiModelProperty(value ="收件人电话" )
    private String addressContactTel;
    @NotNull
    private String addressZip;
    @NotNull
    private String addressProvince;
    @NotNull
    private String addressCity;
    @NotNull
    private String addressDistrict;
    @NotNull
    private String addressStreet;
    @NotNull
    private String addressDetail;
    @NotNull
    private Integer isDefaultAddress;
}
