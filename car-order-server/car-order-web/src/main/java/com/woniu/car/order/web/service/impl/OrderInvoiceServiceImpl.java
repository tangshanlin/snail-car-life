package com.woniu.car.order.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.order.web.entity.OrderInvoice;
import com.woniu.car.order.web.mapper.OrderInvoiceMapper;
import com.woniu.car.order.web.service.OrderInvoiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 订单发票表 服务实现类
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
@Service("orderInvoiceService")
public class OrderInvoiceServiceImpl extends ServiceImpl<OrderInvoiceMapper, OrderInvoice> implements OrderInvoiceService {

    @Resource
    private OrderInvoiceMapper orderInvoiceMapper;

    /**
     * @Author WangPeng
     * @Description //新增发票信息
     * @Date  2021/4/8
     * @Param [orderInvoice]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean insertOrderInvoice(OrderInvoice orderInvoice) {
        int row = orderInvoiceMapper.insert(orderInvoice);
        return row==1?true:false;
    }
}
