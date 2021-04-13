package com.woniu.car.order.web.vo;

import com.woniu.car.order.web.entity.CarserviceOrder;
import com.woniu.car.order.web.entity.PowerplantOrder;
import com.woniu.car.order.web.entity.ProductOrder;
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

    public CarserviceOrder carserviceOrder;

    public ProductOrder productOrder;

    public PowerplantOrder powerplantOrder;
}
