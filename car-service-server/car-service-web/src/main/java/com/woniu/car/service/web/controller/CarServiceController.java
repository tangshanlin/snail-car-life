package com.woniu.car.service.web.controller;


import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.items.model.dto.CarServiceDto;
import com.woniu.car.items.model.entity.CarService;
import com.woniu.car.items.model.param.carservice.*;
import com.woniu.car.service.web.service.CarServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sokyo
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/car_service")
@Api(tags = "汽车具体服务相关接口")
public class CarServiceController {
    @Resource
    private CarServiceService carServiceImpl;

    /**
     * @Author HuangZhengXing
     * @Description TODO 新增具体服务信息
     * @Date  2021/4/12
     * @Param [addCarServiceParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PostMapping("/api/add_car_service")
    @ApiOperation(value = "新增汽车具体服务",notes = "flag为true时新增成功,false时生成失败")
    @ApiImplicitParam(name = "addCarServiceParam",value = "接收新增具体服务参数",required = true,dataType = "AddCarServiceParam")
    public ResultEntity addCarService(AddCarServiceParam addCarServiceParam){
        CarServiceDto carServiceDto = new CarServiceDto();
        BeanUtils.copyProperties(addCarServiceParam,carServiceDto);
        System.out.println("CarServiceDto"+":"+carServiceDto);
        int i = carServiceImpl.addCarService(carServiceDto);
        if (i>0){
            return ResultEntity.buildSuccessEntity().setMessage("新增具体服务成功").setCode(ConstCode.ACCESS_SUCCESS);
        }else if (i == -10){
            return ResultEntity.buildFailEntity().setMessage("没有上传服务图片").setCode(ConstCode.LAST_STAGE).setFlag(false);
        }else if (i == -20){
            return ResultEntity.buildFailEntity().setMessage("没有上传服务详情介绍图片").setCode(ConstCode.LAST_STAGE).setFlag(false);
        }else if (i == -30){
            return ResultEntity.buildFailEntity().setMessage("服务名称已存在").setCode(ConstCode.LAST_STAGE).setFlag(false);
        }else {
            return ResultEntity.buildFailEntity().setMessage("新增具体服务失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
        }
    }
    /**
     * @Author HuangZhengXing
     * @Description TODO 根据服务id查询具体服务信息
     * @Date  2021/4/12
     * @Param [getOneCarServiceParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity<com.woniu.car.items.model.entity.CarService>
     **/
    @PostMapping("/get_one_car_service")
    @ApiOperation(value = "根据id查询汽车具体服务信息",notes = "flag为true时为成功,false时为失败")
    @ApiImplicitParam(name = "getOneCarServiceParam",value = "接收要查询的具体服务参数",required = true,dataType = "GetOneCarServiceParam")
    public ResultEntity<CarService> getOneCarService(@RequestBody GetOneCarServiceParam getOneCarServiceParam){
        CarService carService = new CarService();
        BeanUtils.copyProperties(getOneCarServiceParam,carService);
        System.out.println("CarService"+":"+carService);
        CarService carServiceById = carServiceImpl.getCarServiceById(carService);
      return ResultEntity.buildSuccessEntity(CarService.class).setMessage("查询成功").setCode(ConstCode.ACCESS_SUCCESS).setData(carServiceById);
    }
    /**
     * @Author HuangZhengXing
     * @Description TODO 根据门店id查询所有服务信息
     * @Date  2021/4/12
     * @Param [listCarServiceByShopPararm]
     * @return com.woniu.car.commons.core.dto.ResultEntity<java.util.List<com.woniu.car.items.model.entity.CarService>>
     **/
    @PostMapping("/list_car_service_by_shop")
    @ApiOperation(value = "根据门店id查询门店下服务信息",notes = "flag为true时为成功,false时为失败")
    @ApiImplicitParam(name = "listCarServiceByShopPararm",value = "接收要查询的门店服务的门店id",required = true,dataType = "ListCarServiceByShopPararm")
    public ResultEntity<List<CarService>> listCarServiceByShopId(@RequestBody ListCarServiceByShopPararm listCarServiceByShopPararm){
        CarService carService = new CarService();
        BeanUtils.copyProperties(listCarServiceByShopPararm,carService);
        System.out.println("CarService"+":"+carService);
        List<CarService> carServices = carServiceImpl.listCarServiceByShopId(carService);
        return ResultEntity.buildListSuccessEntity(CarService.class).setMessage("根据门店查询门店下的服务成功").setCode(ConstCode.ACCESS_SUCCESS).setData(carServices);
    }

    /**
     * @Author HuangZhengXing
     * @Description TODO 根据二级分类id查询二级分类下的具体服务信息
     * @Date  2021/4/12
     * @Param [listCarServiceByTwoClassifyParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity<java.util.List<com.woniu.car.items.model.entity.CarService>>
     **/
    @PostMapping("/list_car_service_by_two_classify")
    @ApiOperation(value = "根据二级分类id查询二级分类下服务信息",notes = "flag为true时为成功,false时为失败")
    @ApiImplicitParam(name = "listCarServiceByTwoClassifyParam",value = "接收要查询的二级分类id",required = true,dataType = "ListCarServiceByTwoClassifyParam")
    public ResultEntity<List<CarService>> listCarServiceByTwoClassify(@RequestBody ListCarServiceByTwoClassifyParam listCarServiceByTwoClassifyParam){
        CarService carService = new CarService();
        BeanUtils.copyProperties(listCarServiceByTwoClassifyParam,carService);
        System.out.println("CarService"+":"+carService);
        List<CarService> listCarServiceByTwoClassify = carServiceImpl.listCarServiceByTwoClassify(carService);
        return ResultEntity.buildListSuccessEntity(CarService.class).setMessage("查询成功").setCode(ConstCode.ACCESS_SUCCESS).setData(listCarServiceByTwoClassify);
    }

