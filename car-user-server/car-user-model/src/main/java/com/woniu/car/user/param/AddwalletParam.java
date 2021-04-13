package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    private BigDecimal walletMoney;
    @ApiModelProperty("钱包密码")
    private String walletPassword;


}
