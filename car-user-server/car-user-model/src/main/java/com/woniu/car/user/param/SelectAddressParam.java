package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName SelectAddressParam
 * @Desc TODO
 * @Author Administrator
 * @Date 2021/4/9 9:23
 * @Version 1.0
 */
@Data
@ApiModel(value = "查询账户地址的参数")
public class SelectAddressParam {
    @ApiModelProperty(value = "用户帐户名")
    @NotNull
    private String userAccount;
}
