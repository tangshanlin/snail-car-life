package com.woniu.car.auth.web.service.impl;

import com.woniu.car.auth.web.entity.Permission;
import com.woniu.car.auth.web.service.PermissionService;
import com.woniu.car.auth.web.mapper.PermissionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author WTY
 * @since 2021-04-05
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissionNameByBackUserId(Integer backUserId) {
        //查询得到的所有菜单
        List<Permission> permissionNameByRoleId = permissionMapper.getPermissionNameByBackUserId(backUserId);
        //将所有菜单进行组合 每个二级菜单对应于他自己的一级菜单
        //这是所有的一级菜单的集合
        List<Permission> rootMenus = new ArrayList<>();
        permissionNameByRoleId.forEach(permission -> {
            //如果Level==1 为一级菜单
            if (permission.getLevel().equals("1")) {
                //给一级菜单设置一个二级菜单集合
                permission.setChildren(new ArrayList<Permission>());
                rootMenus.add(permission);
            }
        });
        int a = 1;
        //找出所有的二级菜单，并将所有的二级菜单放到一级菜单下
        permissionNameByRoleId.forEach(permission -> {
            rootMenus.forEach(root -> {
                //如果某个菜单的pid跟一级菜单的id相同，则其一定是二级菜单，此时将该菜单放到一级菜单的子菜单内
                if (permission.getParentId().equals(root.getPermissionId())) {
                    root.getChildren().add(permission);
                    return;
                }
            });
        });
        return rootMenus;
    }
}
