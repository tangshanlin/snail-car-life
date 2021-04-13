package com.woniu.car.user.param;

import lombok.Data;

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
    private Integer walletId;
    private Integer userId;

    private BigDecimal walletMoney;

    private String walletPassword;
}
