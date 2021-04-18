package com.woniu.car.shop.web.controller;


import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.shop.model.dtoVo.FindShopServiceEarningAllByShopIdDtoVo;
import com.woniu.car.shop.model.paramVo.AddShopServiceEarningsParamVo;
import com.woniu.car.shop.model.paramVo.ShopIdParamVo;
import com.woniu.car.shop.web.service.ShopEarningsInfoService;
import com.woniu.car.shop.web.service.ShopServiceEarningsService;
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

    /*
    * @Author TangShanLin
    * @Description TODO 后端调用，门店后端查看自己1服务收益
    * @Date  10:22
    * @Param [shopIdParamVo]
    * @return com.woniu.car.commons.core.dto.ResultEntity<java.util.List<com.woniu.car.shop.model.dtoVo.FindShopServiceEarningAllByShopIdDtoVo>>
    **/
    @GetMapping("get_shop_service_earnings")
    @ApiOperation(value = "后台查看门店服务具体收益数据",notes = "后台查看门店每个服务总收益数据")
    public ResultEntity<List<FindShopServiceEarningAllByShopIdDtoVo>> getShopServiceEarnings(ShopIdParamVo shopIdParamVo){
        List<FindShopServiceEarningAllByShopIdDtoVo> findShopServiceEarningAllByShopIdDtoVoList = shopServiceEarningsService.getShopServiceEarnings(shopIdParamVo);
        if (ObjectUtils.isEmpty(findShopServiceEarningAllByShopIdDtoVoList)) {
            return ResultEntity.buildListFailEntity(FindShopServiceEarningAllByShopIdDtoVo.class)
                    .setMessage("还未添加服务，没有收益信息");
        }else {
            return ResultEntity.buildListSuccessEntity(FindShopServiceEarningAllByShopIdDtoVo.class)
                    .setMessage("查询收益信息成功")
                    .setData(findShopServiceEarningAllByShopIdDtoVoList);
        }
    }



}

