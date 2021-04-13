package com.woniu.car.shop.web.controller;


import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.shop.model.paramVo.AddShopEarningsInfoParamVo;
import com.woniu.car.shop.web.service.ShopEarningsInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  门店服务具体收益
 * </p>
 *
 * @author tsl
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/shop-earnings-info")
@Api(tags = "门店服务具体收益")
public class ShopEarningsInfoController {

    @Resource
    private ShopEarningsInfoService shopEarningsInfoService;

    /*
     * @Author TangShanLin
     * @Description TODO 门店完成服务新增门店服务具体收益数据
     * @Date  21:35
     * @Param [addShopServiceEarningsParamVo]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @ApiOperation(value = "门店完成服务新增门店服务具体收益数据")
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "shopId",value = "关联门店id"),
            @ApiImplicitParam(name="carServiceName",value = "服务名称"),
            @ApiImplicitParam(name="carServiceMoney",value = "服务金额"),
            @ApiImplicitParam(name="payTime",value = "付款时间"),
            @ApiImplicitParam(name="couponMoney",value = "优惠券面额(元)"),
            @ApiImplicitParam(name="couponGoods",value = "发行来源(0平台-其他对应门店id)"),
    })
    @PostMapping("add_shop_earnings_info")
    public ResultEntity addShopEarningsInfo(AddShopEarningsInfoParamVo addShopEarningsInfoParamVo){
        Boolean boo = shopEarningsInfoService.addShopEarningsInfo(addShopEarningsInfoParamVo);
        if(boo){
            return ResultEntity.buildSuccessEntity().setMessage("新增门店服务收益成功");
        }else {
            return ResultEntity.buildSuccessEntity().setMessage("新增门店服务收益失败");
        }
    }
}

