package com.woniu.car.user.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.web.util.BeanCopyUtil;

import com.woniu.car.user.param.UpdateUserinformationParam;
import com.woniu.car.user.web.domain.UserInformation;
import com.woniu.car.user.web.service.UserInformationService;
import com.woniu.car.user.web.util.GetTokenUtil;
import io.swagger.annotations.*;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 用户信息明细表 前端控制器
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/user-information")
@Api(tags = "用户详情服务接口")
public class UserInformationController {
    @Resource
    private UserInformationService userInformationService;

    /*
     * @Author SuShanHu
     * @Description TODO 查询用户详情信息的接口
     * @Date  2021/4/10
     * @Param
     * @return
     **/
    @GetMapping("/selectUerInformation")
    @ApiOperation(value = "查询用户详情信息",notes = "<span style='color:red;'>查询用户详情的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1328 ,message = "查询用户详情信息成功"),
            @ApiResponse(code = 1329,message = "查询用户详情信息成功")
    })


    public ResultEntity<UserInformation> selectUerInformation(){
        //获取userId
        Integer userId = GetTokenUtil.getUserId();

        //根据userid查询详细信息
        UserInformation userInformation = userInformationService.getOne(new QueryWrapper<UserInformation>().eq("user_id", userId));

        return ResultEntity.buildEntity(UserInformation.class).setCode(ConstCode.SELECTUSERINFORMATION_SUCCESS)
                .setFlag(true).setMessage("查询用户详情信息成功").setData(userInformation);

    }

    @PutMapping("/updateUserInformation")
    @ApiOperation(value = "新增用户信息接口",notes ="<span style='color:red;'>新增用户详情的接口</span>" )
    @ApiResponses({
            @ApiResponse(code =1330,message = "新增用户详情成功"),
            @ApiResponse(code =1331,message = "新增用户详情失败"),
            @ApiResponse(code =1400,message = "输入参数错误")
    })
//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//
//            @ApiImplicitParam(name = "userName", value = "用户昵称", dataType = "String",  example = "tom"),
//            @ApiImplicitParam(name = "userBirthday", value = "用户生日", dataType = "Long",  example = "10月1日"),
//            @ApiImplicitParam(name = "userImage" , value = "用户头像", dataType = "String",  example = "15578491030"),
//
//    })

    public ResultEntity  updateUserInformation(@RequestBody @Valid UpdateUserinformationParam updateUerinformationParam){
        //校验用户参数是否错误
        if (!ObjectUtils.isEmpty(updateUerinformationParam)){
            //从jwt中取出userid
            Integer userId = GetTokenUtil.getUserId();
            UserInformation userInformationDb = userInformationService.getOne(new QueryWrapper<UserInformation>().eq("user_id",userId));
            UserInformation userInformationCopy
                    = BeanCopyUtil.copyOne(updateUerinformationParam,UserInformation::new);
            userInformationCopy.setUserId(userId);
            userInformationCopy.setUserScore(userInformationDb.getUserScore());
            userInformationCopy.setWalletMoney(userInformationDb.getWalletMoney());
            userInformationCopy.setUserTel(userInformationDb.getUserTel());

            //执行新增
            boolean save = userInformationService.save(userInformationCopy);
            if (save) return ResultEntity.buildEntity().setCode(ConstCode.ADDUSERINFORMATION_SUCCESS)
                    .setFlag(true).setMessage("新增用户详情信息成功");
            return ResultEntity.buildEntity().setCode(ConstCode.ADDUSERINFORMATION_FAIL).setFlag(false)
                    .setMessage("新增用户详情信息失败");

        }
        return  ResultEntity.buildEntity().setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }


}

