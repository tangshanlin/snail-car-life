package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateAddress
 * @Desc 修改地址的输入参数类
 * @Author Administrator
 * @Date 2021/4/9 9:52
 * @Version 1.0
 */
@Data
@ApiModel(value = "地址更新的参数")
public class UpdateAddress {

    @ApiModelProperty(value ="地址Id" )
    @NotNull
    private Integer addressId;
    @ApiModelProperty(value = "联系人姓名")
    @NotNull
    private String addressContactName;
    @ApiModelProperty(value = "联系人电话")
    @NotNull
    private String addressContactTel;
    @ApiModelProperty(value = "邮编")
    @NotNull
    private String addressZip;
    @ApiModelProperty(value = "省")
    @NotNull
    private String addressProvince;
    @ApiModelProperty(value = "城市")
    @NotNull
    private String addressCity;
    @ApiModelProperty(value = "区域")
    @NotNull
    private String addressDistrict;
    @ApiModelProperty(value = "街道")
    @NotNull
    private String addressStreet;
    @ApiModelProperty(value = "详细地址")
    @NotNull
    private String addressDetail;
    @ApiModelProperty(value = "是否是默认地址（1为默认地址，0为普通地址）")
    @NotNull
    private Integer isDefaultAddress;
}
