package com.woniu.car.order.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.order.model.param.AddPowerplantOrderVo;
import com.woniu.car.order.model.param.OrderVo;
import com.woniu.car.order.model.param.UserVo;
import com.woniu.car.order.web.entity.PowerplantOrder;
import com.woniu.car.order.web.mapper.PowerplantOrderMapper;
import com.woniu.car.order.web.service.PowerplantOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 电站订单表 服务实现类
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
@Service("powerplantOrderService")
@Slf4j
public class PowerplantOrderServiceImpl extends ServiceImpl<PowerplantOrderMapper, PowerplantOrder> implements PowerplantOrderService {


    @Resource
    private PowerplantOrderMapper powerplantOrderMapper;

    /**
     * @Author WangPeng
     * @Description //根据订单id查询电站订单信息
     * @Date  2021/4/8
     * @Param [powerplantOrder]
     * @return com.woniu.car.order.web.entity.PowerplantOrder
     **/
    @Override
    public PowerplantOrder findpowerplantOrderBypowerplantOrderId(PowerplantOrder powerplantOrder) {
        PowerplantOrder powerplantOrder1 = powerplantOrderMapper.selectById(powerplantOrder.getPowerplantOrderId());
        return powerplantOrder1;
    }

    /**
     * @Author WangPeng
     * @Description //根据订单单号查询电站订单信息
     * @Date  2021/4/8
     * @Param [powerplantOrder]
     * @return com.woniu.car.order.web.entity.PowerplantOrder
     **/
    @Override
    public PowerplantOrder findpowerplantOrderByOrderCode(PowerplantOrder powerplantOrder) {
        log.info("开始查询订单号："+powerplantOrder.getOrderCode());
        QueryWrapper<PowerplantOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_code",powerplantOrder.getOrderCode());
        PowerplantOrder powerplantOrder1 = powerplantOrderMapper.selectOne(wrapper);
        if(!ObjectUtils.isEmpty(powerplantOrder1)){
            log.info("查询订单号："+powerplantOrder.getOrderCode()+"成功");
        }else{
            log.info("查询订单号："+powerplantOrder.getOrderCode()+"失败，此订单不存在");
        }
        return powerplantOrder1;
    }

    /**
     * @Author WangPeng
     * @Description //调用根据user_id查询电站订单方法
     * @Date  2021/4/8
     * @Param [user]
     * @return java.util.List<com.woniu.car.order.web.entity.PowerplantOrder>
     **/
    @Override
    public List<PowerplantOrder> findpowerplantOrderByUserId(UserVo userVo) {
        QueryWrapper<PowerplantOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userVo.getUserId());
        List<PowerplantOrder> powerplantOrders = powerplantOrderMapper.selectList(wrapper);
        return powerplantOrders;
    }

    /**
     * @Author WangPeng
     * @Description TODO 新增电站订单
     * @Date  2021/4/8
     * @Param [PowerplantOrder]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean insertPowerplantOrder(PowerplantOrder powerplantOrder) {
        log.info("开始生成电站订单");
        int row = powerplantOrderMapper.insert(powerplantOrder);
        if(row>0){
            log.info("生成电站订单成功");
        }else{
            log.info("生成电站订单失败");
        }
        return row>0?true:false;
    }

    /**
     * @Author WangPeng
     * @Description TODO 查询所有电站订单
     * @Date  11:00
     * @Param []
     * @return java.util.List<com.woniu.car.order.web.entity.PowerplantOrder>
     **/
    @Override
    public List<PowerplantOrder> findAllPowerplantOrder() {
        return powerplantOrderMapper.selectList(null);
    }

//    /**
//     * @Author WangPeng
//     * @Description TODO 根据订单编号查询订单详情信息
//     * @Date  3:40
//     * @Param [orderVo]
//     * @return com.woniu.car.order.web.entity.PowerplantOrder
//     **/
//    public PowerplantOrder findPowerplantOrderByOrderNo(OrderVo orderVo) {
//        QueryWrapper<PowerplantOrder> wrapper = new QueryWrapper<>();
//        wrapper.eq("order_code",orderVo.getOrderNo());
//        PowerplantOrder powerplantOrder = powerplantOrderMapper.selectOne(wrapper);
//        return powerplantOrder;
//    }

}
