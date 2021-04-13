package com.woniu.car.user.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.user.param.AddDriverLicenseParam;
import com.woniu.car.user.web.domain.DriverLicense;
import com.woniu.car.user.web.service.DriverLicenseService;
import com.woniu.car.user.web.util.GetTokenUtil;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 驾驶证 前端控制器
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-09
 */
@RestController
@RequestMapping("/driver-license")
@Api(tags = "驾驶证服务接口")
public class DriverLicenseController {
    @Resource
    private DriverLicenseService driverLicenseService;
    //新增驾驶证
    @PostMapping("/add_driverLicense")
    @ApiOperation(value = "添加驾驶证接口", notes = "<span style='color:red;'>用来添加驾驶证的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1320, message = "添加驾驶证成功"),
            @ApiResponse(code = 1321, message = "添加驾驶证失败"),


    })

//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//
//
//            @ApiImplicitParam(name = "userRelalName", value = "证件真实名", dataType = "String",  example = "马化腾"),
//            @ApiImplicitParam(name = "driverlicensePassportNo", value = "证件号码", dataType = "String",  example = "15578491030"),
//            @ApiImplicitParam(name = "driverlicenseUserGender" , value = "性别", dataType = "String", example = "男"),
//            @ApiImplicitParam(name = "driverlicenseNation", value = "国籍", dataType = "String",  example = "中国"),
//            @ApiImplicitParam(name = "driverlicenseAddress", value = "住址", dataType = "String",  example = "重庆市"),
//            @ApiImplicitParam(name = "driverlicenseCreate", value = "初次领证时间", dataType = "Long", example = "2020年3月5日"),
//            @ApiImplicitParam(name = "driverlicenseStarttime", value = "准驾车型", dataType = "String",  example = "C1"),
//            @ApiImplicitParam(name = "driverlicenseStarttime", value = "有效期开始时间", dataType = "Long",example = "2020年3月5日"),
//            @ApiImplicitParam(name = "driverlicenseEndtime", value = "有效期结束时间", dataType = "Long",example = "2020年5月5日")
//
//
//
//    })
    public ResultEntity addDriverLicense(@RequestBody @Valid AddDriverLicenseParam addDriverLicenseParam){
        //从jwt中获取userid
        Integer userId = GetTokenUtil.getUserId();
        //开始新增
        DriverLicense driverLicense = BeanCopyUtil.copyOne(addDriverLicenseParam, DriverLicense::new);
        driverLicense.setUserId(userId);
        boolean save = driverLicenseService.save(driverLicense);
        if (save) return ResultEntity.buildEntity().setCode(ConstCode.ADDDRIVERLICENSE_SUCCESS)
                .setFlag(true).setMessage("新增驾驶证成功");
        return ResultEntity.buildEntity().setCode(ConstCode.ADDDRIVERLICENSE_FAIL).setFlag(false)
                .setMessage("新增驾驶证失败");

    }

    @GetMapping("/select_driverlicense")
    @ApiOperation(value = "查询驾驶证接口",notes = "<span style='color:red;'>用来添加驾驶证的接口</span>")
    @ApiResponses({
            @ApiResponse(code =1322,message = "查询驾驶证成功"),
            @ApiResponse(code = 1323,message = "查询驾驶证失败")
    })
    public ResultEntity<DriverLicense> selectDriverlicense(){
        //从jwt中获取userid
        Integer userId = GetTokenUtil.getUserId();
        //执行查询
        DriverLicense driverLicense = driverLicenseService.getOne(new QueryWrapper<DriverLicense>().eq("user_id",userId));
        return ResultEntity.buildEntity(DriverLicense.class).setCode(ConstCode.SELECTDRIVERLICENSE_SUCCESS)
                .setFlag(true).setMessage("查询驾驶证成功").setData(driverLicense);

    }


}

