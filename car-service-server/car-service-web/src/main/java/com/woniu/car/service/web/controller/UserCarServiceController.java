package com.woniu.car.service.web.controller;


import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.items.model.entity.UserCarService;
import com.woniu.car.items.model.param.carservice.DeleteCarServiceParam;
import com.woniu.car.items.model.param.userservice.AddUserServiceParam;
import com.woniu.car.items.model.param.userservice.ListUserServiceByUserParam;
import com.woniu.car.items.model.param.userservice.UpdateUserCarServiceStatusParam;
import com.woniu.car.service.web.service.UserCarServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sokyo
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/user_car_service")
@Api(tags = "用户服务关联相关接口")
public class UserCarServiceController {
    @Resource
    private UserCarServiceService userCarServiceService;

    /**
     * @Author HuangZhengXing
     * @Description TODO 新增用户服务关联信息
     * @Date  2021/4/12
     * @Param [addUserServiceParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PostMapping("/api/add_user_car_service")
    @ApiOperation(value = "新增用户服务关联信息",notes = "flag为true时新增成功,false时生成失败")
    @ApiImplicitParam(name = "addUserServiceParam",value = "接收新增用户服务关联信息参数",required = true,dataType = "AddUserServiceParam")
    public ResultEntity addUserCarService(@RequestBody AddUserServiceParam addUserServiceParam){
        UserCarService userCarService = new UserCarService();
        BeanUtils.copyProperties(addUserServiceParam,userCarService);
        System.out.println("UserCarService"+":"+userCarService);
        boolean b = userCarServiceService.addUserCarService(userCarService);
        if (b) return ResultEntity.buildSuccessEntity().setMessage("新增成功").setCode(ConstCode.ACCESS_SUCCESS);
        return ResultEntity.buildFailEntity().setMessage("新增失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
    }
    /**
     * @Author HuangZhengXing
     * @Description TODO 根据用户id查询关联表信息
     * @Date  2021/4/12
     * @Param [listUserServiceByUserParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity<java.util.List<com.woniu.car.items.model.entity.UserCarService>>
     **/
    @PostMapping("/list_car_service_user")
    @ApiOperation(value = "根据用户id查询关联表信息",notes = "flag为true时新增成功,false时生成失败")
    @ApiImplicitParam(name = "listUserServiceByUserParam",value = "接收要查询关联表信息的用户id",required = true,dataType = "ListUserServiceByUserParam")
    public ResultEntity<List<UserCarService>> listUserServiceByUser(@RequestBody ListUserServiceByUserParam listUserServiceByUserParam){
        UserCarService userCarService = new UserCarService();
        BeanUtils.copyProperties(listUserServiceByUserParam,userCarService);
        System.out.println("UserCarService"+":"+userCarService);
        List<UserCarService> userCarServices = userCarServiceService.listUserCarServiceByUser(userCarService);
        return ResultEntity.buildListSuccessEntity(UserCarService.class).setMessage("查询成功").setCode(ConstCode.ACCESS_SUCCESS).setData(userCarServices);
    }
    /**
     * @Author HuangZhengXing
     * @Description TODO 查询所有用户服务关联信息
     * @Date  2021/4/12
     * @Param []
     * @return com.woniu.car.commons.core.dto.ResultEntity<java.util.List<com.woniu.car.items.model.entity.UserCarService>>
     **/
    @GetMapping("/api/list_user_service_all")
    @ApiOperation(value = "查询所有用户服务关联信息",notes = "不需要携带参数")
    public ResultEntity<List<UserCarService>> listUserCarServiceAll(){
        List<UserCarService> userCarServiceList = userCarServiceService.listUserCarServiceAll();
        return ResultEntity.buildListSuccessEntity(UserCarService.class).setMessage("查询成功").setCode(ConstCode.ACCESS_SUCCESS).setData(userCarServiceList);
    }

    /**
     * @Author HuangZhengXing
     * @Description TODO 根据用户服务关联id修改服务状态
     * @Date  2021/4/12
     * @Param [updateUserCarServiceStatusParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PutMapping("/api/update_user_service")
    @ApiOperation(value = "根据用户服务关联id修改服务状态",notes = "flag为true时新增成功,false时生成失败")
    @ApiImplicitParam(name = "updateUserCarServiceStatusParam",value = "接收要修改关联表信息id和要修改的状态",required = true,dataType = "UpdateUserCarServiceStatusParam")
    public ResultEntity updateUserServiceStatus(@RequestBody UpdateUserCarServiceStatusParam updateUserCarServiceStatusParam){
        UserCarService userCarService = new UserCarService();
        BeanUtils.copyProperties(updateUserCarServiceStatusParam,userCarService);
        System.out.println("UserCarService"+":"+userCarService);
        boolean b = userCarServiceService.updateCarServiceStatus(userCarService);
        if (b) return ResultEntity.buildSuccessEntity().setMessage("修改成功").setCode(ConstCode.ACCESS_SUCCESS);
        return ResultEntity.buildFailEntity().setMessage("修改失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
    }

    /**
     * @Author HuangZhengXing
     * @Description TODO 根据用户服务关联id删除信息
     * @Date  2021/4/12
     * @Param [deleteCarServiceParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @DeleteMapping("/api/delete_user_service")
    @ApiOperation(value = "根据用户服务关联id删除信息",notes = "flag为true时新增成功,false时生成失败")
    @ApiImplicitParam(name = "deleteCarServiceParam",value = "接收要删除的关联表id",required = true,dataType = "DeleteCarServiceParam")
    public ResultEntity deleteUserService(@RequestBody DeleteCarServiceParam deleteCarServiceParam){
        UserCarService userCarService = new UserCarService();
        BeanUtils.copyProperties(deleteCarServiceParam,userCarService);
        System.out.println("UserCarService"+":"+userCarService);
        boolean b = userCarServiceService.deleteCarService(userCarService);
        if (b) return ResultEntity.buildSuccessEntity().setMessage("删除成功").setCode(ConstCode.ACCESS_SUCCESS);
        return ResultEntity.buildFailEntity().setMessage("删除失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
    }
}

