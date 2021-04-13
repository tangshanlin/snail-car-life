package com.woniu.car.user.param;

import lombok.Data;

/**
 * @ClassName DeleteWalletParam
 * @Desc 删除钱包的参数类
 * @Author Administrator
 * @Date 2021/4/9 11:20
 * @Version 1.0
 */
@Data
public class DeleteWalletParam {
    private Integer userId;
    private Integer walletId;
}
