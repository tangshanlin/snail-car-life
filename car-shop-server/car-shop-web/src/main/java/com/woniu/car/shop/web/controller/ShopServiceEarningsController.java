package com.woniu.car.shop.web.controller;


import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.shop.model.paramVo.AddShopServiceEarningsParamVo;
import com.woniu.car.shop.web.service.ShopEarningsInfoService;
import com.woniu.car.shop.web.service.ShopServiceEarningsService;
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
 *  门店服务收益
 * </p>
 *
 * @author tsl
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/shop-service-earnings")
@Api(tags = "门店服务收益")
public class ShopServiceEarningsController {

    @Resource
    private ShopServiceEarningsService shopServiceEarningsService;

    /*
    * @Author TangShanLin
    * @Description TODO 新增服务完成后添加门店服务具体收益数据
    * @Date  16:56
    * @Param []
    * @return com.woniu.car.commons.core.dto.ResultEntity
    **/
    @ApiOperation(value = "添加门店服务具体收益数据",notes = "新增服务完成后添加门店服务具体收益数据")
    @PostMapping("add_shop_service_earnings")
    public ResultEntity addShopServiceEarnings(@RequestBody @Valid AddShopServiceEarningsParamVo addShopServiceEarningsParamVo){
        Boolean boo = shopServiceEarningsService.addShopServiceEarnings(addShopServiceEarningsParamVo);
        if(boo){
            return ResultEntity.buildSuccessEntity().setMessage("添加门店服务收益数据成功");
        }
        return ResultEntity.buildFailEntity().setMessage("添加门店服务收益失败");
    }



}

