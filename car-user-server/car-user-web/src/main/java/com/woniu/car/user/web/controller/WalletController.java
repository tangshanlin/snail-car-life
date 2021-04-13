package com.woniu.car.user.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.user.param.AddwalletParam;
import com.woniu.car.user.param.DeleteWalletParam;
import com.woniu.car.user.param.UpdateWalletParam;
import com.woniu.car.user.web.domain.User;
import com.woniu.car.user.web.domain.Wallet;
import com.woniu.car.user.web.service.UserService;
import com.woniu.car.user.web.service.WalletService;
import com.woniu.car.user.web.util.GetTokenUtil;
import io.swagger.annotations.*;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * <p>
 * 钱包表 前端控制器
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/wallet")
@Api(tags = "用户钱包服务接口")
public class WalletController {
    @Resource
    private WalletService walletService;
    @Resource
    private UserService userService;

    //新增钱包方
    @PostMapping("/addWallet")
    @ApiOperation(value = "创建钱包接口", notes = "<span style='color:red;'>用来创建钱包接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1360, message = "创建钱包成功"),
            @ApiResponse(code = 1361, message = "创建钱包失败"),
            @ApiResponse(code = 1400, message = "输入参数错误")

    })

//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "integer", paramType = "path", example = "110"),
//            @ApiImplicitParam(name = "walletMoney",value = "钱包余额",dataType = "BigDecimal",type ="path",example = "100.00"),
//            @ApiImplicitParam(name = "walletPassword",value = "钱包密码",dataType = "String",type ="path",example = "123232")       })
    public ResultEntity addWallet(@RequestBody @Valid AddwalletParam addwalletParam){
        //校验输入参数
        //从jwt中获取userid
        Integer userId = GetTokenUtil.getUserId();
        User userDb = userService.getById(userId);
        if (!ObjectUtils.isEmpty(userDb)){
            //校验成功执行新增
            //查询是否存在
            Wallet walletDb = walletService.getOne(new QueryWrapper<Wallet>().eq("user_id", userId));
            if (!ObjectUtils.isEmpty(walletDb)){
                Wallet wallet = BeanCopyUtil.copyOne(addwalletParam, Wallet::new);
                boolean save = walletService.save(wallet);
                if (save) return ResultEntity.buildEntity().setCode(ConstCode.ADDWALLET_SUCCESS).setFlag(true).setMessage("创建钱包成功");
                return ResultEntity.buildEntity().setCode(ConstCode.ADDWALLET_FAIL).setFlag(false).setMessage("创建钱包失败");

            }

        }
        return ResultEntity.buildEntity().setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }
    //删除钱包方法
    @DeleteMapping("/deleteWallet")
    @ApiOperation(value = "删除钱包接口", notes = "<span style='color:red;'>用来删除钱包接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1362, message = "删除钱包成功"),
            @ApiResponse(code = 1363, message = "删除钱包失败"),
            @ApiResponse(code = 1400, message = "输入参数错误")

    })
//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "integer", paramType = "path", example = "110"),
//            @ApiImplicitParam(name = "walletId", value = "钱包ID", dataType = "integer", type = "path", example = "100.00"),
//    })
    public ResultEntity deleteWallet(@RequestBody @Valid DeleteWalletParam deleteWalletParam){
        //校验输入参数
        Integer userId = deleteWalletParam.getUserId();
        Integer walletId = deleteWalletParam.getWalletId();
        Wallet walletDb = walletService.getById(walletId);
        if (walletDb.getUserId()==userId){
            //校验成功进行删除
            boolean b = walletService.removeById(walletId);
            if (b) return ResultEntity.buildEntity().setCode(ConstCode.DELETEWALLET_SUCCESS).setFlag(true).setMessage("删除钱包成功");
            return ResultEntity.buildEntity().setCode(ConstCode.DELETEWALLET_FAIL).setFlag(false).setMessage("删除钱包失败");


        }
        return ResultEntity.buildEntity().setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }

    //修改余额的方法
    @PutMapping("/updateWallet")
    @ApiOperation(value = "修改钱包密码的接口",notes = "<span style='color:red;'>用来修改钱包密码的接口</span>" )
    @ApiResponses({
            @ApiResponse(code = 1364, message = "修改钱包成功"),
            @ApiResponse(code = 1365, message = "修改钱包失败"),
            @ApiResponse(code = 1400, message = "输入参数错误")

    })
//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "integer",  example = "110"),
//            @ApiImplicitParam(name = "walletId", value = "钱包ID", dataType = "integer",  example = "100.00"),
//            @ApiImplicitParam(name = "walletMoney",value = "钱包余额",dataType = "BigDecimal",example = "100.00"),
//            @ApiImplicitParam(name = "walletPassword",value = "钱包密码",dataType = "String",example = "123232")
//    })

    public ResultEntity updateWallet(@RequestBody @Valid UpdateWalletParam updateWalletParam){
        //参数校验
        Integer userId = GetTokenUtil.getUserId();

        Wallet walletDb = walletService.getOne(new QueryWrapper<Wallet>().eq("user_id",userId));
        if (!ObjectUtils.isEmpty(walletDb)){
            //校验成功 进行修改

            String walletPassword = updateWalletParam.getWalletPassword();

            if (walletPassword!=null){
                walletDb.setWalletPassword(walletPassword);
            }
            boolean b = walletService.updateById(walletDb);
            if (b) return ResultEntity.buildEntity().setCode(ConstCode.UPDATEWALLET_SUCCESS).setFlag(true).setMessage("修改钱包成功");
            return ResultEntity.buildEntity().setCode(ConstCode.UPDATEWALLET_FAIL).setFlag(false)
                    .setMessage("修改钱包成功");
        }
        return ResultEntity.buildEntity().setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }

    //查询钱包的方法
@GetMapping("/selectWallet")
@ApiOperation(value = "查询钱包的接口",notes = "<span style='color:red;'>用来查询钱包的接口</span>" )
@ApiResponses({
        @ApiResponse(code = 1366, message = "查询钱包成功"),
        @ApiResponse(code = 1367, message = "查询钱包失败"),
        @ApiResponse(code = 1400, message = "输入参数错误")

})
//@ApiImplicitParams({
//        //dataType:参数类型
//        //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//        @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "integer", paramType = "path", example = "110"),
//        @ApiImplicitParam(name = "walletId", value = "钱包ID", dataType = "integer", type = "path", example = "100.00"),
//
//})
    public ResultEntity<Wallet> selectWallet(){
        //校验参数

    //从token中获取userid
    Integer userId = GetTokenUtil.getUserId();


    if (userId!=null){
        //校验成功执行查询
        Wallet walletDb = walletService.getOne(new QueryWrapper<Wallet>().eq("user_id", userId));
        return ResultEntity.buildEntity(Wallet.class).setCode(ConstCode.SELECTWALLET_SUCCESS).setFlag(true).setMessage("查询钱包成功")
                .setData(walletDb);
    }
    return ResultEntity.buildEntity(Wallet.class).setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
}
}

