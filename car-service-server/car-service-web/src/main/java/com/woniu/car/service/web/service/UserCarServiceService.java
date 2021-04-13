package com.woniu.car.service.web.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.car.items.model.entity.UserCarService;
import org.apache.catalina.User;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sokyo
 * @since 2021-04-05
 */
public interface UserCarServiceService extends IService<UserCarService> {
    //新增用户服务关联信息
    public boolean addUserCarService(UserCarService userCarService);
    //根据用户id查询信息
    public List<UserCarService> listUserCarServiceByUser(UserCarService userCarService);
    //查询所有信息
    public List<UserCarService> listUserCarServiceAll();
    //根据用户服务关联表id修改状态
    public boolean updateCarServiceStatus(UserCarService userCarService);
    //根据用户服务关联表id删除
    public boolean deleteCarService(UserCarService userCarService);

}
