package com.woniu.car.auth.model.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/13/1:47
 * @Description: 平台余额修改后的参数
 */
@Data
public class BackBalanceDto {
    private BigDecimal backBalance;//返回修改后的金额
}
