package com.woniu.car.service.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.car.items.model.entity.CarServiceFlowpath;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sokyo
 * @since 2021-04-05
 */
public interface CarServiceFlowpathService extends IService<CarServiceFlowpath> {
    //新增具体服务流程
    public boolean insertCarServiceFlowpath(CarServiceFlowpath carServiceFlowpath);
    //根据具体服务id修改信息
    public boolean updateCarServiceFlowpath(Integer carServiceFlowpath_id);
    //根据具体服务id删除信息
    public boolean deleteCarServiceFlowpath(Integer carServiceFlowpath_id);
    //根据具体服务id查询具体服务流程
    public CarServiceFlowpath selectCarServiceFlowpathById(Integer carService_id);
}
