package com.woniu.car.message.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.car.message.model.param.OrderComplainsParam;
import com.woniu.car.message.web.domain.OrderComplains;

/**
 * <p>
 *  订单投诉 服务类
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
public interface OrderComplainsService extends IService<OrderComplains> {

    Integer addOrderComplains(OrderComplainsParam orderComplainsParam);






}
