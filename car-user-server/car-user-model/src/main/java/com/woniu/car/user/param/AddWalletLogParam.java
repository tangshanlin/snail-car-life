package com.woniu.car.user.param;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName AddWalletLogParam
 * @Desc 增加钱包日志的参数
 * @Author Administrator
 * @Date 2021/4/9 12:19
 * @Version 1.0
 */
@Data
public class AddWalletLogParam {


    private Integer walletId;


    private BigDecimal walletChange;



    //变化事件
    private String walletlogEvent;


    private Integer userId;

    //"类型(1充值2消费3退款4提现)"
    private Integer walletlogType;
}
