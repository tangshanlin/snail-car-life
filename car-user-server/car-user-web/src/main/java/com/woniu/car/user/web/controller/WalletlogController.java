package com.woniu.car.user.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.user.param.AddWalletLogParam;
import com.woniu.car.user.param.SelectWalletLogParam;
import com.woniu.car.user.web.domain.Wallet;
import com.woniu.car.user.web.domain.Walletlog;
import com.woniu.car.user.web.service.WalletService;
import com.woniu.car.user.web.service.WalletlogService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 钱包日志 前端控制器
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/walletlog")
@Api(tags = "用户钱包日志服务接口")
public class WalletlogController {
    @Resource
    private WalletlogService walletlogService;
    @Resource
    private WalletService walletService;
    //增加日志
@PostMapping("/addWalletLog")
@ApiOperation(value = "添加钱包日志接口", notes = "<span style='color:red;'>用来添加钱包日志接口</span>")
@ApiResponses({
        @ApiResponse(code = 1370, message = "添加钱包日志成功"),
        @ApiResponse(code = 1371, message = "添加钱包日志失败"),
        @ApiResponse(code = 1400, message = "输入参数错误")

})

@ApiImplicitParams({
        //dataType:参数类型
        //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
        @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "integer", paramType = "path", example = "110"),
        @ApiImplicitParam(name = "walletId", value = "钱包ID", dataType = "integer", paramType = "path", example = "110"),
        @ApiImplicitParam(name = "walletEvent", value = "变化事件", dataType = "String", paramType = "path", example = "3月1日14点充值"),
        @ApiImplicitParam(name = "walletChange", value = "变化的数量", dataType = "BigDecimal", paramType = "path", example = "10.00"),
        @ApiImplicitParam(name = "walletType", value = "日志的类型（1充值2消费3退款4提现）", dataType = "integer", paramType = "path", example = "1"),

})

    public ResultEntity addWalletLog(AddWalletLogParam addWalletLogParam){
    //校验
    Integer userId = addWalletLogParam.getUserId();
    Integer walletId = addWalletLogParam.getWalletId();
    Wallet walletDb = walletService.getById(walletId);

    if (walletDb.getUserId()==userId){
         //校验完成
        //判断执行更改
        Walletlog walletlog = BeanCopyUtil.copyOne(addWalletLogParam, Walletlog::new);
        Integer walletlogType = addWalletLogParam.getWalletlogType();
        walletlog.setWalletOld(walletDb.getWalletMoney());
        BigDecimal walletChange = addWalletLogParam.getWalletChange();

        //如果walletlogtype是1充值3退款
        if (walletlogType==1||walletlogType==3){

            BigDecimal add = walletDb.getWalletMoney().add(walletChange);
            walletlog.setWalletMoney(add);
            //修改钱包余额
            walletDb.setWalletMoney(add);

        }if (walletlogType==2||walletlogType==4){
            //如果walletlogtype是2消费3体现
            BigDecimal subtract = walletDb.getWalletMoney().subtract(walletChange);
            walletlog.setWalletMoney(subtract);
            //修改钱包余额
            walletDb.setWalletMoney(subtract);
        }
        //执行钱包日志的添加和钱包余额的修改
        boolean save = walletlogService.save(walletlog);
        if (save){
            boolean b = walletService.updateById(walletDb);
            if (b) return ResultEntity.buildEntity().setCode(ConstCode.ADDWALLETLOG_SUCCESE).setFlag(true)
            .setMessage("添加日志成功");
            return ResultEntity.buildEntity().setCode(ConstCode.ADDWALLETLOG_FAIL).setFlag(false)
                    .setMessage("添加日志失败");
        }
    }
    return ResultEntity.buildEntity().setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }

    //查询钱包日志
    @GetMapping("/selectWalletLog")
    @ApiOperation(value = "添加钱包日志接口", notes = "<span style='color:red;'>用来添加钱包日志接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1372, message = "查询钱包日志成功"),
            @ApiResponse(code = 1373, message = "查询钱包日志失败"),
            @ApiResponse(code = 1400, message = "输入参数错误")

    })

    @ApiImplicitParams({
            //dataType:参数类型
            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "integer", paramType = "path", example = "110"),
            @ApiImplicitParam(name = "walletId", value = "钱包ID", dataType = "integer", paramType = "path", example = "110"),


    })

    public ResultEntity selectWalletLog(SelectWalletLogParam selectWalletLogParam){
    //校验参数
        Integer walletId = selectWalletLogParam.getWalletId();
        Wallet walletDb = walletService.getById(walletId);
        if (walletDb.getUserId()==selectWalletLogParam.getUserId()){
            //校验成功 执行查询
            List<Walletlog> walletlogList = walletlogService.list(new QueryWrapper<Walletlog>().eq("wallet_id", walletId));
            //返回
            if (walletlogList!=null) return ResultEntity.buildEntity(List.class).setCode(ConstCode.SELECTWALLETLOG_SUCCESS).setFlag(true)
            .setMessage("查询钱包日志成功").setData(walletlogList);
            return ResultEntity.buildEntity().setCode(ConstCode.SELECTWALLETLOG_FAIL).setFlag(false).setMessage("查询钱包日志失败");

        }
        return ResultEntity.buildEntity().setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }


}

