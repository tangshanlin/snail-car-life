package com.woniu.car.order.web.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.order.model.param.OrderVo;
import com.woniu.car.order.model.param.UseCodeVo;
import com.woniu.car.order.model.param.UserVo;
import com.woniu.car.order.web.code.OrderCode;
import com.woniu.car.order.web.entity.CarserviceOrder;
import com.woniu.car.order.web.entity.PowerplantOrder;
import com.woniu.car.order.web.entity.ProductOrder;
import com.woniu.car.order.web.entity.ProductOrderDetail;
import com.woniu.car.order.web.mapper.CarserviceOrderMapper;
import com.woniu.car.order.web.mapper.ProductOrderDetailMapper;
import com.woniu.car.order.web.mapper.ProductOrderMapper;
import com.woniu.car.order.web.service.CarserviceOrderService;
import com.woniu.car.order.web.service.PowerplantOrderService;
import com.woniu.car.order.web.service.ProductOrderDetailService;
import com.woniu.car.order.web.service.ProductOrderService;
import com.woniu.car.order.web.util.QRUtil;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 服务订单表 服务实现类
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */

/*
 * @Author WangPeng
 * @Description //TODO
 * @Date 2021/4/8 0:52
 * @Param
 * @return
 **/
@Service("carserviceOrderService")
@Slf4j
public class CarserviceOrderServiceImpl extends ServiceImpl<CarserviceOrderMapper, CarserviceOrder> implements CarserviceOrderService {

    @Resource
    private CarserviceOrderMapper carserviceOrderMapper;

    @Resource
    private ProductOrderMapper productOrderMapper;

    @Resource
    private CarserviceOrderService carserviceOrderService;

    @Resource
    private ProductOrderDetailService productOrderDetailService;

    @Resource
    private ProductOrderService productOrderService;

    @Resource
    private PowerplantOrderService powerplantOrderService;

    @Resource
    private ProductOrderDetailMapper productOrderDetailMapper;


    //根据订单id查询全部订单信息
    @Override
    public CarserviceOrder findCarserviceOrderByOrderId(CarserviceOrder carserviceOrder) {
        System.out.println(carserviceOrderMapper);
        CarserviceOrder carserviceOrder1 = carserviceOrderMapper.selectById(carserviceOrder.getCarserviceOrderId());
        System.out.println(carserviceOrder.getCarserviceOrderId());
        List<CarserviceOrder> carserviceOrders = carserviceOrderMapper.selectList(null);
        System.out.println(carserviceOrders);
        return carserviceOrders.get(0);
    }


    /**
     * @Author WangPeng
     * @Description //根据订单卷码查询服务订单信息
     * @Date  2021/4/8
     * @Param [carserviceOrder]
     * @return com.woniu.car.order.web.entity.CarserviceOrder
     **/
    @Override
    public CarserviceOrder findCarserviceOrderByOrderCode(CarserviceOrder carserviceOrder) {
        QueryWrapper<CarserviceOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_code",carserviceOrder.getOrderCode());
        CarserviceOrder carserviceOrder1 = carserviceOrderMapper.selectOne(wrapper);
        return carserviceOrder1;
    }


