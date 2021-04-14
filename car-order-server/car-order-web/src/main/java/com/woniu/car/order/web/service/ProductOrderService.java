package com.woniu.car.order.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.car.order.model.param.OrderVo;
import com.woniu.car.order.model.param.UserVo;
import com.woniu.car.order.web.entity.ProductOrder;

import java.util.List;

/**
 * <p>
 * 商品订单表 服务类
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
public interface ProductOrderService extends IService<ProductOrder> {

    /**
     * @Author WangPeng
     * @Description //根据商品订单id查询商品订单信息
     * @Date  2021/4/8
     * @Param [productOrder]
     * @return com.woniu.car.order.web.entity.ProductOrder
     **/
    public ProductOrder findProductOrderByOrderId(ProductOrder productOrder);


    /**
     * @Author WangPeng
     * @Description //根据商品订单编号查询订单信息
     * @Date  2021/4/8
     * @Param [order]
     * @return com.woniu.car.order.web.entity.ProductOrder
     **/
    public ProductOrder findProductOrderByProductOrderNo(OrderVo orderVo);

    /**
     * @Author WangPeng
     * @Description //根据用户id查询商品信息
     * @Date  2021/4/8
     * @Param [user]
     * @return java.util.List<com.woniu.car.order.web.entity.ProductOrder>
     **/
    public List<ProductOrder> findProductOrderByUserId(UserVo userVo);

    /**
     * @Author WangPeng
     * @Description //生成商品订单信息
     * @Date  2021/4/8
     * @Param [ProductOrder]
     * @return java.lang.Boolean
     **/
    public Boolean insertProductOrder(ProductOrder productOrder);

    /**
     * @Author WangPeng
     * @Description TODO 根据商品订单id查询商品订单信息
     * @Date  1:34
     * @Param [productorderId]
     * @return com.woniu.car.order.web.entity.ProductOrder
     **/
    public ProductOrder findProductOrderByProductOrderId(Integer productOrderId);
    
    /**
     * @Author WangPeng
     * @Description TODO 查询所有商品订单
     * @Date  10:35
     * @Param []
     * @return com.woniu.car.order.web.entity.ProductOrder
     **/
    
    public List<ProductOrder> findAllProductOrder();
}
