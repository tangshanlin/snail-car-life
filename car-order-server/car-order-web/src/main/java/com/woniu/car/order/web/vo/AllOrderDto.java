package com.woniu.car.order.web.vo;



import com.woniu.car.order.web.entity.CarserviceOrder;
import com.woniu.car.order.web.entity.PowerplantOrder;
import com.woniu.car.order.web.entity.ProductOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class AllOrderDto {
    @ApiModelProperty(value = "服务订单集合")
    public List<CarserviceOrder> carserviceOrders;

    @ApiModelProperty(value = "商品订单集合")
    public List<ProductOrder> productOrders;

    @ApiModelProperty(value = "电站订单集合")
    public List<PowerplantOrder> powerplantOrders;

    public AllOrderDto(List<CarserviceOrder> carserviceOrders, List<ProductOrder> productOrders, List<PowerplantOrder> powerplantOrders) {
        carserviceOrders = carserviceOrders;
        this.productOrders = productOrders;
        this.powerplantOrders = powerplantOrders;
    }
}
