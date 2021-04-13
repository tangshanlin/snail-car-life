package com.woniu.car.auth.web.mapper;

import com.woniu.car.auth.web.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WTY
 * @since 2021-04-05
 */
public interface PermissionMapper extends BaseMapper<Permission> {

@Select("SELECT p.* FROM t_back_user AS bu " +
        "JOIN t_user_to_role AS ur ON ur.back_user_id = bu.back_user_id " +
        "JOIN t_role AS r ON ur.role_id = r.role_id " +
        "JOIN t_role_to_permission AS rp ON rp.role_id = r.role_id " +
        "JOIN t_permission AS p ON p.permission_id = rp.permission_id " +
        "WHERE bu.back_user_id = #{backUserId}")
    List<Permission> getPermissionNameByBackUserId(Integer backUserId);
}
