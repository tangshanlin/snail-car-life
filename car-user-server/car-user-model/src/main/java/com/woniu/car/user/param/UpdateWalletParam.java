package com.woniu.car.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName UpdateWalletParam
 * @Desc TODO
 * @Author Administrator
 * @Date 2021/4/9 11:40
 * @Version 1.0
 */
@Data
public class UpdateWalletParam {


    @ApiModelProperty(value = "钱包密码")
    private String walletPassword;
}
