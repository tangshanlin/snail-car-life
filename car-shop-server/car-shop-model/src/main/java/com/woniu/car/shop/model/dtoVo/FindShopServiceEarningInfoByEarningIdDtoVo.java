package com.woniu.car.shop.model.dtoVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/17/10:30
 * @Description:
 */
@Data
public class FindShopServiceEarningInfoByEarningIdDtoVo {

    private Integer shopEarningsInfoId;//门店服务收益id

    private Integer shopServiceEarningsId;//关联门店服务收益表

    private String userAccount;//用户账号

    private Long payTime;//付款时间

    private BigDecimal carServicePrice;//该次服务门店收益

    private BigDecimal shopServiceInfoPlatformMoney;//该次服务平台抽成金额
}
