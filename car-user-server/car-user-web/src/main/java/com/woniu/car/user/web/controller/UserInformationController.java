package com.woniu.car.user.web.controller;


import cn.hutool.Hutool;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.core.exception.CarException;
import com.woniu.car.commons.web.util.BeanCopyUtil;

import com.woniu.car.user.param.UpdateUserInformationImageParam;
import com.woniu.car.user.param.UpdateUserinformationParam;
import com.woniu.car.user.web.domain.UserInformation;
import com.woniu.car.user.web.service.UserInformationService;
import com.woniu.car.user.web.util.GetTokenUtil;
import com.woniu.car.user.web.util.UserFileUpload;
import io.swagger.annotations.*;
import org.aspectj.weaver.ast.Var;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;

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
    @Resource
    private UserFileUpload userFileUpload;


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

            //时间转换
            DateTime parse = DateUtil.parse(updateUerinformationParam.getUserBirthday());
            long time = parse.getTime();
            System.out.println(time);
          userInformationDb.setUserBirthday(time);


            userInformationDb.setUserId(userId);

            userInformationDb.setUserName(updateUerinformationParam.getUserName());

            //执行新增
            boolean save = userInformationService.updateById(userInformationDb);
            if (save) return ResultEntity.buildEntity().setCode(ConstCode.ADDUSERINFORMATION_SUCCESS)
                    .setFlag(true).setMessage("新增用户详情信息成功");
            return ResultEntity.buildEntity().setCode(ConstCode.ADDUSERINFORMATION_FAIL).setFlag(false)
                    .setMessage("新增用户详情信息失败");

        }
        return  ResultEntity.buildEntity().setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }
  @PostMapping  ("/update-image")
  @ApiOperation(value = "更改用户头像接口",notes ="<span style='color:red;'>更改用户头像的接口</span>" )
  @ApiResponses({
          @ApiResponse(code = 1386,message = "头像更新成功"),
          @ApiResponse(code = 1387,message = "头像更新失败")
  })
    public ResultEntity updateImage(@Valid UpdateUserInformationImageParam updateUserInformationImageParam){
        //获取token中的userid
      Integer userId = GetTokenUtil.getUserId();
      if (ObjectUtils.isEmpty(userId)){
          throw new CarException("未登陆，请登录后再上传头像",500);
      }
      UserInformation userInforDb = userInformationService.getOne(new QueryWrapper<UserInformation>().eq("user_id", userId));

      MultipartFile[] multipartFiles = new MultipartFile[1];
     multipartFiles[0]=updateUserInformationImageParam.getFile();
//      multipartFiles[0]=updateUserInformationImageParam;
      ArrayList<String> upload = userFileUpload.upload(multipartFiles);
      String s = upload.get(0);
      userInforDb.setUserImage(s);
      boolean b = userInformationService.updateById(userInforDb);
      if (b)
          return ResultEntity.buildEntity().setCode(ConstCode.UPLOADUSERIMAGE_SUCESS).setFlag(true)
          .setMessage("更新头像成功");

      return ResultEntity.buildEntity().setCode(ConstCode.UPLOADUSERIMAGE_FAIL).setFlag(false)
              .setMessage("更新头像失败");
  }

}

