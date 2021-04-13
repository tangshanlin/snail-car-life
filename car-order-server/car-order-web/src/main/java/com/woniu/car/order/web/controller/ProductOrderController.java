package com.woniu.car.order.web.controller;


import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.order.model.param.ExpressNoParams;
import com.woniu.car.order.model.param.OrderVo;
import com.woniu.car.order.web.code.OrderCode;
import com.woniu.car.order.web.entity.OrderLogistics;
import com.woniu.car.order.web.entity.ProductOrder;
import com.woniu.car.order.web.service.CarserviceOrderService;
import com.woniu.car.order.web.service.OrderLogisticsService;
import com.woniu.car.order.web.service.ProductOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 商品订单表 前端控制器
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
@RestController
@RequestMapping("/product_order")
@Api(tags = "商品订单")
public class ProductOrderController {

    @Resource
    private CarserviceOrderService carserviceOrderService;

    @Resource
    private OrderLogisticsService orderLogisticsService;

    @Resource
    private ProductOrderService productOrderService;

    /**
     * @Author WangPeng
     * @Description TODO 为订单新增物流单号（会更改状商品订单态为已发货）
     * @Date  10:32
     * @Param [orderVo]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @RequestMapping(value = "api/update_carservice_order_status_for_sent_by_order_no",method= RequestMethod.POST)
    @ApiOperation("为订单新增物流单号（会更改状商品订单态为已发货）")
    public ResultEntity updateCarserviceOrderStatusForSentByOrderNo(@RequestBody @Valid ExpressNoParams expressNoParams){
        /*根据商品订单id查询信息*/
        ProductOrder pr = productOrderService.findProductOrderByProductOrderId(expressNoParams.getProductOrderId());
        /*验证订单号是否存在*/
        if(!ObjectUtils.isEmpty(pr)){
            /*判断订单状态*/
            if(pr.getProductOrderStatus().equals(OrderCode.ORDER_NOT_SHIPPED)){
                /*添加快递单号信息*/
                Boolean aBoolean1 = orderLogisticsService.updateProductOrderExpressNo(expressNoParams);
                if(aBoolean1){
                    /*修改订单状态为已发货*/
                    Boolean aBoolean = carserviceOrderService.updateOrderStatus(new OrderVo().setOrderStatus(OrderCode.ORDER_SENT).setOrderNo(pr.getProductOrderNo()));
                    if(aBoolean){
                        return ResultEntity.buildFailEntity()
                                .setCode(ConstCode.ACCESS_SUCCESS)
                                .setFlag(true)
                                .setMessage("修改物流信息成功");
                    }else{
                        return ResultEntity.buildFailEntity()
                                .setCode(ConstCode.LAST_STAGE)
                                .setFlag(false)
                                .setMessage("修改物流信息失败");
                    }
                }else{
                    return ResultEntity.buildFailEntity()
                            .setCode(ConstCode.LAST_STAGE)
                            .setFlag(false)
                            .setMessage("修改物流信息失败");
                }
            }else{
                return ResultEntity.buildFailEntity()
                        .setCode(ConstCode.LAST_STAGE)
                        .setFlag(false)
                        .setMessage("订单当前状态无法修改");
            }
        }else{
            return ResultEntity.buildFailEntity()
                    .setCode(ConstCode.LAST_STAGE)
                    .setFlag(false)
                    .setMessage("未知订单错误");
        }
    }
}

