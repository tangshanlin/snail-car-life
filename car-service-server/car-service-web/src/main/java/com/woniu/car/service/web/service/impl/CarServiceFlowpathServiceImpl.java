package com.woniu.car.service.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.items.model.entity.CarServiceFlowpath;
import com.woniu.car.service.web.mapper.CarServiceFlowpathMapper;
import com.woniu.car.service.web.service.CarServiceFlowpathService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sokyo
 * @since 2021-04-05
 */
@Service
public class CarServiceFlowpathServiceImpl extends ServiceImpl<CarServiceFlowpathMapper, CarServiceFlowpath> implements CarServiceFlowpathService {

    @Resource
    private CarServiceFlowpathMapper carServiceFlowpathMapper;


    //新增具体服务流程
    @Override
    public boolean insertCarServiceFlowpath(CarServiceFlowpath carServiceFlowpath) {
        int insert = carServiceFlowpathMapper.insert(carServiceFlowpath);
        Boolean b = false;
        if (insert>0){
            b=true;
        }
        return b;
    }
    //根据具体服务id修改信息
    @Override
    public boolean updateCarServiceFlowpath(Integer carServiceFlowpath_id) {
        return false;
    }
    //根据具体服务id删除信息
    @Override
    public boolean deleteCarServiceFlowpath(Integer carServiceFlowpath_id) {
        return false;
    }
    //根据具体服务id查询具体服务流程
    @Override
    public CarServiceFlowpath selectCarServiceFlowpathById(Integer carService_id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("car_service_id",carService_id);
        CarServiceFlowpath carServiceFlowpath = carServiceFlowpathMapper.selectOne(wrapper);
        return carServiceFlowpath;
    }
}
