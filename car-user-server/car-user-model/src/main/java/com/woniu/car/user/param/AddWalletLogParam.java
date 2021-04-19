package com.woniu.car.user.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName AddWalletLogParam
 * @Desc 增加钱包日志的参数
 * @Author Administrator
 * @Date 2021/4/9 12:19
 * @Version 1.0
 */
@Data
@ApiModel(value = "添加钱包消费日志的参数")
public class AddWalletLogParam {
    @ApiModelProperty(value = "钱包变化金额")
    @NotEmpty(message = "钱包变化金额不能为空")
    private BigDecimal walletChange;
    @ApiModelProperty(value = "钱包密码")
    @NotEmpty(message = "钱包密码不能为空")
    private String walletPassword;



    //变化事件
    @ApiModelProperty(value = "钱包变化事件")
    @NotNull
    private String walletlogEvent;



    //"类型(1充值2消费3退款4提现)"
    @ApiModelProperty(value = "变化类型（1充值2消费3退款4提现）")
    @NotNull
    private Integer walletlogType;
}
