package com.woniu.car.shop.model.paramVo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 唐山林
 * @Date: 2021/04/12/17:02
 * @Description: 门店新填服务时生成门店服务类别收益数据
 */
@Data
public class AddShopServiceEarningsParamVo {
    private Integer shopId;//关联门店id

    private String carServiceName;//服务名称
}
