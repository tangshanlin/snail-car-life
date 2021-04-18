package com.woniu.car.shop.web.controller;


import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;

import com.woniu.car.shop.model.dtoVo.*;
import com.woniu.car.shop.model.paramVo.*;
import com.woniu.car.shop.web.domain.EsShopWoniuCar;
import com.woniu.car.shop.web.service.ShopService;
import io.swagger.annotations.*;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tsl
 * @since 2021-04-05
 */
@RestController
@Api(tags = "门店服务接口信息")
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private ShopService shopService;

    /**
     * 申请成为门店接口
     * @param addShopParamVo
     * @return
     */
    @PostMapping("add_shop")
    @ApiOperation(value = "申请成为门店接口")
    public ResultEntity addShop(@Valid AddShopParamVo addShopParamVo){
        shopService.addShopParamVo(addShopParamVo);
        return ResultEntity.buildSuccessEntity()
                .setMessage("添加成功");
    }

    /*
    * @Author TangShanLin
    * @Description TODO 门店通过审核接口
    * @Date  16:52
    * @Param [shopId]
    * @return com.woniu.car.commons.core.dto.ResultEntity
    **/
    @ApiOperation(value = "门店通过审核接口")
    @PutMapping("update_shop_account_start")
    public ResultEntity updateShopAccountStart(@RequestBody @Valid ShopIdParamVo shopId){
        Integer state = shopService.updateShopAccountStart(shopId);
        if(state == ConstCode.ACCESS_SUCCESS){
            return ResultEntity.buildSuccessEntity()
                    .setMessage("审核通过");
        } else if (state == ConstCode.GET_ACCOUNT_ROLE_FAIL){
            return ResultEntity.buildFailEntity()
                    .setCode(ConstCode.GET_ACCOUNT_ROLE_FAIL)
                    .setMessage("账号授予角色失败");
        }else if(state == ConstCode.ADD_END_ACCOUNT_FAIL){
            return ResultEntity.buildFailEntity()
                    .setCode(ConstCode.ADD_END_ACCOUNT_FAIL)
                    .setMessage("新增后台账户失败");
        }else {
            return ResultEntity.buildFailEntity()
                    .setCode(ConstCode.The_Store_Already_Exists)
                    .setMessage("门店不存在");
        }
    }


    /**
     * 根据门店id查询详细信息接口
     * @param shopId
     * @return
     */
    @ApiOperation(value = "根据门店id查询详细信息接口")
    @GetMapping("get_shop_info")
    public ResultEntity<FindShopInfoVo> findShopInfo(@Validated ShopIdParamVo shopId){
        FindShopInfoVo findShopInfoVo = shopService.findShopInfo(shopId);
        if (ObjectUtils.isEmpty(findShopInfoVo)) {
            return ResultEntity.buildFailEntity(FindShopInfoVo.class)
                    .setMessage("未查询到门店信息");
        }
        return ResultEntity.buildSuccessEntity(FindShopInfoVo.class)
                .setData(findShopInfoVo)
                .setMessage("根据门店id查询详细信息成功");
    }

    /**
     * 查询对应品牌的4s门店接口
     * @param findShopByClass
     * @return
     */
    @ApiOperation(value = "查询对应品牌的4s门店接口",notes = "如果没传品牌就查所有所有4s门店信息")
    @GetMapping("list_shop_by_class")
    public ResultEntity<List<FindShopByClassDtoVo>> findShopByClass(@Valid FindShopByClassParamVo findShopByClass){
        List<FindShopByClassDtoVo> findShopByClassDtoVoList = shopService.findShopByClass(findShopByClass);
        if(ObjectUtils.isEmpty(findShopByClassDtoVoList)){
            return ResultEntity.buildListFailEntity(FindShopByClassDtoVo.class)
                    .setMessage("未查询到该品牌下的4s门店");
        }
        return ResultEntity.buildListSuccessEntity(FindShopByClassDtoVo.class)
                .setData(findShopByClassDtoVoList)
                .setMessage("查询对应品牌的4s门店成功");
    }

    /*
    * @Author TangShanLin
    * @Description TODO 查询优选好店接口
    * @Date  15:36
    * @Param []
    * @return com.woniu.car.commons.core.dto.ResultEntity<java.util.List<com.woniu.car.shop.model.dtoVo.FindShopByIntegralDtoVo>>
    **/
    @ApiOperation(value = "查询优选好店接口",notes = "是展示门店信誉分在90之上的门店信息")
    @GetMapping("list_shop_by_integral")
    public ResultEntity<List<FindShopByIntegralDtoVo>> findShopByIntegral(){
        List<FindShopByIntegralDtoVo> findShopByIntegralDtoVoList = shopService.findShopByIntegral();
        if (ObjectUtils.isEmpty(findShopByIntegralDtoVoList)) {
            return ResultEntity.buildListFailEntity(FindShopByIntegralDtoVo.class)
                    .setMessage("暂无优选好店");
        }
        return ResultEntity.buildListSuccessEntity(FindShopByIntegralDtoVo.class)
                .setData(findShopByIntegralDtoVoList)
                .setMessage("查询优选好店成功");
    }

    /*
    * @Author TangShanLin
    * @Description TODO 查询所有附近门店接口(未写完)
    * @Date  15:36
    * @Param [meLngLat]
    * @return com.woniu.car.commons.core.dto.ResultEntity<java.util.List<com.woniu.car.shop.model.dtoVo.FindShopInfoAll>>
    **/
    @ApiOperation(value = "查询所有附近门店接口",notes = "功能暂时未完成")
    @GetMapping("list_shop_info_all")
    public ResultEntity<SearchHits<EsShopWoniuCar>> findShopInfoAll(@Valid FindShopInfoByMeLngLat meLngLat){
        System.out.println(meLngLat);
        SearchHits<EsShopWoniuCar> findShopInfoAllList = shopService.findShopInfoAll(meLngLat);
        return ResultEntity.buildSearchHitsSuccessEntity(EsShopWoniuCar.class)
                .setData(findShopInfoAllList)
                .setMessage("查询所有附近门店信息成功");
    }


    /*
    * @Author TangShanLin
    * @Description TODO 通过门店id查询门店名称接口
    * @Date  1:44
    * @Param [shopIdParamVo]
    * @return com.woniu.car.commons.core.dto.ResultEntity<com.woniu.car.shop.model.dtoVo.ShopNameDtoVo>
    **/
    @ApiOperation(value = "通过门店id查询门店名称接口")
    @GetMapping("get_shop_name_by_shop_id")
    public ResultEntity<ShopNameDtoVo> getShopNameByShopId(@Valid ShopIdParamVo shopIdParamVo){
        ShopNameDtoVo shopNameDtoVo = shopService.getShopNameByShopId(shopIdParamVo);
        if (ObjectUtils.isEmpty(shopIdParamVo)) {
            return ResultEntity.buildFailEntity(ShopNameDtoVo.class)
                    .setMessage("未查到门店名称");
        }else {
            return ResultEntity.buildSuccessEntity(ShopNameDtoVo.class)
                    .setData(shopNameDtoVo)
                    .setMessage("查询门店名称成功");
        }
    }

    /*
    * @Author TangShanLin
    * @Description TODO 通过门店id查询门店信誉分接口
    * @Date  17:54
    * @Param [shopIdParamVo]
    * @return com.woniu.car.commons.core.dto.ResultEntity<com.woniu.car.shop.model.dtoVo.ShopIntegralDtoVo>
    **/
    @ApiOperation(value = "通过门店id查询门店信誉分接口")
    @GetMapping("get_shop_integral_by_shop_id")
    public ResultEntity<ShopIntegralDtoVo> getShopIntegralByShopId(@Valid ShopIdParamVo shopIdParamVo){
        //先判断传入值是否为空
        if (ObjectUtils.isEmpty(shopIdParamVo)) {
            return ResultEntity.buildFailEntity(ShopIntegralDtoVo.class)
                    .setMessage("门店id传入为空");
        }

        ShopIntegralDtoVo shopIntegralDtoVo = shopService.getShopIntegralByShopId(shopIdParamVo);
        if (ObjectUtils.isEmpty(shopIntegralDtoVo)) {
            return ResultEntity.buildFailEntity(ShopIntegralDtoVo.class)
                    .setMessage("查询门店信誉积分为空");
        }else{
            return ResultEntity.buildSuccessEntity(ShopIntegralDtoVo.class)
                    .setData(shopIntegralDtoVo)
                    .setMessage("查询门店信誉积分成功");
        }
    }

    /*
    * @Author TangShanLin
    * @Description TODO 修改门店信誉分接口
    * @Date  9:34
    * @Param [shopIdParamVo]
    * @return com.woniu.car.commons.core.dto.ResultEntity
    **/
    @ApiOperation(value = "修改门店信誉分接口")
    @PutMapping("update_shop_integral_by_shop_id")
    public ResultEntity updateShopIntegralByShopId(@RequestBody @Valid ShopIdParamVo shopIdParamVo){
        //先判断传入值是否为空
        if (ObjectUtils.isEmpty(shopIdParamVo)) {
            return ResultEntity.buildFailEntity(ShopIntegralDtoVo.class)
                    .setMessage("门店id传入为空");
        }
        System.out.println(shopIdParamVo);
        Boolean boo= shopService.updateShopIntegralByShopId(shopIdParamVo);
        System.out.println(boo);
        if (boo) {
            return ResultEntity.buildSuccessEntity(ShopIntegralDtoVo.class)
                    .setMessage("修改门店信誉积分成功");
        }else{
            return ResultEntity.buildFailEntity(ShopIntegralDtoVo.class)
                    .setMessage("修改门店信誉积分失败");
        }
    }

    /*
    * @Author TangShanLin
    * @Description TODO 查询所有未审核的门店信息
    * @Date  13:14
    * @Param [shopStateParamVo]
    * @return com.woniu.car.commons.core.dto.ResultEntity<java.util.List<com.woniu.car.shop.model.dtoVo.FindShopInfoByStateDtoVo>>
    **/
    @ApiOperation(value = "查询所有未审核的门店信息",notes = "后端：根据审核状态字段为未审核查询所有门店信息")
    @GetMapping("api/list_shop_info_by_state")
    public ResultEntity<List<FindShopInfoByStateDtoVo>> listShopInfoByState(){
        List<FindShopInfoByStateDtoVo> findShopInfoByStateDtoVos = shopService.listShopInfoByState();
        if (ObjectUtils.isEmpty(findShopInfoByStateDtoVos)) {
            return ResultEntity.buildListFailEntity(FindShopInfoByStateDtoVo.class)
                    .setMessage("没有未审核的门店");
        }else {
            return ResultEntity.buildListSuccessEntity(FindShopInfoByStateDtoVo.class)
                    .setMessage("查询未审核的门店成功")
                    .setData(findShopInfoByStateDtoVos);
        }
    }

    @ApiOperation(value = "内部调用：修改门店总评分",notes = "feign：根据门店id和门店总评分修改评分")
    @PutMapping("update_shop_grade")
    public ResultEntity updateShopGrade(@RequestBody @Valid AmendShopGradeByShopIdParamVo amendShopGradeByShopIdParamVo){
        Boolean boo = shopService.updateShopGrade(amendShopGradeByShopIdParamVo);
        if (boo) {
            return ResultEntity.buildSuccessEntity()
                    .setMessage("修改总评分成功");
        }else {
            return ResultEntity.buildFailEntity()
                    .setMessage("修改总评分失败");
        }
    }

    @PutMapping("update_shop_order_number")
    @ApiOperation(value = "内部调用：修改门店总成交数",notes = "feign：每次完成后修改门店总成交数")
    public ResultEntity updateShopOrderNumber(@RequestBody @Valid ShopIdParamVo shopIdParamVo){
        Boolean boo = shopService.updateShopOrderNumber(shopIdParamVo);
        if (boo) {
            return ResultEntity.buildSuccessEntity()
                    .setMessage("修改门店总成交数成功");
        }else {
            return ResultEntity.buildFailEntity()
                    .setMessage("修改门店总成交数失败");
        }
    }

}

