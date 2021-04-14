package com.woniu.car.order.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.car.order.model.param.AddPowerplantOrderVo;
import com.woniu.car.order.model.param.OrderVo;
import com.woniu.car.order.model.param.UserVo;
import com.woniu.car.order.web.entity.CarserviceOrder;
import com.woniu.car.order.web.entity.PowerplantOrder;

import java.util.List;

/**
 * <p>
 * 电站订单表 服务类
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
public interface PowerplantOrderService extends IService<PowerplantOrder> {

   /**
    * @Author WangPeng
    * @Description  //根据订单id查询电站订单信息
    * @Date  2021/4/8
    * @Param [powerplantOrder]
    * @return com.woniu.car.order.web.entity.PowerplantOrder
    **/
    public PowerplantOrder findpowerplantOrderBypowerplantOrderId(PowerplantOrder powerplantOrder);

    /**
     * @Author WangPeng
     * @Description //根据订单券码查询电站订单信息
     * @Date  2021/4/8
     * @Param [powerplantOrder]
     * @return com.woniu.car.order.web.entity.PowerplantOrder
     **/
    public PowerplantOrder findpowerplantOrderByOrderCode(PowerplantOrder powerplantOrder);

    /**
     * @Author WangPeng
     * @Description //调用根据user_id查询电站订单方法
     * @Date  2021/4/8
     * @Param [user]
     * @return java.util.List<com.woniu.car.order.web.entity.PowerplantOrder>
     **/
    public List<PowerplantOrder> findpowerplantOrderByUserId(UserVo userVo);

    /**
     * @Author WangPeng
     * @Description TODO 新增电站订单
     * @Date  2021/4/8
     * @Param [PowerplantOrder]
     * @return java.lang.Boolean
     **/
    public Boolean  insertPowerplantOrder(PowerplantOrder powerplantOrder);
    
//    /**
//     * @Author WangPeng
//     * @Description TODO 根据订单编号查询订单详情
//     * @Date  3:40
//     * @Param [orderVo]
//     * @return com.woniu.car.order.web.entity.PowerplantOrder
//     **/
//
//    public PowerplantOrder findPowerplantOrderByOrderNo(OrderVo orderVo);

 /**
  * @Author WangPeng
  * @Description TODO 查询所有电站订单
  * @Date  10:32
  * @Param []
  * @return com.woniu.car.order.web.entity.CarserviceOrder
  **/
 public List<PowerplantOrder> findAllPowerplantOrder();
}
