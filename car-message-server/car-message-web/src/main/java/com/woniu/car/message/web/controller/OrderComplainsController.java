package com.woniu.car.message.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.commons.web.util.BeanCopyUtil;
import com.woniu.car.message.client.OrderClient;
import com.woniu.car.message.client.UserClient;
import com.woniu.car.message.model.dto.OrderComplainsDto;
import com.woniu.car.message.model.dto.UserInformation;
import com.woniu.car.message.model.param.OrderComplainsParam;
import com.woniu.car.message.model.param.OrderVoP;
import com.woniu.car.message.model.param.UserIdParam;
import com.woniu.car.message.web.domain.OrderComplains;
import com.woniu.car.message.web.service.OrderComplainsService;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.*;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
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
    @Resource
    private UserClient userClient;

    /**
     * @Author Lints
     * @Date 2021/4/9/009 11:06
     * @Description This is description of method
     * @Param
     * @Return
     * @Since version-1.0
     */

    @PostMapping("add_order_complain")
    @ApiOperation(value = "添加订单投诉",notes = "<span style='color:red;'>用来添加订单投诉的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "添加订单投诉成功"),
            @ApiResponse(code=500,message = "添加订单投诉失败")
    })
    public ResultEntity<?> addOrderComplain(@RequestBody  @Validated OrderComplainsParam orderComplainsParam){
        if(!ObjectUtils.isEmpty(orderComplainsParam)){
            Integer isadd=orderComplainsService.addOrderComplains(orderComplainsParam);
            if(isadd==1){
                return ResultEntity.buildSuccessEntity().setMessage("添加订单投诉成功");
            }
            if (isadd==3){
                return ResultEntity.buildFailEntity().setMessage("没有这种类型的订单");
            }
            return ResultEntity.buildFailEntity().setMessage("添加订单投诉失败");
        }
        return ResultEntity.buildFailEntity().setMessage("订单投诉参数不能为空");
    }

    @GetMapping("list_yourself_order_complain")
    @ApiOperation(value = "查看订单投诉详情",notes = "<span style='color:red;'>用来查看订单投诉详情的接口</span>")
    @ApiResponses({
            @ApiResponse(code = 200,message = "根据用户编号查看订单投诉详情成功"),
            @ApiResponse(code=500,message = "根据用户编号查看订单投诉详情失败")
    })
    public ResultEntity<List<OrderComplainsDto>> getYourSelfOrderComplains(){
        UserInformation userInformation = userClient.selectUerInformation().getData();
        if(ObjectUtils.isEmpty(userInformation)){
            return ResultEntity.buildListSuccessEntity(OrderComplainsDto.class).setMessage("用户未登录");
        }
        QueryWrapper<OrderComplains> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("complain_user_id",userInformation.getUserId());
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

