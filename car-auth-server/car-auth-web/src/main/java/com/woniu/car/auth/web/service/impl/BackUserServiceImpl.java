package com.woniu.car.auth.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.auth.web.entity.BackUser;
import com.woniu.car.auth.web.mapper.BackUserMapper;
import com.woniu.car.auth.web.service.BackUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WTY
 * @since 2021-04-05
 */
@Service
public class BackUserServiceImpl extends ServiceImpl<BackUserMapper, BackUser> implements BackUserService {
    @Resource
    private BackUserMapper backUserMapper;
    @Override
    public BackUser selectByBackUsername(QueryWrapper<BackUser> queryWrapper) {
        return backUserMapper.selectOne(queryWrapper);
    }
}
