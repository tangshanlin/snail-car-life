package com.woniu.car.order.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.order.model.param.AddProductOrderVo;
import com.woniu.car.order.model.param.OrderVo;
import com.woniu.car.order.model.param.UserVo;
import com.woniu.car.order.web.entity.ProductOrder;
import com.woniu.car.order.web.mapper.ProductOrderMapper;
import com.woniu.car.order.web.service.ProductOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品订单表 服务实现类
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
@Service("productOrderService")
@Slf4j
public class ProductOrderServiceImpl extends ServiceImpl<ProductOrderMapper, ProductOrder> implements ProductOrderService {

    @Resource
    private ProductOrderMapper productOrderMapper;

    /**
     * @Author WangPeng
     * @Description //根据商品订单id查询商品订单信息
     * @Date  2021/4/8
     * @Param [productOrder]
     * @return com.woniu.car.order.web.entity.ProductOrder
     **/
    @Override
    public ProductOrder findProductOrderByOrderId(ProductOrder productOrder) {
        ProductOrder productOrder1 = productOrderMapper.selectById(productOrder.getProductOrderId());
        return productOrder1;
    }

    /**
     * @Author WangPeng
     * @Description //根据商品订单编号查询订单信息
     * @Date  2021/4/8
     * @Param [order]
     * @return com.woniu.car.order.web.entity.ProductOrder
     **/
    @Override
    public ProductOrder findProductOrderByProductOrderNo(OrderVo orderVo) {
        QueryWrapper<ProductOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("product_order_no", orderVo.getOrderNo());
        ProductOrder productOrder = productOrderMapper.selectOne(wrapper);
        return productOrder;
    }

    /**
     * @Author WangPeng
     * @Description //根据用户id查询商品信息
     * @Date  2021/4/8
     * @Param [user]
     * @return java.util.List<com.woniu.car.order.web.entity.ProductOrder>
     **/
    @Override
    public List<ProductOrder> findProductOrderByUserId(UserVo userVo) {
        QueryWrapper<ProductOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userVo.getUserId());
        List<ProductOrder> productOrders = productOrderMapper.selectList(wrapper);
        return productOrders;
    }

    /**
     * @Author WangPeng
     * @Description //生成商品订单信息
     * @Date  2021/4/8
     * @Param [ProductOrder]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean insertProductOrder(ProductOrder productOrder) {
        log.info("开始生成订单");
        int row = productOrderMapper.insert(productOrder);
        log.info("生成订单成功");
        return row>0?true:false;
    }

    /**
     * @Author WangPeng
     * @Description TODO 根据商品订单id查询商品订单信息
     * @Date  1:34
     * @Param [productorderId]
     * @return com.woniu.car.order.web.entity.ProductOrder
     **/
    @Override
    public ProductOrder findProductOrderByProductOrderId(Integer productOrderId) {
        QueryWrapper<ProductOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("product_order_id",productOrderId);
        ProductOrder productOrder = productOrderMapper.selectOne(wrapper);
        return productOrder;
    }
}
