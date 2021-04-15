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
import javax.validation.Valid;

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
    @ApiOperation(value = "新增门店服务具体收益数据",notes = "feign：门店完成服务新增门店服务具体收益数据")
    @PostMapping("add_shop_earnings_info")
    public ResultEntity addShopEarningsInfo(@RequestBody @Valid AddShopEarningsInfoParamVo addShopEarningsInfoParamVo){
        Boolean boo = shopEarningsInfoService.addShopEarningsInfo(addShopEarningsInfoParamVo);
        if(boo){
            return ResultEntity.buildSuccessEntity().setMessage("新增门店服务收益成功");
        }else {
            return ResultEntity.buildFailEntity().setMessage("新增门店服务收益失败");
        }
    }
}

