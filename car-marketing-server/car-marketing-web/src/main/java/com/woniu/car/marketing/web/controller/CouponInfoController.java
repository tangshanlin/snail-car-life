package com.woniu.car.marketing.web.controller;


import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.marketing.client.feign.FeignUserClient;
import com.woniu.car.marketing.model.dtoVo.GetCouponInfoByIdDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetCouponInfoByUserIdDtoVo;
import com.woniu.car.marketing.model.paramVo.AddUserGetCoupon;
import com.woniu.car.marketing.model.paramVo.GetCouponInfoByIdParamVo;
import com.woniu.car.marketing.model.paramVo.GetCouponInfoByUserIdAndSourceParamVo;
import com.woniu.car.marketing.web.service.CouponInfoService;
import com.woniu.car.user.web.domain.UserInformation;
import com.woniu.car.user.web.util.GetTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  用户领取优惠券服务接口信息
 * </p>
 *
 * @author tsl
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/coupon-info")
@Api(tags = "用户领取优惠券服务接口信息")
public class CouponInfoController {

    @Resource
    private CouponInfoService couponInfoService;

    /**
     * 用户在订单中展示所有符合条件的优惠券
     * @param getCouponInfoByUserIdAndSourceParamVo
     * @return
     */
    @ApiOperation(value = "用户在订单中展示所有符合条件的优惠券")
    @GetMapping("/list_coupon_info_by_user_id")
    public ResultEntity<List<GetCouponInfoByIdDtoVo>> getCouponInfoByUserIdAll(@Valid GetCouponInfoByUserIdAndSourceParamVo getCouponInfoByUserIdAndSourceParamVo){
        List<GetCouponInfoByIdDtoVo> getCouponInfoByIdDtoVoList = couponInfoService.getCouponInfoByUserIdAll(getCouponInfoByUserIdAndSourceParamVo);
        return ResultEntity.buildListSuccessEntity(GetCouponInfoByIdDtoVo.class)
                .setData(getCouponInfoByIdDtoVoList)
                .setMessage("查询用户自己优惠券信息成功");
    }

    /**
     * 通过用户优惠券主键id查优惠券信息
     * @param getCouponInfoByIdParamVo
     * @return
     */
    @ApiOperation(value = "通过用户领取优惠券表主键id查优惠券信息")
    @GetMapping("/get_coupon_info_by_id")
    public ResultEntity<GetCouponInfoByIdDtoVo> getCouponInfoById(@Valid GetCouponInfoByIdParamVo getCouponInfoByIdParamVo){
        GetCouponInfoByIdDtoVo getCouponInfoByIdDtoVo = couponInfoService.getCouponInfoById(getCouponInfoByIdParamVo);
        return ResultEntity.buildSuccessEntity(GetCouponInfoByIdDtoVo.class)
                .setData(getCouponInfoByIdDtoVo)
                .setMessage("查询用户具体某个优惠券成功");
    }

    /*
    * @Author TangShanLin
    * @Description TODO 用户领取优惠券接口
    * @Date  10:13
    * @Param [addUserGetCoupon]
    * @return com.woniu.car.commons.core.dto.ResultEntity
    **/
    @ApiOperation(value = "用户领取优惠券接口")
    @PostMapping("/add_user_get_coupon")
    public ResultEntity addUserGetCoupon(@RequestBody @Valid AddUserGetCoupon addUserGetCoupon){
        Boolean verdict = couponInfoService.addUserGetCoupon(addUserGetCoupon);
        if(verdict){
            return ResultEntity.buildSuccessEntity().setMessage("领取成功");
        }else{
            return ResultEntity.buildFailEntity().setMessage("该优惠券发放完毕，领取失败");
        }
    }

    /*
    * @Author TangShanLin
    * @Description TODO 根据用户id返回未过期的优惠券信息
    * @Date  11:49
    * @Param [couponInfoByUserIdParamVo]
    * @return com.woniu.car.commons.core.dto.ResultEntity<java.util.List<com.woniu.car.marketing.model.dtoVo.GetCouponInfoByUserIdDtoVo>>
    **/
    @ApiOperation(value = "根据用户id返回未过期的优惠券信息")
    @GetMapping("list_coupon_info_by_user_id_all")
    public ResultEntity<List<GetCouponInfoByUserIdDtoVo>> listCouponInfoByUserId(){
        List<GetCouponInfoByUserIdDtoVo> getCouponInfoByUserIdDtoVoList = couponInfoService.listCouponInfoByUserId();
        if (ObjectUtils.isEmpty(getCouponInfoByUserIdDtoVoList)) {
            return ResultEntity.buildListFailEntity(GetCouponInfoByUserIdDtoVo.class)
                    .setMessage("没有可使用的优惠券");
        }else {
            return ResultEntity.buildListSuccessEntity(GetCouponInfoByUserIdDtoVo.class)
                    .setMessage("查询优惠券成功")
                    .setData(getCouponInfoByUserIdDtoVoList);
        }
    }

    /*
    * @Author TangShanLin
    * @Description TODO 消耗积分领取优惠券
    * @Date  13:12
    * @Param [addUserGetCoupon]
    * @return com.woniu.car.commons.core.dto.ResultEntity
    **/
    @ApiOperation(value = "用户通过积分领取优惠券接口")
    @PostMapping("/add_user_get_coupon_by_credits")
    public ResultEntity addUserGetCouponByCredits(@RequestBody @Valid AddUserGetCoupon addUserGetCoupon){
        Integer verdict = couponInfoService.addUserGetCouponByCredits(addUserGetCoupon);
        if(verdict==1){
            return ResultEntity.buildSuccessEntity()
                    .setMessage("领取成功");

        }else if (verdict==0){
            return ResultEntity.buildFailEntity()
                    .setMessage("该优惠券发放完毕，领取失败")
                    .setCode(ConstCode.ADD_COUPON_GET_FAIL);
        }else {
            return ResultEntity.buildFailEntity()
                    .setMessage("积分不足兑换该优惠券")
                    .setCode(ConstCode.NOT_ENOUGH_POINTS_FAIL);
        }
    }



}

