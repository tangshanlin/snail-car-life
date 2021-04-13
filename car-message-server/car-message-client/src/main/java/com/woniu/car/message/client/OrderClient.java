package com.woniu.car.message.client;

import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.message.model.param.OrderVoP;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("car-order-server")
public interface OrderClient {

    @RequestMapping(value = "/order/carservice_order/find_orderinfo_by_orderno",method = RequestMethod.GET)
    public ResultEntity findOrderInfoByOrderNo(@SpringQueryMap OrderVoP orderVo);


}