    /**
     * @Author HuangZhengXing
     * @Description TODO 查询所有服务信息
     * @Date  2021/4/12
     * @Param []
     * @return com.woniu.car.commons.core.dto.ResultEntity<java.util.List<com.woniu.car.items.model.entity.CarService>>
     **/
    @GetMapping("/api/list_car_service_all")
    @ApiOperation(value = "根据二级分类id查询二级分类下服务信息",notes = "不需要携带参数")
    public ResultEntity<List<CarService>> listCarServiceAll(){
        List<CarService> listCarServiceAll = carServiceImpl.listCarServiceAll();
        return ResultEntity.buildListSuccessEntity(CarService.class).setMessage("查询成功").setCode(ConstCode.ACCESS_SUCCESS).setData(listCarServiceAll);
    }

    /**
     * @Author HuangZhengXing
     * @Description TODO 根据服务id修改服务基本信息
     * @Date  2021/4/12
     * @Param [updateCarServiceInfoParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PostMapping("/api/update_car_service")
    @ApiOperation(value = "根据具体服务id修改基本服务信息",notes = "flag为true时为成功,false时为失败")
    @ApiImplicitParam(name = "updateCarServiceInfoParam",value = "接收要修改的基本信息参数",required = true,dataType = "UpdateCarServiceInfoParam")
    public ResultEntity updateCarServiceInfo(UpdateCarServiceInfoParam updateCarServiceInfoParam){
        System.out.println(updateCarServiceInfoParam);
        CarServiceDto carServiceDto = new CarServiceDto();
        BeanUtils.copyProperties(updateCarServiceInfoParam,carServiceDto);
        System.out.println("CarServiceDto"+":"+carServiceDto);
        boolean b = carServiceImpl.updateCarService(carServiceDto);
        if (b) return ResultEntity.buildSuccessEntity().setMessage("修改成功").setCode(ConstCode.ACCESS_SUCCESS);
        return ResultEntity.buildFailEntity().setMessage("修改失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
    }

    /*
     * @Author HuangZhengXing
     * @Description TODO 根据具体服务id修改服务状态
     * @Date  2021/4/12
     * @Param [updateCarServiceStatusParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PutMapping("/api/update_car_service_status")
    @ApiOperation(value = "根据具体服务id修改服务状态",notes = "flag为true时为成功,false时为失败 服务状态 0待上架 1已上架 2已下架")
    @ApiImplicitParam(name = "updateCarServiceStatusParam",value = "接收要修改的服务状态参数",required = true,dataType = "UpdateCarServiceStatusParam")
    public ResultEntity updateCarServiceStatus(@RequestBody UpdateCarServiceStatusParam updateCarServiceStatusParam){
        CarService carService = new CarService();
        BeanUtils.copyProperties(updateCarServiceStatusParam,carService);
        System.out.println("CarService"+":"+carService);
        boolean b = carServiceImpl.updateCarServiceStatus(carService);
        if (b) return ResultEntity.buildSuccessEntity().setMessage("修改成功").setCode(ConstCode.ACCESS_SUCCESS);
        return ResultEntity.buildFailEntity().setMessage("修改失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
    }

    /**
     * @Author HuangZhengXing
     * @Description TODO 根据具体服务id修改已售数量
     * @Date  2021/4/12
     * @Param [updateCarServiceSoldParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PutMapping("/api/update_car_service_sold")
    @ApiOperation(value = "根据具体服务id修改已售数量",notes = "flag为true时为成功,false时为失败")
    @ApiImplicitParam(name = "updateCarServiceSoldParam",value = "接收要修改的服务id",required = true,dataType = "UpdateCarServiceSoldParam")
    public ResultEntity updateCarServiceSold(@RequestBody UpdateCarServiceSoldParam updateCarServiceSoldParam){
        CarService carService = new CarService();
        BeanUtils.copyProperties(updateCarServiceSoldParam,carService);
        System.out.println("CarService"+":"+carService);
        boolean b = carServiceImpl.updateCarServiceSold(carService);
        if (b) return ResultEntity.buildSuccessEntity().setMessage("修改已售数量成功").setCode(ConstCode.ACCESS_SUCCESS);
        return ResultEntity.buildFailEntity().setMessage("修改已售数量失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
    }
    /**
     * @Author HuangZhengXing
     * @Description TODO 根据具体服务id删除服务
     * @Date  2021/4/12
     * @Param [deleteCarServiceParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @DeleteMapping("/api/delete_car_service")
    @ApiOperation(value = "根据具体服务id删除服务",notes = "flag为true时为成功,false时为失败")
    @ApiImplicitParam(name = "deleteCarServiceParam",value = "接收要删除的服务id",required = true,dataType = "DeleteCarServiceParam")
    public ResultEntity deleteCarService(@RequestBody DeleteCarServiceParam deleteCarServiceParam){
        CarService carService = new CarService();
        BeanUtils.copyProperties(deleteCarServiceParam,carService);
        System.out.println("CarService"+":"+carService);
        boolean b = carServiceImpl.deleteCarServiceById(carService);
        if (b) return ResultEntity.buildSuccessEntity().setMessage("删除成功").setCode(ConstCode.ACCESS_SUCCESS);
        return ResultEntity.buildFailEntity().setMessage("删除失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
    }


}

