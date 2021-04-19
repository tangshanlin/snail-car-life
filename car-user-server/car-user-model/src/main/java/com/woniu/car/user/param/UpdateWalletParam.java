package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
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
@ApiModel(value = "钱包密码更新的参数")
public class UpdateWalletParam {


    @ApiModelProperty(value = "钱包旧密码")
    @NotEmpty(message = "钱包旧密码不能为空")
    private String walletOldPassword;
    @ApiModelProperty(value = "钱包新密码")
    @NotEmpty(message = "钱包新密码不能为空")
    private String walletNewPassword;
}
