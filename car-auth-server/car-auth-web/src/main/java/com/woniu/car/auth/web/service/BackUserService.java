package com.woniu.car.auth.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.auth.web.entity.BackUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WTY
 * @since 2021-04-05
 */
public interface BackUserService extends IService<BackUser> {

    BackUser selectByBackUsername(QueryWrapper<BackUser> queryWrapper);
}
