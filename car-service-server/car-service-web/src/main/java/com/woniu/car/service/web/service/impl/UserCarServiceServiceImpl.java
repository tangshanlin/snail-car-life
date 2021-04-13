package com.woniu.car.service.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniu.car.items.model.entity.CarService;
import com.woniu.car.items.model.entity.UserCarService;
import com.woniu.car.items.model.finalcode.UserCarServiceStatusCode;
import com.woniu.car.service.web.mapper.CarServiceMapper;
import com.woniu.car.service.web.mapper.UserCarServiceMapper;
import com.woniu.car.service.web.service.UserCarServiceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sokyo
 * @since 2021-04-05
 */
@Service
@Slf4j
public class UserCarServiceServiceImpl extends ServiceImpl<UserCarServiceMapper, UserCarService> implements UserCarServiceService {
    
    @Resource
    private UserCarServiceMapper userCarServiceMapper;
    @Resource
    private CarServiceMapper carServiceMapper;
    /**
     * @Author HuangZhengXing
     * @Description TODO 新增用户服务关联信息
     * @Date  2021/4/12
     * @Param [userCarService]
     * @return boolean
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUserCarService(UserCarService userCarService) {
        log.info("开始新增用户服务关联信息:{}",userCarService);
        QueryWrapper<CarService> wrapper = new QueryWrapper<>();
        wrapper.eq("car_service_id",userCarService.getCarServiceId());
        CarService carService = carServiceMapper.selectOne(wrapper);
        userCarService.setCarServiceName(carService.getCarServiceName());
        userCarService.setUserServiceStatus(UserCarServiceStatusCode.SERVICE_NOT_STARTED);
        int insert = userCarServiceMapper.insert(userCarService);
        boolean b = false;
        if (insert>0) b=true;
        return b;
    }
    /**
     * @Author HuangZhengXing
     * @Description TODO 根据用户id查询信息
     * @Date  2021/4/12
     * @Param [userCarService]
     * @return java.util.List<com.woniu.car.items.model.entity.UserCarService>
     **/
    @Override
    public List<UserCarService> listUserCarServiceByUser(UserCarService userCarService) {
        log.info("开始根据用户id查询用户服务关联表信息");
        QueryWrapper<UserCarService> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userCarService.getUserId());
        List<UserCarService> userCarServiceList = userCarServiceMapper.selectList(wrapper);
        return userCarServiceList;
    }
    /**
     * @Author HuangZhengXing
     * @Description TODO 查询所有信息
     * @Date  2021/4/12
     * @Param [userCarService]
     * @return java.util.List<com.woniu.car.items.model.entity.UserCarService>
     **/
    @Override
    public List<UserCarService> listUserCarServiceAll() {
        log.info("开始查询所有用户服务关联信息");
        List<UserCarService> userCarServiceList = userCarServiceMapper.selectList(null);
        return userCarServiceList;
    }
    /**
     * @Author HuangZhengXing
     * @Description TODO 根据用户服务关联表id修改状态
     * @Date  2021/4/12
     * @Param [userCarService]
     * @return boolean
     **/
    @Override
    public boolean updateCarServiceStatus(UserCarService userCarService) {
        log.info("开始根据用户服务关联表id修改状态:{}",userCarService);
        UpdateWrapper<UserCarService> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_service_id",userCarService.getUserServiceId());
        int update = userCarServiceMapper.update(userCarService, wrapper);
        boolean b = false;
        if(update>0) b=true;
        return b;
    }
    
    /**
     * @Author HuangZhengXing
     * @Description TODO 根据用户服务关联表id删除
     * @Date  2021/4/12
     * @Param [userCarService]
     * @return boolean
     **/
    @Override
    public boolean deleteCarService(UserCarService userCarService) {
        log.info("开始删除id为:{}",userCarService.getCarServiceId());
        int i = userCarServiceMapper.deleteById(userCarService.getCarServiceId());
        boolean b = false;
        if (i>0) b=true;
        return b;
    }
}
