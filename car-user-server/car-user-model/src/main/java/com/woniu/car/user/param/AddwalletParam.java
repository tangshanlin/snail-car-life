package com.woniu.car.user.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @ClassName AddwalletParam
 * @Desc TODO
 * @Author Administrator
 * @Date 2021/4/9 10:21
 * @Version 1.0
 */
@Data

public class AddwalletParam {
    private Integer userId;

    private BigDecimal walletMoney;

    private String walletPassword;


}
