package com.woniu.car.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName SelectAddressParam
 * @Desc TODO
 * @Author Administrator
 * @Date 2021/4/9 9:23
 * @Version 1.0
 */
@Data
public class SelectAddressParam {
    @ApiModelProperty(value = "用户帐户名")
    private String userAccount;
}
