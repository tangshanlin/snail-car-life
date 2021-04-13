package com.woniu.car.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName DeleteWalletParam
 * @Desc 删除钱包的参数类
 * @Author Administrator
 * @Date 2021/4/9 11:20
 * @Version 1.0
 */
@Data
public class DeleteWalletParam {
    @ApiModelProperty(value = "用户id")
    @NotNull
    private Integer userId;
    @ApiModelProperty(value = "钱包id")
    @NotNull
    private Integer walletId;
}