    /**
     * @Author WangPeng
     * @Description //根据订单编号修改服务订单状态
     * @Date  2021/4/8
     * @Param [carserviceOrder]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean updateServiceOrderStatusByOrderNo(CarserviceOrder carserviceOrder) {
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.eq("carservice_order_no",carserviceOrder.getCarserviceOrderNo());
        wrapper.set("carservice_order_status",carserviceOrder.getCarserviceOrderStatus());
        int row = carserviceOrderMapper.update(null,wrapper);
        return row==1?true:false;
    }


    /**
     * @Author WangPeng
     * @Description //根据userId查询全部订单信息
     * @Date  2021/4/8
     * @Param [user]
     * @return java.util.List<com.woniu.car.order.web.entity.CarserviceOrder>
     **/
    @Override
    public List<CarserviceOrder> findCarserviceOrdersByUserId(UserVo userVo) {
        QueryWrapper<CarserviceOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userVo.getUserId());
        List<CarserviceOrder> carserviceOrders = carserviceOrderMapper.selectList(wrapper);
        return carserviceOrders;
    }


    /**
     * @Author WangPeng
     * @Description //根据门店及服务id生成订单
     * @Date  2021/4/8
     * @Param [carserviceOrder]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean insertCarServiceOrder(CarserviceOrder carserviceOrder){
        log.info("开始生成服务订单");
        int row = carserviceOrderMapper.insert(carserviceOrder);
        if(row>0){
            log.info("生成服务订单成功");
        }else{
            log.info("生成服务订单失败");
        }
        return row>0?true:false;
    }


    /**
     * @Author WangPeng
     * @Description //根据订单编号改变订单状态
     * @Date  2021/4/8
     * @Param [order]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean updateOrderStatus(OrderVo orderVo) {
        int row = 0;

        //先查询服务订单
        CarserviceOrder carserviceOrder = carserviceOrderService.findCarserviceOrderByOrderNo(orderVo);
        //判断是否为空
        if (!ObjectUtil.isEmpty(carserviceOrder)) {
            //服务修改状态
            UpdateWrapper<CarserviceOrder> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("carservice_order_id",carserviceOrder.getCarserviceOrderId());
            updateWrapper.set("carservice_order_status", orderVo.getOrderStatus());
            row = carserviceOrderMapper.update(null, updateWrapper);
            return row>0?true:false;
        }
        //当为空的时候去查询商品订单
        ProductOrder productOrder = productOrderService.findProductOrderByProductOrderNo(orderVo);
        if (!ObjectUtil.isEmpty(productOrder)) {
            //商品修改状态
            UpdateWrapper<ProductOrder> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("product_order_id",productOrder.getProductOrderId());
            updateWrapper.set("product_order_status", orderVo.getOrderStatus());
            row = productOrderMapper.update(null, updateWrapper);
            return row>0?true:false;
        }
        return false;
    }


    /**
     * @Author WangPeng
     * @Description //根据订单编号删除订单
     * @Date  2021/4/8
     * @Param [order]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean deleteOrderByOrderNo(OrderVo orderVo) {
        int row = 0;
        //根据订单编号查询服务订单信息
        CarserviceOrder carserviceOrder = carserviceOrderService.findCarserviceOrderByOrderNo(orderVo);
        String carserviceOrderStatus = carserviceOrder.getCarserviceOrderStatus();
        if (!ObjectUtil.isEmpty(carserviceOrder)) {
           if(OrderCode.ORDER_COMPLETED.equals(carserviceOrderStatus)
                   ||OrderCode.ORDER_ED_MONEY_BACK.equals(carserviceOrderStatus)
                   ||OrderCode.ORDER_RECEIPT_GOODS.equals(carserviceOrderStatus)
                   ||OrderCode.ORDER_TRANSACTION_CANCELLED.equals(carserviceOrderStatus)){
                //满足条件后进行删除服务订单
               UpdateWrapper<CarserviceOrder> wrapper = new UpdateWrapper<>();
               wrapper.eq("carservice_order_no", orderVo.getOrderNo());
               row = carserviceOrderMapper.delete( wrapper);
                return row>0?true:false;
           }else{
               return false;
           }
        }
        ProductOrder productOrder = productOrderService.findProductOrderByProductOrderNo(orderVo);
        String productOrderStatus = productOrder.getProductOrderStatus();
        if (!ObjectUtil.isEmpty(productOrder)) {
            if(OrderCode.ORDER_COMPLETED.equals(productOrderStatus)
                    ||OrderCode.ORDER_ED_MONEY_BACK.equals(productOrderStatus)
                        ||OrderCode.ORDER_TRANSACTION_CANCELLED.equals(productOrderStatus)){
                    //满足条件后进行删除商品订单
                    UpdateWrapper<ProductOrder> wrapper = new UpdateWrapper<>();
                    wrapper.eq("productOrder_order_no", orderVo.getOrderNo());
                    row = productOrderMapper.delete(wrapper);
                    /*删除商品详细订单*/
                UpdateWrapper<ProductOrderDetail> wrapper1 = new UpdateWrapper<>();
                wrapper1.eq("product_order_no",orderVo.getOrderNo());
                int row1 = productOrderDetailMapper.delete(wrapper1);
                return row>0?true:false;
            }else{
                return false;
            }
        }
        PowerplantOrder powerplantOrder = powerplantOrderService.findpowerplantOrderByOrderCode(new PowerplantOrder().setOrderCode(orderVo.getOrderNo()));
        String powerplantOrderStatus = powerplantOrder.getPowerplantOrderStatus();
        if (!ObjectUtil.isEmpty(powerplantOrder)) {
            if (OrderCode.ORDER_COMPLETED.equals(powerplantOrderStatus)) {
                //满足条件后进行删除订单
                UpdateWrapper<ProductOrder> wrapper = new UpdateWrapper<>();
                wrapper.eq("order_code", orderVo.getOrderNo());
                row = productOrderMapper.delete(wrapper);
                return row > 0 ? true : false;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * @Author WangPeng
     * @Description //根据订单编号查询订单信息
     * @Date  2021/4/8
     * @Param [order]
     * @return com.woniu.car.order.web.entity.CarserviceOrder
     **/
    @Override
    public CarserviceOrder findCarserviceOrderByOrderNo(OrderVo orderVo) {
        /*设置条件*/
        QueryWrapper<CarserviceOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("carservice_order_no", orderVo.getOrderNo());
        /*查询服务订单*/
        CarserviceOrder carserviceOrder = carserviceOrderMapper.selectOne(wrapper);
        return carserviceOrder;
    }

    /**
     * @Author WangPeng
     * @Description TODO 根据使用码生成二维码图片
     * @Date  2021/4/8
     * @Param [useCode]
     * @return java.io.InputStream
     **/
    @Override
    public InputStream insertQRCode(String useCode)  {
        InputStream qr = null;
        try {
            log.debug("根据使用码生成二维码");
            qr = QRUtil.createQR(useCode, "jpg", "http://192.168.90.240:9000/order/logo.png");
            log.debug("生成二维码成功");
            return qr;
        } catch (Exception e) {
            log.debug("生成二维码失败");
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @Author WangPeng
     * @Description TODO 通过服务使用码查询服务订单信息
     * @Date  0:43
     * @Param [useCodeVo]
     * @return java.lang.Boolean
     **/
    @Override
    public CarserviceOrder findCarserviceOrderByUseCode(UseCodeVo useCodeVo) {
        QueryWrapper<CarserviceOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_code",useCodeVo.getUseCode());

        log.info("服务使用码为："+useCodeVo.getUseCode()+"正在开始查询信息");
        CarserviceOrder carserviceOrder = carserviceOrderMapper.selectOne(wrapper);

        return carserviceOrder;
    }

    /**
     * @Author WangPeng
     * @Description TODO 根据服务使用码改变订单状态为服务中
     * @Date  1:02
     * @Param [useCodeVo]
     * @return java.lang.Boolean
     **/
    @Override
    public Boolean updateCarserviceOrderStatusByUseCode(UseCodeVo useCodeVo) {
        UpdateWrapper<CarserviceOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("order_code",useCodeVo.getUseCode());
        wrapper.set("carservice_order_status",OrderCode.ORDER_SERVICEING);
        int row = carserviceOrderMapper.update(null, wrapper);
        return row>0?true:false;
    }

    /**
     * @Author WangPeng
     * @Description TODO 查询所有商品订单
     * @Date  11:23
     * @Param []
     * @return java.util.List<com.woniu.car.order.web.entity.CarserviceOrder>
     **/
    @Override
    public List<CarserviceOrder> findAllCarserviceOrder() {
        return  carserviceOrderMapper.selectList(null);
    }
}
