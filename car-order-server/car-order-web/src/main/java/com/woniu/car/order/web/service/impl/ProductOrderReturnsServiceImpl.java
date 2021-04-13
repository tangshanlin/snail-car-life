package com.woniu.car.order.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.order.web.entity.ProductOrderReturns;
import com.woniu.car.order.web.mapper.ProductOrderReturnsMapper;
import com.woniu.car.order.web.service.ProductOrderReturnsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品退货表 服务实现类
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
@Service("productOrderReturnsService")
public class ProductOrderReturnsServiceImpl extends ServiceImpl<ProductOrderReturnsMapper, ProductOrderReturns> implements ProductOrderReturnsService {

    @Resource
    private ProductOrderReturnsMapper productOrderReturnsMapper;

    /**
     * @Author WangPeng
     * @Description //根据商userId查询详细信息
     * @Date  2021/4/8
     * @Param [productOrderReturns]
     * @return java.util.List<com.woniu.car.order.web.entity.ProductOrderReturns>
     **/
    @Override
    public List<ProductOrderReturns> findProductOrderReturnsByUserId(ProductOrderReturns productOrderReturns) {
        QueryWrapper<ProductOrderReturns> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",productOrderReturns.getUserId());
        List<ProductOrderReturns> productOrderReturns1 = productOrderReturnsMapper.selectList(wrapper);
        return productOrderReturns1;
    }
}
