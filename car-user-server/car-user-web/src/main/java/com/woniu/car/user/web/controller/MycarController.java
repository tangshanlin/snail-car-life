package com.woniu.car.user.web.controller;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.user.param.AddMycarParam;
import com.woniu.car.user.param.DeleteMycarParam;
import com.woniu.car.user.param.UpdateMyCarParam;
import com.woniu.car.user.web.domain.Mycar;
import com.woniu.car.user.web.service.MycarService;
import com.woniu.car.user.web.util.GetTokenUtil;
import io.swagger.annotations.*;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 我的爱车 前端控制器
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-12
 */
@RestController
@Api(tags = "爱车服务接口")
@RequestMapping("/mycar")
public class MycarController {
    @Resource
    private MycarService mycarService;

    /*查询我的爱车*/
    @GetMapping("/select_mycar")
    @ApiOperation(value = "查询我的爱车的接口",notes = "<span style='color:red;'>用来查询我的爱车的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1332, message = "查询我的爱车成功"),
            @ApiResponse(code = 1333, message = "查询我的爱车失败"),
    })

    public ResultEntity<List<Mycar>> selectMycar(){
        //从token获取userID
        Integer userId = GetTokenUtil.getUserId();
        //根据userId 查询selectMycar
        List<Mycar> mycarList = mycarService.list(new QueryWrapper<Mycar>().eq("user_id", userId));
        Mycar mycarDb = (Mycar) mycarList;
        return ResultEntity.buildListEntity(Mycar.class).setCode(ConstCode.SELECTMYCAR_SUCCESS).setFlag(true)
                .setMessage("查询我的爱车成功");
    }

    /*新增我的爱车信息接口*/
    @PostMapping("/add_mycar")
    @ApiOperation(value = "新增我的爱车信息接口",notes = "<span style='color:red;'>用来查询我的爱车的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1334,message = "新增我的爱车成功"),
            @ApiResponse(code = 1335,message = "新增我的爱车失败"),
            @ApiResponse(code = 1400,message = "输入参数有误")
    })
//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//
//            @ApiImplicitParam(name = "userId", value = "用户Id", dataType = "Integer",  example = "1"),
//            @ApiImplicitParam(name = "mycarTypeId", value = "车型ID", dataType = "Integer", example = "77"),
//            @ApiImplicitParam(name = "mycarBrand" , value = "车品牌", dataType = "String",example = "宝马"),
//            @ApiImplicitParam(name = "mucarType", value = "款型", dataType = "String",  example = "宝马740"),
//            @ApiImplicitParam(name = "mycarImage", value = "爱车图片", dataType = "String", example = "baidu.com"),
//            @ApiImplicitParam(name = "mycarKm", value = "里程", dataType = "Integr", example = "110"),
//            @ApiImplicitParam(name = "mycarCode", value = "车牌号", dataType = "String",example = "渝A110"),
//            @ApiImplicitParam(name = "mycarProductionDate", value = "生产年份", dataType = "Long", example = "2016"),
//            @ApiImplicitParam(name = "mycarOntheroadTime", value = "爱车上路时间", dataType = "Long", example = "2020年10月"),
//            @ApiImplicitParam(name = "mycarRegisterTime", value = "注册时间", dataType = "String",  example = "2020年10月"),
//            @ApiImplicitParam(name = "mycarEngineCapaciTy", value = "发动机排量", dataType = "String",  example = "2L"),
//
//    })
    public ResultEntity addMycar(@RequestBody @Valid AddMycarParam addMycarParam){
        //校验输入参数
        Integer userId = addMycarParam.getUserId();
        //从jwt中获取useid
        Integer userIdToken= GetTokenUtil.getUserId();


        if (!ObjectUtils.isEmpty(addMycarParam)&userId==userIdToken){
            //校验成功添加
            Mycar mycar = BeanCopyUtil.copyOne(addMycarParam, Mycar::new);
            boolean save = mycarService.save(mycar);
            if(save) return ResultEntity.buildEntity().setCode(ConstCode.ADDMYCAR_SUCCESS).setFlag(true)
                    .setMessage("添加我的爱车信息成功");
            return ResultEntity.buildEntity().setCode(ConstCode.ADDMYCAR_FAIL).setFlag(true)
                    .setMessage("添加我的爱车失败");

        }
        return  ResultEntity.buildEntity().setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }

    /*修改我的爱车*/
    @PutMapping("/update_mycar")
    @ApiOperation(value = "修改我的爱车接口" ,notes = "<span style='color:red;'>用来查询我的爱车的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1336,message = "修改我的爱车成功"),
            @ApiResponse(code = 1336,message = "修改我的爱车失败"),
            @ApiResponse(code = 1400,message = "输入参数错误")
    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId", value = "用户Id", dataType = "Integer",  example = "1"),
//            @ApiImplicitParam(name = "mycarKm", value = "里程", dataType = "Integr",  example = "2700")
//    })
    public ResultEntity updateMycar(@RequestBody @Valid UpdateMyCarParam updateMyCarParam){
        //校验参数
        Integer userId = updateMyCarParam.getUserId();
        Integer mycarKm = updateMyCarParam.getMycarKm();
        if (userId!=null&mycarKm!=null){
            //从jwt中取出userID
            Integer userIdToken = GetTokenUtil.getUserId();
            if (userId==userIdToken){
                //开始修改
                Mycar mycarDb = mycarService.getOne(new QueryWrapper<Mycar>().eq("user_id", userId));
                mycarDb.setMycarKm(mycarKm);
                boolean b = mycarService.updateById(mycarDb);
                if (b) return ResultEntity.buildEntity().setCode(ConstCode.UPDATEMYCAR_SUCCESS).setFlag(true)
                .setMessage("修改爱车成功");
                return ResultEntity.buildEntity().setCode(ConstCode.UPDATEMYCAR_FAIL).setFlag(false)
                        .setMessage("修改爱车失败");

            }
        }
        return  ResultEntity.buildEntity().setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");

    }
    /*删除我的爱车功能*/
    @DeleteMapping("/delete_mycar")
    @ApiOperation(value = "删除我的爱车接口",notes = "<span style='color:red;'>用来查询我的爱车的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1338,message = "删除爱车成功"),
            @ApiResponse(code = 1339,message = "删除爱车失败")
    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId", value = "用户Id", dataType = "Integer",  example = "1"),
//            @ApiImplicitParam(name = "mycarId", value = "爱车ID", dataType = "Integr",  example = "1")
//    })
    public ResultEntity deleteMycar(@RequestBody @Valid DeleteMycarParam deleteMycarParam){
        Integer mycarId = deleteMycarParam.getMycarId();
        Integer userId = deleteMycarParam.getUserId();
        if (mycarId!=null&userId!=null){
            //从jwt中获取userid
            Integer userIdToken = GetTokenUtil.getUserId();
            //校验
            if (userId==userIdToken){
                //校验成功
                //执行删除
                boolean b = mycarService.removeById(mycarId);
                if (b) return ResultEntity.buildEntity().setCode(ConstCode.DELETEMYCAR_SUCCESS).setFlag(true)
                .setMessage("删除爱车成功");
                return ResultEntity.buildEntity().setCode(ConstCode.DELETEMYCAR_FAIL).setFlag(false)
                        .setMessage("删除爱车失败");
            }
        }

        return  ResultEntity.buildEntity().setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");

    }

}

