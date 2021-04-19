package com.woniu.car.shop.model.dtoVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/17/10:16
 * @Description:
 */
@Data
public class FindShopServiceEarningAllByShopIdDtoVo {
    private Integer shopServiceEarningsId;//门店服务类别收益id

    private Integer shopId;//关联门店id

    private String carServiceName;//服务名称

    private BigDecimal shopServiceEarningsMoney;//该服务门店收益总金额

    private BigDecimal shopServicePlatformMoney;//该服务平台抽成总金额
}
