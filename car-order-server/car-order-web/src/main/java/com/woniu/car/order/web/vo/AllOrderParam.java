package com.woniu.car.order.web.vo;

import com.woniu.car.order.web.entity.CarserviceOrder;
import com.woniu.car.order.web.entity.PowerplantOrder;
import com.woniu.car.order.web.entity.ProductOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName AllOrderParam
 * @Desc TODO
 * @Author 21174
 * @Date 2021/4/12 15:31
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class AllOrderParam {

    @ApiModelProperty(value = "服务订单集合")
    public CarserviceOrder carserviceOrder;

    @ApiModelProperty(value = "商品订单集合")
    public ProductOrder productOrder;

    @ApiModelProperty(value = "订单订单集合")
    public PowerplantOrder powerplantOrder;
}
