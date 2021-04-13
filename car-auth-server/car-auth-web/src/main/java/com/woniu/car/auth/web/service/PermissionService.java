package com.woniu.car.auth.web.service;

import com.woniu.car.auth.web.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WTY
 * @since 2021-04-05
 */
public interface PermissionService extends IService<Permission> {
    /**
     * 通过用户id查询角色对应的所有权限菜单名称
     * @param roleId 角色id
     * @return 权限菜单名称集合
     */
    List<Permission> getPermissionNameByBackUserId(Integer roleId);
}
