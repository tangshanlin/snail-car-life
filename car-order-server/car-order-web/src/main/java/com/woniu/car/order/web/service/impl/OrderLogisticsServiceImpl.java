package com.woniu.car.order.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.order.model.param.ExpressNoParams;
import com.woniu.car.order.web.entity.OrderLogistics;
import com.woniu.car.order.web.mapper.OrderLogisticsMapper;
import com.woniu.car.order.web.service.OrderLogisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 商品订单物流表 服务实现类
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
@Service("orderLogisticsService")
@Slf4j
public class OrderLogisticsServiceImpl extends ServiceImpl<OrderLogisticsMapper, OrderLogistics> implements OrderLogisticsService {

    @Resource
    private OrderLogisticsMapper orderLogisticsMapper;
    /**
     * @Author WangPeng
     * @Description //新增物流信息
     * @Date  2021/4/8
     * @Param [orderLogistics]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean insertOrderLogistics(OrderLogistics orderLogistics) {
        log.info("开始新增物流信息");
        int row = orderLogisticsMapper.insert(orderLogistics);
        if(row>0){
            log.info("商品订单id："+orderLogistics.getProductOrderId()+"新增物流信息成功");
        }else{
            log.info("商品订单id："+orderLogistics.getProductOrderId()+"新增物流信息失败");
        }
        return row==1?true:false;
    }

    /**
     * @Author WangPeng
     * @Description TODO 根据订单id修改快递单号信息
     * @Date  10:46
     * @Param [expressNoParams]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean updateProductOrderExpressNo(ExpressNoParams expressNoParams) {
        System.out.println(expressNoParams);
        UpdateWrapper<OrderLogistics> wrapper = new UpdateWrapper<>();
        wrapper.eq("product_order_id",expressNoParams.getProductOrderId());
        wrapper.set("express_no",expressNoParams.getExpressNo());
        log.info("开始增加快递单号");
        int row = orderLogisticsMapper.update(null, wrapper);
        if(row>0){
            log.info("增加快递单号成功");
        }else{
            log.info("增加快递单号失败");
        }
        return row>0?true:false;
    }
}
