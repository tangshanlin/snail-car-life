package com.woniu.car.user.param;

import lombok.Data;

/**
 * @ClassName SelectWalletParam
 * @Desc 查询钱包的参数类
 * @Author Administrator
 * @Date 2021/4/9 12:01
 * @Version 1.0
 */
@Data
public class SelectWalletParam {
    private Integer userId;
    private Integer walletId;

}
