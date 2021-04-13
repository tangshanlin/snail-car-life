package com.woniu.car.order.web.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.marketing.model.dtoVo.GetCouponInfoByIdDtoVo;
import com.woniu.car.marketing.model.paramVo.GetCouponInfoByIdParamVo;
import com.woniu.car.marketing.model.paramVo.UpdatePaySuccessCouponParamVo;
import com.woniu.car.order.client.feign.MarketingClient;
import com.woniu.car.order.client.feign.StationClient;
import com.woniu.car.order.model.param.AddPowerplantOrderVo;
import com.woniu.car.order.web.code.OrderCode;
import com.woniu.car.order.web.entity.PowerplantOrder;
import com.woniu.car.order.web.service.PowerplantOrderService;
import com.woniu.car.station.model.entity.Powerplant;
import com.woniu.car.station.model.entity.Station;
import com.woniu.car.station.model.param.GetPowerplantParam;
import com.woniu.car.station.model.param.station.GetOneStationParam;
import com.woniu.car.user.web.domain.Address;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * <p>
 * 电站订单表 前端控制器
 * </p>
 *
 * @author WP
 * @since 2021-04-03
 */
@RestController
@RequestMapping("/powerplant_order")
@Api(tags = "电站订单")
public class PowerplantOrderController {

    @Resource
    private PowerplantOrderService powerplantOrderService;

    @Resource
    private MarketingClient marketingClient;

    @Resource
    private StationClient stationClient;


    /**
     * @Author WangPeng
     * @Description TODO 新增电站订单
     * @Date  9:03
     * @Param [addPowerplantOrderVo]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @RequestMapping(value = "insert_powerplant_order",method= RequestMethod.POST)
    @ApiOperation("新增电站订单")
    public ResultEntity insertPowerplantOrder(@RequestBody AddPowerplantOrderVo addPowerplantOrderVo){
        /*根据电桩id查询电站信息*/
        GetOneStationParam getOneStationParam = new GetOneStationParam();
        getOneStationParam.setStationId(addPowerplantOrderVo.getStationId());
        Object data = stationClient.getOneStation(getOneStationParam).getData();
        // 将数据转成json字符串
        String jsonObject= JSON.toJSONString(data);
        //将json转成需要的对象
        Station station= JSONObject.parseObject(jsonObject,Station.class);

        /*根据电站id查询电桩信息*/
        GetPowerplantParam getPowerplantParam = new GetPowerplantParam();
        getPowerplantParam.setPowerplantId(addPowerplantOrderVo.getPowerplantId());
        Object data1 = stationClient.getOnePowerplant(getPowerplantParam).getData();
        // 将数据转成json字符串
        String jsonObject1= JSON.toJSONString(data1);
        //将json转成需要的对象
        Powerplant powerplant= JSONObject.parseObject(jsonObject,Powerplant.class);

        /*创建电站订单对象*/
        PowerplantOrder powerplantOrder = new PowerplantOrder();

        /*计算出总价*/
        BigDecimal totalPrice = station.getStationPrice().multiply(new BigDecimal(addPowerplantOrderVo.getElectricity()));

        /*根据优惠券id查询优惠券详情*/
        GetCouponInfoByIdParamVo getCouponInfoByIdParamVo = new GetCouponInfoByIdParamVo();
        getCouponInfoByIdParamVo.setCouponInfoId(addPowerplantOrderVo.getCouponInfoId());
        GetCouponInfoByIdDtoVo couponInfo = marketingClient.getCouponInfoById(getCouponInfoByIdParamVo).getData();


        /*判断优惠券是否存在*/
        if (!ObjectUtils.isEmpty(couponInfo)) {
            /*判断优惠券门槛*/
            if (totalPrice.compareTo(couponInfo.getCouponCondition()) ==-1) {
                /*计算出实付金额*/
                BigDecimal amountPaid = totalPrice.subtract(totalPrice.subtract(couponInfo.getCouponMoney()));
                /*优惠券id*/
                powerplantOrder.setCouponInfoId(addPowerplantOrderVo.getCouponInfoId());
                /*实付金额*/
                powerplantOrder.setAmountPaid(amountPaid);

                /*修改优惠券状态*/
                UpdatePaySuccessCouponParamVo updatePaySuccessCouponParamVo = new UpdatePaySuccessCouponParamVo();
                updatePaySuccessCouponParamVo.setCouponInfoId(addPowerplantOrderVo.getCouponInfoId());
                updatePaySuccessCouponParamVo.setCouponId(couponInfo.getCouponId());
                marketingClient.updateCouponByPaySuccess(updatePaySuccessCouponParamVo);
            }else{
                /*优惠券id*/
                powerplantOrder.setCouponInfoId(0);
                /*实付金额*/
                powerplantOrder.setAmountPaid(totalPrice);
            }
        }else{
            /*优惠券id*/
            powerplantOrder.setCouponInfoId(0);
        }
        /*电桩id*/
        powerplantOrder.setStationId(addPowerplantOrderVo.getStationId());
        /*电站id*/
        powerplantOrder.setPowerplantId(addPowerplantOrderVo.getPowerplantId());
        /*用户id*/
        powerplantOrder.setUserId(addPowerplantOrderVo.getUserId());
        /*电桩编号*/
        powerplantOrder.setStationMumeration(station.getStationNumeration());
        /*开始时间*/
        powerplantOrder.setChargeStartTime(addPowerplantOrderVo.getChargeStartTime());
        /*结束时间*/
        powerplantOrder.setChargeEndTime(addPowerplantOrderVo.getChargeEndTime());
        /*每度电多少钱*/
        powerplantOrder.setKilowattHourPrice(station.getStationPrice());
        /*电的度数*/
        powerplantOrder.setElectricity(addPowerplantOrderVo.getElectricity());
        /*车牌号*/
        powerplantOrder.setCarCode(addPowerplantOrderVo.getCarCode());
        /*充电类型*/
        powerplantOrder.setStationType(station.getStationType()+"");
        /*订单状态*/
        powerplantOrder.setPowerplantOrderStatus(OrderCode.ORDER_NON_PAYMENT);
        /*电桩图片*/
        powerplantOrder.setStationImage(station.getStationImage());
        /*充电费用*/
        powerplantOrder.setTotalPrice(totalPrice);
        /*电桩品牌*/
        powerplantOrder.setStationBrand(station.getStationBrand());
        /*电站名称*/
        powerplantOrder.setPowerplanName(powerplant.getPowerplanName());

        /*调用新增方法*/
        Boolean aBoolean = powerplantOrderService.insertPowerplantOrder(powerplantOrder);

        if(aBoolean){
            return ResultEntity.buildFailEntity()
                    .setCode(ConstCode.ACCESS_SUCCESS)
                    .setFlag(true)
                    .setMessage("新增订单成功");
        }
        return ResultEntity.buildFailEntity()
                .setCode(ConstCode.LAST_STAGE)
                .setFlag(false)
                .setMessage("新增订单失败");
    }
}

