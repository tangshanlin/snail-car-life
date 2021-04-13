package com.woniu.car.order.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.order.model.param.OrderVo;
import com.woniu.car.order.model.param.UseCodeVo;
import com.woniu.car.order.model.param.UserVo;
import com.woniu.car.order.web.entity.CarserviceOrder;
import org.aspectj.weaver.ast.Or;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 服务订单表 服务类
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
public interface CarserviceOrderService extends IService<CarserviceOrder> {


    /**
     * @Author WangPeng
     * @Description //根据订单id查询服务订单信息
     * @Date  2021/4/8
     * @Param [carserviceOrder]
     * @return com.woniu.car.order.web.entity.CarserviceOrder
     **/
    public CarserviceOrder findCarserviceOrderByOrderId(CarserviceOrder carserviceOrder);

    /**
     * @Author WangPeng
     * @Description  //根据订单卷码查询服务订单信息
     * @Date  2021/4/8
     * @Param [carserviceOrder]
     * @return com.woniu.car.order.web.entity.CarserviceOrder
     **/
    public CarserviceOrder findCarserviceOrderByOrderCode(CarserviceOrder carserviceOrder);


    /**
     * @Author WangPeng
     * @Description //根据订单编号修改状态
     * @Date  2021/4/8
     * @Param [carserviceOrder]
     * @return java.lang.Boolean
     **/
    public Boolean updateServiceOrderStatusByOrderNo(CarserviceOrder carserviceOrder);

    /**
     * @Author WangPeng
     * @Description //根据userId查询全部订单信息
     * @Date  2021/4/8
     * @Param [user]
     * @return java.util.List<com.woniu.car.order.web.entity.CarserviceOrder>
     **/
    public List<CarserviceOrder> findCarserviceOrdersByUserId(UserVo userVo);

    /**
     * @Author WangPeng
     * @Description //根据门店及服务id生成订单
     * @Date  2021/4/8
     * @Param [carserviceOrder]
     * @return java.lang.Boolean
     **/
    public Boolean insertCarServiceOrder(CarserviceOrder carserviceOrder);

    /**
     * @Author WangPeng
     * @Description //根据订单编号改变订单状态
     * @Date  2021/4/8
     * @Param [order]
     * @return java.lang.Boolean
     **/
    public Boolean updateOrderStatus(OrderVo orderVo);

    /**
     * @Author WangPeng
     * @Description //根据订单编号删除订单
     * @Date  2021/4/8
     * @Param [order]
     * @return java.lang.Boolean
     **/
    public Boolean deleteOrderByOrderNo(OrderVo orderVo);

    /**
     * @Author WangPeng
     * @Description //根据订单编号查询订单信息
     * @Date  2021/4/8
     * @Param [order]
     * @return com.woniu.car.order.web.entity.CarserviceOrder
     **/
    public CarserviceOrder findCarserviceOrderByOrderNo(OrderVo orderVo);

    /**
     * @Author WangPeng
     * @Description TODO 根据使用码生成二维码图片
     * @Date  2021/4/8
     * @Param []
     * @return java.io.InputStream
     **/
    public InputStream insertQRCode(String useCode);


    /**
     * @Author WangPeng
     * @Description TODO 通过服务使用码查询服务订单信息
     * @Date  0:42
     * @Param [useCodeVo]
     * @return java.lang.Boolean
     **/
    public CarserviceOrder findCarserviceOrderByUseCode(UseCodeVo useCodeVo);


    /**
     * @Author WangPeng
     * @Description TODO 根据服务使用码改变订单状态为服务中
     * @Date  0:59
     * @Param [orderVo]
     * @return java.lang.Boolean
     **/
    
    public Boolean updateCarserviceOrderStatusByUseCode(UseCodeVo useCodeVo);

}
