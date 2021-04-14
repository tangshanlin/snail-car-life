package com.woniu.car.order.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.car.order.web.entity.ProductOrderDetail;

import java.util.List;

/**
 * <p>
 * 商品详情表 服务类
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
public interface ProductOrderDetailService extends IService<ProductOrderDetail> {

    /**
     * @Author WangPeng
     * @Description //根据商品订单编号查询订单详细信息
     * @Date  2021/4/8
     * @Param [ProductOrderDetail]
     * @return java.util.List<com.woniu.car.order.web.entity.ProductOrderDetail>
     **/
    public List<ProductOrderDetail> findProductOrderDerailByProductOrderNo(String productOrderNo);

    /**
     * @Author WangPeng
     * @Description TODO 生成商品订单
     * @Date  2021/4/8
     * @Param [addProductOrderVo]
     * @return java.lang.Boolean
     **/
    public Boolean insertProductOrderDetail(ProductOrderDetail productOrderDetail);
}
