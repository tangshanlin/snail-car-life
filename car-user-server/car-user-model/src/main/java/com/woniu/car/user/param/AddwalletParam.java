package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName AddwalletParam
 * @Desc TODO
 * @Author Administrator
 * @Date 2021/4/9 10:21
 * @Version 1.0
 */
@Data
@ApiModel(value = "添加钱包的参数")
public class AddwalletParam {

    @ApiModelProperty("钱包余额")
    @NotEmpty(message = "钱包余额不能为空")
    private BigDecimal walletMoney;
    @ApiModelProperty("钱包密码")
    @NotEmpty(message = "钱包密码不能为空")
    private String walletPassword;


}
