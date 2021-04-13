package com.woniu.car.order.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.car.order.model.param.ExpressNoParams;
import com.woniu.car.order.web.entity.OrderLogistics;

/**
 * <p>
 * 商品订单物流表 服务类
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
public interface OrderLogisticsService extends IService<OrderLogistics> {

    /*
     * @Author WangPeng
     * @Description //新增物流信息
     * @Date  2021/4/8
     * @Param [orderLogistics]
     * @return java.lang.Boolean
     **/
    public Boolean insertOrderLogistics(OrderLogistics orderLogistics);
    
    /**
     * @Author WangPeng
     * @Description TODO 根据订单id修改快递单号信息
     * @Date  10:45
     * @Param [expressNo]
     * @return java.lang.Boolean
     **/
    public Boolean updateProductOrderExpressNo(ExpressNoParams expressNoParams);
    
    
}
