package com.woniu.car.shop.web.controller;


import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.shop.model.dtoVo.FindShopServiceEarningInfoByEarningIdDtoVo;
import com.woniu.car.shop.model.paramVo.AddShopEarningsInfoParamVo;
import com.woniu.car.shop.model.paramVo.FindShopEarningIdParamVo;
import com.woniu.car.shop.web.service.ShopEarningsInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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
        Integer integer = shopEarningsInfoService.addShopEarningsInfo(addShopEarningsInfoParamVo);
        if(integer.equals(200)){
            return ResultEntity.buildSuccessEntity().setMessage("新增门店服务收益成功");
        }else if(integer.equals(ConstCode.Service_Revenue_Is_Empty)){
            return ResultEntity.buildFailEntity()
                    .setMessage("新增门店服务收益失败,服务不存在")
                    .setCode(ConstCode.Service_Revenue_Is_Empty);
        }else {
            return ResultEntity.buildFailEntity()
                    .setMessage("添加门店服务具体收益数据不存在");
        }
    }

    @GetMapping("get_shop_earnings_info")
    @ApiOperation(value = "后台：查询该服务具体收益信息",notes = "通过服务收益id查看具体收益记录")
    public ResultEntity<List<FindShopServiceEarningInfoByEarningIdDtoVo>> getShopEarningsInfo(FindShopEarningIdParamVo findShopEarningIdParamVo){
        List<FindShopServiceEarningInfoByEarningIdDtoVo> findShopServiceEarningInfoByEarningIdDtoVos = shopEarningsInfoService.getShopEarningsInfo(findShopEarningIdParamVo);
        if (ObjectUtils.isEmpty(findShopServiceEarningInfoByEarningIdDtoVos)) {
            return ResultEntity.buildListFailEntity(FindShopServiceEarningInfoByEarningIdDtoVo.class)
                    .setMessage("还没有消费记录");
        }else {
            return ResultEntity.buildListSuccessEntity(FindShopServiceEarningInfoByEarningIdDtoVo.class)
                    .setMessage("查询该服务具体消费信息成功")
                    .setData(findShopServiceEarningInfoByEarningIdDtoVos);
        }
    }
}

