package com.woniu.car.order.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.woniu.car.order.client.OrderClient;
import com.woniu.car.order.web.entity.ProductOrderDetail;
import com.woniu.car.order.web.mapper.ProductOrderDetailMapper;
import com.woniu.car.order.web.service.ProductOrderDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品详情表 服务实现类
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
@Service("productOrderDetailService")
public class ProductOrderDetailServiceImpl extends ServiceImpl<ProductOrderDetailMapper, ProductOrderDetail> implements ProductOrderDetailService {

    @Resource
    private ProductOrderDetailMapper productOrderDetailMapper;



   /**
    * @Author WangPeng
    * @Description  //根据商品订单编号查询订单详细信息
    * @Date  2021/4/8
    * @Param [productOrderNo]
    * @return java.util.List<com.woniu.car.order.web.entity.ProductOrderDetail>
    **/
    @Override
    public List<ProductOrderDetail> findProductOrderDerailByProductOrderNo(String productOrderNo) {
        QueryWrapper<ProductOrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("product_order_no",productOrderNo);
        List<ProductOrderDetail> productOrderDetails = productOrderDetailMapper.selectList(wrapper);
        return productOrderDetails;
    }

    /**
     * @Author WangPeng
     * @Description TODO 生成商品订单
     * @Date  2021/4/8
     * @Param [ProductOrderDetail]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean insertProductOrderDetail(ProductOrderDetail productOrderDetail) {
        log.debug("商品id为"+productOrderDetail.getProductId()+",开始生成商品详情订单");
        int row = productOrderDetailMapper.insert(productOrderDetail);
        if(row>0){
            log.debug("商品id为"+productOrderDetail.getProductId()+",生成商品详情订单成功");
        }else{
            log.debug("商品id为"+productOrderDetail.getProductId()+",生成商品详情订单失败");
        }
        return row>0?true:false;
    }

}
