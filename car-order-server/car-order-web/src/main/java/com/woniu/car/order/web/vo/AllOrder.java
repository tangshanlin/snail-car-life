package com.woniu.car.order.web.vo;



import com.woniu.car.order.web.entity.CarserviceOrder;
import com.woniu.car.order.web.entity.PowerplantOrder;
import com.woniu.car.order.web.entity.ProductOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class AllOrder {
    public List<CarserviceOrder> CarserviceOrders;

    public List<ProductOrder> productOrders;

    public List<PowerplantOrder> powerplantOrders;

    public AllOrder(List<CarserviceOrder> carserviceOrders, List<ProductOrder> productOrders, List<PowerplantOrder> powerplantOrders) {
        CarserviceOrders = carserviceOrders;
        this.productOrders = productOrders;
        this.powerplantOrders = powerplantOrders;
    }
}
