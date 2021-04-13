package com.woniu.car.order.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName AddProductOrder
 * @Desc 生成商品订单对象
 * @Author 21174
 * @Date 2021/4/8 1:19
 * @Version 1.0
 */
@Data
public class AddProductOrderVo{
    @ApiModelProperty(value = "优惠券id")
    private Integer couponInfoId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "订单详细信息")
    private List<AddProductDetailOrderVo> productDetailOrders;

    @ApiModelProperty(value = "用户收获地址id")
    private Integer addressId;

}
