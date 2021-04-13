package com.woniu.car.order.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.car.order.web.entity.OrderInvoice;

/**
 * <p>
 * 订单发票表 服务类
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
public interface OrderInvoiceService extends IService<OrderInvoice> {

    /**
     * @Author WangPeng
     * @Description //新增发票信息
     * @Date  2021/4/8
     * @Param [orderInvoice]
     * @return java.lang.Boolean
     **/
    public Boolean insertOrderInvoice(OrderInvoice orderInvoice);

}
