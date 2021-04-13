package com.woniu.car.marketing.web.controller;


import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.marketing.model.dtoVo.AdvertisingInfoBySourceAllDtoVo;
import com.woniu.car.marketing.model.dtoVo.GetAdvertisingByAuditAllDtoVo;
import com.woniu.car.marketing.model.paramVo.AddAdvertising;
import com.woniu.car.marketing.model.paramVo.AdvertisingByAuditParamVo;
import com.woniu.car.marketing.model.paramVo.AdvertisingBySourceParamVo;
import com.woniu.car.marketing.model.paramVo.UpdateAdvertisingIdParamVo;
import com.woniu.car.marketing.web.service.AdvertisingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  活动服务接口信息
 * </p>
 *
 * @author tsl
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/advertising")
@Api(tags = "活动服务接口信息")
public class AdvertisingController {

    @Resource
    private AdvertisingService advertisingService;

    /*
    * @Author TangShanLin
    * @Description TODO 活动创建申请
    * @Date  15:08
    * @Param []
    * @return com.woniu.car.commons.core.dto.ResultEntity
    **/
    @ApiOperation(value = "活动创建申请")
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "MultipartFile[]",value = "活动图片"),
            @ApiImplicitParam(name="advertisingSourceId",value = "发布来源(0平台其他对应门店对应id)"),
            @ApiImplicitParam(name="couponId",value = "关联优惠券id"),
            @ApiImplicitParam(name="advertisingExplain",value = "活动说明"),
            @ApiImplicitParam(name="advertisingBeginTime",value = "活动开始时间"),
            @ApiImplicitParam(name="advertisingEndTime",value = "活动结束时间"),
    })
    @PostMapping("api/add_advertising")
    public ResultEntity addAdvertising(AddAdvertising addAdvertising){
        Boolean verdict = advertisingService.addAdvertising(addAdvertising);
        if(verdict) return ResultEntity.buildSuccessEntity().setMessage("申请消息发送成功");
        return ResultEntity.buildFailEntity().setMessage("申请失败");
    }

    /*
    * @Author TangShanLin
    * @Description TODO 后端活动审核通过接口
    * @Date  23:43
    * @Param []
    * @return com.woniu.car.commons.core.dto.ResultEntity
    **/
    @ApiOperation(value = "后端活动审核通过接口")
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "advertisingId",value = "活动主键id"),
    })
    @PutMapping("api/update_advertising_pass")
    public ResultEntity updateAdvertisingPass(@RequestBody UpdateAdvertisingIdParamVo updateAdvertisingIdParamVo){
        System.out.println(updateAdvertisingIdParamVo);
        Boolean verdict = advertisingService.updateAdvertisingPass(updateAdvertisingIdParamVo);
        if(verdict) return ResultEntity.buildSuccessEntity().setMessage("审核通过");
        return ResultEntity.buildFailEntity().setMessage("审核失败");
    }

    /*
     * @Author TangShanLin
     * @Description TODO 后端活动审核拒绝接口
     * @Date  23:43
     * @Param []
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @ApiOperation(value = "后端活动审核拒绝接口")
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "advertisingId",value = "活动主键id"),
    })
    @PutMapping("api/update_advertising_refuse")
    public ResultEntity updateAdvertisingRefuse(@RequestBody UpdateAdvertisingIdParamVo updateAdvertisingIdParamVo){
        Boolean verdict = advertisingService.updateAdvertisingRefuse(updateAdvertisingIdParamVo);
        if(verdict) return ResultEntity.buildSuccessEntity().setMessage("审核拒绝完成");
        return ResultEntity.buildFailEntity().setMessage("审核失败");
    }

    /*
    * @Author TangShanLin
    * @Description TODO 后端根据审核状态查看活动信息接口
    * @Date  11:45
    * @Param []
    * @return com.woniu.car.commons.core.dto.ResultEntity<java.util.List<com.woniu.car.marketing.model.dtoVo.GetAdvertisingByAuditAll>>
    **/
    @ApiOperation(value = "后端根据审核状态查看活动信息接口")
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "advertisingAudit",value = "审核状态（0未审核1已审核2拒绝）"),
    })
    @GetMapping("api/list_advertising_by_audit_all")
    public ResultEntity<List<GetAdvertisingByAuditAllDtoVo>> listAdvertisingByAuditAll(AdvertisingByAuditParamVo getListAdvertisingByAuditAll){
        List<GetAdvertisingByAuditAllDtoVo> listGetAdvertisingByAuditAll =advertisingService.listAdvertisingByAuditAll(getListAdvertisingByAuditAll);
        if (ObjectUtils.isEmpty(listGetAdvertisingByAuditAll)) {
            return ResultEntity.buildListFailEntity(GetAdvertisingByAuditAllDtoVo.class)
                    .setMessage("未查到活动信息");

        }else {
            return ResultEntity.buildListSuccessEntity(GetAdvertisingByAuditAllDtoVo.class)
                    .setData(listGetAdvertisingByAuditAll)
                    .setMessage("查询活动信息成功");
        }
    }

    /*
    * @Author TangShanLin
    * @Description TODO 前端传入活动来源（0平台其他对应门店id）展示活动信息
    * @Date  16:12
    * @Param [advertisingBySourceParamVo]
    * @return com.woniu.car.commons.core.dto.ResultEntity<java.util.List<com.woniu.car.marketing.model.dtoVo.AdvertisingInfoBySourceAllDtoVo>>
    **/
    @ApiOperation(value = "前端传入活动来源（0平台其他对应门店id）展示活动信息")
    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "advertisingSourceId",value = "发布来源(0平台其他对应门店对应id)"),
    })
    @GetMapping("list_advertising_by_source")
    public ResultEntity<List<AdvertisingInfoBySourceAllDtoVo>> listAdvertisingBySource(AdvertisingBySourceParamVo advertisingBySourceParamVo){
        List<AdvertisingInfoBySourceAllDtoVo> advertisingInfoBySourceAllDtoVoList = advertisingService.listAdvertisingBySource(advertisingBySourceParamVo);
        if (ObjectUtils.isEmpty(advertisingInfoBySourceAllDtoVoList)) {
            return ResultEntity.buildListFailEntity(AdvertisingInfoBySourceAllDtoVo.class)
                    .setMessage("暂无活动信息");
        }
        return ResultEntity.buildListSuccessEntity(AdvertisingInfoBySourceAllDtoVo.class)
                .setMessage("查询成功")
                .setData(advertisingInfoBySourceAllDtoVoList);
    }

}

