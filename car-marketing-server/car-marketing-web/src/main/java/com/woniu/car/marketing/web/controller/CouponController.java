package com.woniu.car.marketing.web.controller;


import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.marketing.model.dtoVo.GetCouponAllDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetCouponBySourceDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetCouponNameDtoVo;
import com.woniu.car.marketing.model.paramVo.AddCouponParamVo;
import com.woniu.car.marketing.model.paramVo.GetCouponBySourceParamVo;
import com.woniu.car.marketing.model.paramVo.GetCouponIdParamVo;
import com.woniu.car.marketing.model.paramVo.UpdatePaySuccessCouponParamVo;
import com.woniu.car.marketing.web.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  优惠券类别服务接口信息
 * </p>
 *
 * @author tsl
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/coupon")
@Api(tags = "优惠券类别服务接口信息")
public class CouponController {

    @Resource
    private CouponService couponService;

    /*
    * @Author TangShanLin
    * @Description TODO 添加优惠券的后端接口
    * @Date  9:39
    * @Param [addCouponParamVo]
    * @return com.woniu.car.commons.core.dto.ResultEntity
    **/
    @ApiOperation(value = "添加优惠券的后端接口")
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "couponName",value = "优惠券名称"),
            @ApiImplicitParam(name="couponInfo",value = "优惠券说明"),
            @ApiImplicitParam(name="couponMoney",value = "优惠券面额(元)"),
            @ApiImplicitParam(name="couponCondition",value = "使用门槛（满多少可用）"),
            @ApiImplicitParam(name="couponGoods",value = "发行来源(0平台-其他对应门店id)"),
            @ApiImplicitParam(name="couponNumber",value = "总发行量"),
            @ApiImplicitParam(name="couponBeginTime",value = "生效时间"),
            @ApiImplicitParam(name="couponEndTime",value = "失效时间"),
    })
    @PostMapping("/api/add_coupon")
    public ResultEntity addCoupon(@RequestBody AddCouponParamVo addCouponParamVo){
        if (ObjectUtils.isEmpty(addCouponParamVo)) {
            return ResultEntity.buildFailEntity().setMessage("传入参数为空");
        }
        couponService.addCoupon(addCouponParamVo);
        return ResultEntity.buildSuccessEntity().setMessage("添加优惠券类别成功");
    }

    /*
    * @Author TangShanLin
    * @Description TODO 查询所有未失效优惠券类别信息后端接口
    * @Date  9:36
    * @Param
    * @return
    **/
    @ApiOperation(value = "查询所有未失效优惠券类别信息后端接口")
    @GetMapping("/api/list_coupon_all")
    public ResultEntity<List<GetCouponAllDtoVo>> listCouponAll(){
        List<GetCouponAllDtoVo> getCouponInfoAllDtoVoList = couponService.listCouponInfoAll();
        return ResultEntity.buildListSuccessEntity(GetCouponAllDtoVo.class)
                .setMessage("查询所有优惠券类成功")
                .setData(getCouponInfoAllDtoVoList);
    }

    /*
    * @Author TangShanLin
    * @Description TODO 订单支付后优惠券信息的修改接口
    * @Date  9:35
    * @Param [updatePaySuccessCouponParamVo]
    * @return com.woniu.car.commons.core.dto.ResultEntity
    **/
    @ApiOperation(value = "订单支付后优惠券信息的修改接口")
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "couponId",value = "优惠券主键id"),
            @ApiImplicitParam(name="couponInfoId",value = "用户优惠券详情id"),
    })
    @PutMapping("update_coupon_by_pay_success")
    public ResultEntity updateCouponByPaySuccess(@RequestBody UpdatePaySuccessCouponParamVo updatePaySuccessCouponParamVo){
        /*System.out.println(ObjectUtils.isEmpty(updatePaySuccessCouponParamVo));
        if (updatePaySuccessCouponParamVo.getCouponId()==null||updatePaySuccessCouponParamVo.getCouponInfoId()==null) {
            return ResultEntity.buildFailEntity().setMessage("传入参数为空");
        }*/
        couponService.updateCouponByPaySuccess(updatePaySuccessCouponParamVo);
        return ResultEntity.buildSuccessEntity()
                .setMessage("支付后优惠券信息的修改成功");
    }


    /*
    * @Author TangShanLin
    * @Description TODO 根据门店id查询当前门店可用的优惠券接口
    * @Date  12:10
    * @Param [getCouponBySourceParamVo]
    * @return com.woniu.car.commons.core.dto.ResultEntity<java.util.List<com.woniu.car.marketing.model.dtoVo.GetCouponBySourceDtoVo>>
    **/
    @ApiOperation(value = "根据门店id查询当前门店可用的优惠券接口")
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "couponGoods",value = "发行来源(0平台-其他对应门店id)"),
    })
    @GetMapping("list_coupon_by_source")
    public ResultEntity<List<GetCouponBySourceDtoVo>> getCouponBySource(GetCouponBySourceParamVo getCouponBySourceParamVo){
        List<GetCouponBySourceDtoVo> getCouponBySourceDtoVoList = couponService.getCouponBySource(getCouponBySourceParamVo);
        if (ObjectUtils.isEmpty(getCouponBySourceDtoVoList)) {
            return ResultEntity.buildListFailEntity(GetCouponBySourceDtoVo.class)
                    .setMessage("当前门店暂无优惠券发布");
        }else{
            return ResultEntity.buildListSuccessEntity(GetCouponBySourceDtoVo.class)
                    .setMessage("查询当前门店所有可用优惠券成功")
                    .setData(getCouponBySourceDtoVoList);
        }
    }

    /*
    * @Author TangShanLin
    * @Description TODO 通过优惠券类别id查询名称接口
    * @Date  0:59
    * @Param [getCouponIdParamVo]
    * @return com.woniu.car.commons.core.dto.ResultEntity<com.woniu.car.marketing.model.dtoVo.GetCouponNameDtoVo>
    **/
    @ApiOperation(value = "通过优惠券类别id查询名称接口")
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "couponId",value = "优惠券类别id"),
    })
    @GetMapping("get_coupon_name_by_coupon_id")
    public ResultEntity<GetCouponNameDtoVo> getCouponNameByCouponId(GetCouponIdParamVo getCouponIdParamVo){
        GetCouponNameDtoVo couponNameDtoVo = couponService.getCouponNameByCouponId(getCouponIdParamVo);
        if (ObjectUtils.isEmpty(couponNameDtoVo)) {
            return ResultEntity.buildFailEntity(GetCouponNameDtoVo.class)
                    .setMessage("没有门店名称");
        }else{
            return ResultEntity.buildSuccessEntity(GetCouponNameDtoVo.class)
                    .setData(couponNameDtoVo)
                    .setMessage("查询门店成功");
        }
    }

    /*
    * @Author TangShanLin
    * @Description TODO 通过优惠券分类id查看优惠券信息
    * @Date  17:35
    * @Param [getCouponIdParamVo]
    * @return com.woniu.car.commons.core.dto.ResultEntity<com.woniu.car.marketing.model.dtoVo.GetCouponBySourceDtoVo>
    **/
    @ApiOperation(value = "通过优惠券分类id查看优惠券信息")
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "couponId",value = "优惠券类别id"),
    })
    @GetMapping("get_coupon_by_id")
    public ResultEntity<GetCouponBySourceDtoVo> getCouponById(GetCouponIdParamVo getCouponIdParamVo){
        GetCouponBySourceDtoVo CouponBySourceDtoVo = couponService.getCouponById(getCouponIdParamVo);
        if (ObjectUtils.isEmpty(CouponBySourceDtoVo)) {
            return ResultEntity.buildFailEntity(GetCouponBySourceDtoVo.class)
                    .setMessage("没有优惠券类别信息");
        }else {
            return ResultEntity.buildSuccessEntity(GetCouponBySourceDtoVo.class)
                    .setMessage("查询优惠券类别信息成功")
                    .setData(CouponBySourceDtoVo);
        }
    }




}
