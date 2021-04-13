package com.woniu.car.auth.model.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 *
 * @Auther: 唐山林
 * @Date: 2021/04/13/1:44
 * @Description: 平台进行余额修改传入的参数
 */
@Data
public class BackBalanceParams {
    //
    @ApiModelProperty(value = "传入要修改的金额",example = "50")
    @NotNull(message = "参数不能为空")
    private BigDecimal backBalance;
}
