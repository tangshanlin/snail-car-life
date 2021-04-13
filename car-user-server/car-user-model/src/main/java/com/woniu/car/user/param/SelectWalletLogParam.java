package com.woniu.car.user.param;

import lombok.Data;

/**
 * @ClassName SelectWalletLogParam
 * @Desc 查询钱包日志的参数
 * @Author Administrator
 * @Date 2021/4/9 15:02
 * @Version 1.0
 */
@Data
public class SelectWalletLogParam {
    private Integer userId;
    private Integer walletId;
}
