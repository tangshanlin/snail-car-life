package com.woniu.car.user.web.service;

import com.woniu.car.user.param.LoginPasswordParam;
import com.woniu.car.user.param.LoginTelParam;
import com.woniu.car.user.param.RegisterParam;
import com.woniu.car.user.param.TelParam;
import com.woniu.car.user.web.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-07
 */
public interface UserService extends IService<User> {
    //注册方法
    int register(RegisterParam registerParam);


    //根据手机号查询用户
    User selectByTel(TelParam telParam);


}
