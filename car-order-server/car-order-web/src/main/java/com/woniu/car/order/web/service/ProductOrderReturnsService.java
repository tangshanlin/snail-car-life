package com.woniu.car.order.web.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.car.order.web.entity.ProductOrderReturns;

import java.util.List;

/**
 * <p>
 * 商品退货表 服务类
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
public interface ProductOrderReturnsService extends IService<ProductOrderReturns> {

    /**
     * @Author WangPeng
     * @Description //根据商userId查询详细信息
     * @Date  2021/4/8
     * @Param [productOrderReturns]
     * @return java.util.List<com.woniu.car.order.web.entity.ProductOrderReturns>
     **/
    public List<ProductOrderReturns> findProductOrderReturnsByUserId(ProductOrderReturns productOrderReturns);


}
