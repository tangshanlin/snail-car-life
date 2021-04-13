package com.woniu.car.auth.web.controller;


import com.woniu.car.auth.web.entity.Permission;
import com.woniu.car.auth.web.service.PermissionService;
import com.woniu.car.commons.core.dto.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author WTY
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/permission")
@Api(tags = "权限菜单模块")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @GetMapping("/api/permission_id")
    @ApiOperation(value = "查询用户id对应权限菜单")
    @ApiImplicitParam(name = "backUserId",value = "用户",dataType = "Integer", required = true)
    public ResultEntity<List<Permission>> getPermissionNameByBackUserId(Integer backUserId){
        List<Permission> permissionNameList = permissionService.getPermissionNameByBackUserId(backUserId);
        return ResultEntity.buildListSuccessEntity(Permission.class).setData(permissionNameList);
    }


}

