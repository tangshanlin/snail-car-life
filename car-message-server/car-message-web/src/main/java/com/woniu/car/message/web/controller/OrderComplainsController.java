package com.woniu.car.message.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.message.client.OrderClient;
import com.woniu.car.message.model.dto.OrderComplainsDto;
import com.woniu.car.message.model.param.OrderComplainsParam;
import com.woniu.car.message.model.param.OrderVoP;
import com.woniu.car.message.model.param.UserIdParam;
import com.woniu.car.message.web.domain.OrderComplains;
import com.woniu.car.message.web.service.OrderComplainsService;
import io.swagger.annotations.*;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyy
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/order-complains")
@Api(tags = "订单投诉服务接口信息")
public class OrderComplainsController {

    @Resource
    private OrderComplainsService orderComplainsService;

    /**
     * @Author Lints
     * @Date 2021/4/9/009 11:06
     * @Description This is description of method
     * @Param
     * @Return
     * @Since version-1.0
     */

    @GetMapping("add_order_complain")
    @ApiOperation(value = "添加订单投诉",notes = "<span style='color:red;'>用来添加订单投诉的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "添加订单投诉成功"),
            @ApiResponse(code=500,message = "添加订单投诉失败")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "complaintOrderCode",value = "投诉订单编号",dataType = "String",paramType = "query",example = "se123456789"),
            @ApiImplicitParam(name="complainUserId",value = "投诉人编号",dataType = "Integer",paramType = "query",example = "1"),
            @ApiImplicitParam(name="complainUsername",value = "投诉人姓名",dataType = "String",paramType = "query",example = "tom"),
            @ApiImplicitParam(name="complainType",value = "投诉类型（1.服务质量，2 服务人员，3自然灾害，4.服务设备(下拉框选择，传入固定值)",dataType = "String",paramType = "query",example = "自然灾害"),
            @ApiImplicitParam(name = " complainResult",value = "投诉具体内容",dataType = "String",paramType = "query",example = "服务人员态度恶劣"),
    })
    public ResultEntity<?> addOrderComplain(OrderComplainsParam orderComplainsParam){
        Boolean isadd=orderComplainsService.addOrderComplains(orderComplainsParam);
        if(isadd){
            return ResultEntity.buildSuccessEntity().setMessage("添加订单投诉成功");
        }
        return ResultEntity.buildFailEntity().setMessage("添加订单投诉失败");
    }

    @GetMapping("list_yourself_order_complain")
    @ApiOperation(value = "根据用户编号查看订单投诉详情",notes = "<span style='color:red;'>用来查看订单投诉详情的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "根据用户编号查看订单投诉详情成功"),
            @ApiResponse(code=500,message = "根据用户编号查看订单投诉详情失败")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户ID",dataType = "Integer",paramType = "query",example = "1"),
    })
    public ResultEntity<List<OrderComplainsDto>> getYourSelfOrderComplains(UserIdParam userIdParam){
        QueryWrapper<OrderComplains> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("complain_user_id",userIdParam);
        List<OrderComplains> orderComplains = orderComplainsService.list(queryWrapper);
        List<OrderComplainsDto> orderComplainsDtos = BeanCopyUtil.copyList(orderComplains, OrderComplainsDto::new);
        orderComplainsDtos.forEach(orderComplainsDto -> {
            orderComplainsDto.setComplainTimes(new Date(orderComplainsDto.getComplainTime()));
        });
        if(!ObjectUtils.isEmpty(orderComplainsDtos)){
            return ResultEntity.buildListSuccessEntity(OrderComplainsDto.class).setMessage("根据用户编号查看订单投诉详情成功")
                    .setData(orderComplainsDtos);
        }
       return ResultEntity.buildListSuccessEntity(OrderComplainsDto.class).setMessage("根据用户编号查看订单投诉详情失败");
    }
























}

