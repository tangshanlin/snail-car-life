package com.woniu.car.order.web.controller;


import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.order.model.param.AddCarServiceReturnVo;
import com.woniu.car.order.model.param.OrderVo;
import com.woniu.car.order.web.code.OrderCode;
import com.woniu.car.order.web.entity.CarserviceOrder;
import com.woniu.car.order.web.entity.CarserviceReturns;
import com.woniu.car.order.web.service.CarserviceOrderService;
import com.woniu.car.order.web.service.CarserviceReturnsService;
import com.woniu.car.user.web.util.GetTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 服务退款表 前端控制器
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
@RestController
@RequestMapping("/carservice_returns")
@Api(tags = "服务退款")
public class CarserviceReturnsController {

    @Resource
    private CarserviceReturnsService carserviceReturnsService;

    @Resource
    private CarserviceOrderService carserviceOrderService;

    /**
     * @Author WangPeng
     * @Description TODO 服务退款申请接口
     * @Date  1:10
     * @Param [carserviceReturns]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @ApiOperation(value = "服务退款申请接口")
    @RequestMapping(value ="insert_carservice_return_money_order",method = RequestMethod.POST)
    public ResultEntity insertCarserviceReturnMoneyOrder(@RequestBody @Valid AddCarServiceReturnVo addCarServiceReturnVo){
        addCarServiceReturnVo.setUserId(GetTokenUtil.getUserId());

        /*根据服务订单编号查询订单信息*/
        CarserviceOrder carserviceOrder
                = carserviceOrderService.findCarserviceOrderByOrderNo(new OrderVo().setOrderNo(addCarServiceReturnVo.getCarserviceOrderNo()));
        /*创建退款表对象*/
        if (!ObjectUtils.isEmpty(carserviceOrder)) {
            /*判断订单状态*/
            if(OrderCode.ORDER_COMPLETION_PAY.equals(carserviceOrder.getCarserviceOrderStatus())||carserviceOrder.getCarserviceOrderStatus().equals(OrderCode.ORDER_COMPLETED)){
                CarserviceReturns carserviceReturns = new CarserviceReturns();
                /*订单单号*/
                carserviceReturns.setCarserviceOrderNo(addCarServiceReturnVo.getCarserviceOrderNo());
                /*用户id*/
                carserviceReturns.setUserId(carserviceOrder.getUserId());
                /*门店id*/
                carserviceReturns.setShopId(carserviceOrder.getShopId());
                /*退款金额*/
                carserviceReturns.setReturnsAmount(carserviceReturns.getReturnsAmount());
                /*退款原因*/
                carserviceReturns.setReturnReason(addCarServiceReturnVo.getReturnReason());
                /*提交退货信息*/
                Boolean aBoolean = carserviceReturnsService.insertCarserviceReturn(carserviceReturns);
                if(aBoolean){
                    /*修改订单状态为退款中*/
                    Boolean aBoolean1 = carserviceOrderService.updateOrderStatus(new OrderVo().setOrderNo(addCarServiceReturnVo.getCarserviceOrderNo()).setOrderStatus(OrderCode.ORDER_ING_MONEY_BACK));
                    if (aBoolean1){
                        return ResultEntity.buildFailEntity()
                                .setCode(ConstCode.ACCESS_SUCCESS)
                                .setFlag(true)
                                .setMessage("退款申请成功");
                    }else{
                        return ResultEntity.buildFailEntity()
                                .setCode(ConstCode.LAST_STAGE)
                                .setFlag(false)
                                .setMessage("退款申请失败");
                    }
                }

                return ResultEntity.buildFailEntity()
                        .setCode(ConstCode.LAST_STAGE)
                        .setFlag(false)
                        .setMessage("退款申请失败");
            }else{
                return ResultEntity.buildFailEntity()
                        .setCode(ConstCode.LAST_STAGE)
                        .setFlag(false)
                        .setMessage("退款申请失败,当前状态无法修改");
            }

        }
        return ResultEntity.buildFailEntity()
                .setCode(ConstCode.LAST_STAGE)
                .setFlag(false)
                .setMessage("退款申请失败,未知订单错误");

    }

}

