package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "删除钱包的参数")
public class DeleteWalletParam {
    @ApiModelProperty(value = "用户id",example = "1")
    @NotNull(message = "用户id不能为空")
    private Integer userId;
    @ApiModelProperty(value = "钱包id",example = "1")
    @NotNull(message = "钱包id不能为空")
    private Integer walletId;
}
