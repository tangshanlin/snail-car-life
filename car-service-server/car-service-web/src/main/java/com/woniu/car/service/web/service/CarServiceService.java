package com.woniu.car.service.web.service;

import com.woniu.car.items.model.dto.CarServiceDto;
import com.woniu.car.items.model.entity.CarService;
import com.baomidou.mybatisplus.extension.service.IService;
import org.checkerframework.checker.units.qual.C;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sokyo
 * @since 2021-04-05
 */
public interface CarServiceService extends IService<CarService> {
    //新增具体服务信息
    public int addCarService(CarServiceDto carServiceDto);
    //根据服务id查询具体服务信息
    public CarService getCarServiceById(CarService carService);
    //根据门店id查询所有服务信息
    public List<CarService> listCarServiceByShopId(CarService carService);
    //根据二级分类id查询二级分类下的具体服务信息
    public List<CarService> listCarServiceByTwoClassify(CarService carService);
    //查询所有服务信息
    public List<CarService> listCarServiceAll();
    //根据服务id修改服务基本信息
    public boolean updateCarService(CarServiceDto carServiceDto);
    //根据服务id修改服务状态
    public boolean updateCarServiceStatus(CarService carService);
    //根据服务id修改已售数量
    public boolean updateCarServiceSold(CarService carService);
    //根据服务id删除服务
    public boolean deleteCarServiceById(CarService carService);

}
