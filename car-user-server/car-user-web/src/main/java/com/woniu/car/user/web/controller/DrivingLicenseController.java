package com.woniu.car.user.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.user.param.AddDrivingLicenseParam;
import com.woniu.car.user.web.domain.DrivingLicense;
import com.woniu.car.user.web.service.DrivingLicenseService;
import com.woniu.car.user.web.util.GetTokenUtil;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 行驶证 前端控制器
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-13
 */
@RestController
@RequestMapping("/driving-license")
@Api(tags = "行驶证服务")
public class DrivingLicenseController {
    @Resource
    private DrivingLicenseService drivingLicenseService;


    //新增驾驶证
    @PostMapping("/addDrivingLicense")
    @ApiOperation(value = "添加驾驶证接口", notes = "<span style='color:red;'>用来添加驾驶证的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1324, message = "添加行驶证成功"),
            @ApiResponse(code = 1325, message = "添加行驶证失败"),


    })

//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//
//
//            @ApiImplicitParam(name = "drivinglicenseCode", value = "行驶证编号", dataType = "String",  example = "A110"),
//            @ApiImplicitParam(name = "drivinglicenseAddress", value = "住址", dataType = "String",  example = "重庆渝北蜗牛之家"),
//            @ApiImplicitParam(name = "drivinglicenseDate" , value = "发证日期", dataType = "Long",  example = "2016年10月1日"),
//            @ApiImplicitParam(name = "drivinglicenseBrand", value = "品牌型号", dataType = "String",  example = "宝马740"),
//            @ApiImplicitParam(name = "drivinglicenseCartype", value = "车辆类型", dataType = "String",  example = "C1"),
//            @ApiImplicitParam(name = "userRealName", value = "持证人", dataType = "String",  example = "张三"),
//            @ApiImplicitParam(name = "drivinglicenseType", value = "使用性质", dataType = "Integer", example = "C1"),
//            @ApiImplicitParam(name = "engineNumber", value = "发动机号码", dataType = "String",  example = "kksy"),
//            @ApiImplicitParam(name = "carCode", value = "车牌号", dataType = "Long",  example = "渝A110"),
//            @ApiImplicitParam(name = "registerDate", value = "注册日期", dataType = "Long",  example = "2010年10月5日"),
//
//
//
//    })
    public ResultEntity addDrivingLicense(@RequestBody @Valid AddDrivingLicenseParam addDrivingLicenseParam){
        //从jwt中获取userid
        Integer userId = GetTokenUtil.getUserId();
        //开始新增
        DrivingLicense drivingLicense = BeanCopyUtil.copyOne(addDrivingLicenseParam, DrivingLicense::new);
        drivingLicense.setUserId(userId);
        boolean save = drivingLicenseService.save(drivingLicense);
        if (save) return ResultEntity.buildEntity().setCode(ConstCode.ADDDRIVERLICENSE_SUCCESS)
                .setFlag(true).setMessage("新增行驶证成功");
        return ResultEntity.buildEntity().setCode(ConstCode.ADDDRIVERLICENSE_FAIL).setFlag(false)
                .setMessage("新增行驶证失败");


    }

    //查询行驶证接口
    @GetMapping("/selectDrivingLicense")
    @ApiOperation(value = "查询行驶证的接口",notes = "<span style='color:red;'>用来添加驾驶证的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1326, message = "添加行驶证成功"),
            @ApiResponse(code = 1327, message = "添加行驶证成功")
    })
    public ResultEntity<DrivingLicense> selectDrivingLicense(){
        //从jwt中获取userid
        Integer userId = GetTokenUtil.getUserId();
        DrivingLicense drivingLicense = drivingLicenseService.getOne(new QueryWrapper<DrivingLicense>().eq("user_id", userId));

        return ResultEntity.buildEntity(DrivingLicense.class).setCode(ConstCode.SELECTDRIVINGLICENSE_SUCCESS)
                .setFlag(true).setMessage("查询行驶证成功");

    }

}

