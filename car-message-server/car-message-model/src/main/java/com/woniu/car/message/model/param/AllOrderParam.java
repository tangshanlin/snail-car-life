package com.woniu.car.message.model.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

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

    public CarserviceOrder CarserviceOrder;

    public PowerplantOrder powerplantOrder;
}
