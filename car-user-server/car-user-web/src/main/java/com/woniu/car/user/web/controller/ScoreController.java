package com.woniu.car.user.web.controller;


import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.user.param.AddScoreParam;
import com.woniu.car.user.web.domain.Score;
import com.woniu.car.user.web.domain.UserInformation;
import com.woniu.car.user.web.service.ScoreService;
import com.woniu.car.user.web.service.UserInformationService;
import com.woniu.car.user.web.util.GetTokenUtil;
import com.woniu.car.user.web.util.JwtUtils;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 积分表 前端控制器
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-09
 */
@RestController
@RequestMapping("/score")
@Api(tags = "积分服务接口")
public class ScoreController {
    @Resource
    private ScoreService scoreService;
    @Resource
    private UserInformationService userInformationService;


    //新增积分
    @PostMapping("/addScore")
    @ApiOperation(value = "新增积分日志接口", notes = "<span style='color:red;'>用来新增积分日志接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1380, message = "新增积分日志成功"),
            @ApiResponse(code = 1381, message = "新增积分日志失败"),
            @ApiResponse(code = 1400, message = "输入参数错误")

    })
//    @ApiImplicitParams({
//            //dataType:参数类型
//            //paramType:参数由哪里获取     path->从路径中获取，query->?传参，body->ajax请求
//            @ApiImplicitParam(name = "addressId", value = "地址ID", dataType = "integer", paramType = "path", example = "110"),
//
//            @ApiImplicitParam(name = "scoreNumber", value = "积分变化数", dataType = "Integer", example = "10"),
//            @ApiImplicitParam(name = "scoreChange", value = "积分变化事件", dataType = "String", example = "兑换五十的优惠券"),
//            @ApiImplicitParam(name = "scoreChangeType" , value = "积分变化类型（1消费获取积分2兑换消费券消耗积分）", dataType = "String",  example = "2"),
//
//
//
//    })
    public ResultEntity addScore(@RequestBody @Valid AddScoreParam addScoreParam){
        //校验参数
        //从jwt中获取userId；
        String token = GetTokenUtil.getToken();
        DecodedJWT decodeToken = JwtUtils.getDecodeToken(token);
        Claim userId = decodeToken.getClaims().get("userId");
        Integer userId2 = Integer.valueOf(userId.asString());
        Score score = BeanCopyUtil.copyOne(addScoreParam, Score::new);
        score.setUserId(userId2);


        //开始新增
        //判断socreType的类型
        Integer scoreNumber = addScoreParam.getScoreNumber();
        Integer scoreChangeType = addScoreParam.getScoreChangeType();
        UserInformation userInformation = userInformationService.getOne(new QueryWrapper<UserInformation>().eq("user_id", userId2));
        //将积分的原始值赋给score表
        Integer userScore = userInformation.getUserScore();
        score.setScoreOld(userScore);
        if (scoreChangeType==1){
           score.setScoreBalance(userScore+addScoreParam.getScoreNumber());


        }if (scoreChangeType==2){
            score.setScoreBalance(userScore-addScoreParam.getScoreNumber());
        }
        //执行新增
        boolean save = scoreService.save(score);
        if (save){
            //新增成功，修改用户详情表的积分
            userInformation.setUserScore(score.getScoreBalance());
            boolean b = userInformationService.updateById(userInformation);
            if (b) return ResultEntity.buildEntity().setCode(ConstCode.ADDSCORE_SUCCESS).setFlag(true)
            .setMessage("新增积分日志成功");
            return ResultEntity.buildEntity().setCode(ConstCode.ADDSCORE_FAIL).setFlag(false).setMessage("新增积分日志失败");
        }


        return ResultEntity.buildEntity().setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }

    //查询积分的方法
    @GetMapping("/selectScore")
    @ApiOperation(value = "查询积分日志接口", notes = "<span style='color:red;'>用来查询积分日志接口</span>")
    @ApiResponses({
            @ApiResponse(code = 1382, message = "查询积分日志成功"),
            @ApiResponse(code = 1383, message = "查询积分日志失败"),
            @ApiResponse(code = 1400, message = "输入参数错误")

    })

    public ResultEntity<List<Score>> selectScore(){
        //从jwt获取userId
        Integer userId = GetTokenUtil.getUserId();
        //执行查询
        List<Score> userId1 = scoreService.list(new QueryWrapper<Score>().eq("userId", userId));
        return ResultEntity.buildListEntity(Score.class).setCode(ConstCode.SELECTSCORE_SUCESS).setFlag(true)
                .setMessage("查询积分日志成功").setData(userId1);

    }
}

