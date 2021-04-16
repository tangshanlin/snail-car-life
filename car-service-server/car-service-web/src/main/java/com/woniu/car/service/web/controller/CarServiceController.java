package com.woniu.car.service.web.controller;


import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.items.model.dto.CarServiceDto;
import com.woniu.car.items.model.dto.CarServiceImagsDto;
import com.woniu.car.items.model.entity.CarService;
import com.woniu.car.items.model.param.carservice.*;
import com.woniu.car.items.model.param.userservice.UploadCarServiceImageParam;
import com.woniu.car.items.model.param.userservice.UploadCarServiceInfoImagesParam;
import com.woniu.car.service.web.service.CarServiceService;
import com.woniu.car.service.web.util.ServiceFileUpload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private ServiceFileUpload serviceFileUpload;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

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
    public ResultEntity addCarService(@RequestBody AddCarServiceParam addCarServiceParam){

        CarService carService = new CarService();
        BeanUtils.copyProperties(addCarServiceParam,carService);
        System.out.println("CarServiceDto"+":"+carService);
        int i = carServiceImpl.addCarService(carService);
        if (i>0){
            return ResultEntity.buildSuccessEntity().setMessage("新增具体服务成功").setCode(ConstCode.ACCESS_SUCCESS);
        }else if (i == -10){
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
        if (ObjectUtils.isEmpty(getOneCarServiceParam.getCarServiceId())){
            return ResultEntity.buildSuccessEntity(CarService.class).setMessage("查询失败，未获取到要查询的参数").setCode(ConstCode.LAST_STAGE).setFlag(false);
        }else {
            CarService carService = new CarService();
            BeanUtils.copyProperties(getOneCarServiceParam,carService);
            System.out.println("CarService"+":"+carService);
            CarService carServiceById = carServiceImpl.getCarServiceById(carService);
            return ResultEntity.buildSuccessEntity(CarService.class).setMessage("查询成功").setCode(ConstCode.ACCESS_SUCCESS).setData(carServiceById);
        }

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
        System.out.println(listCarServiceByShopPararm.getShopId());
        if(!ObjectUtils.isEmpty(listCarServiceByShopPararm.getShopId())){
                CarService carService = new CarService();
                BeanUtils.copyProperties(listCarServiceByShopPararm,carService);
                System.out.println("CarServiceSelect"+":"+carService);
            List<CarService> carServiceImagsDtoList = carServiceImpl.listCarServiceByShopId(carService);
            return ResultEntity.buildListSuccessEntity(CarService.class).setMessage("根据门店查询门店下的服务成功").setCode(ConstCode.ACCESS_SUCCESS).setData(carServiceImagsDtoList);

        }else {
            return ResultEntity.buildListFailEntity(CarService.class).setMessage("门店id为空或者不正确").setCode(ConstCode.LAST_STAGE).setFlag(false).setData(null);
        }


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
        if (ObjectUtils.isEmpty(listCarServiceByTwoClassifyParam.getTwoClassifyId())){
            return ResultEntity.buildListEntity(CarService.class).setMessage("id为空或者输入错误").setFlag(false).setCode(ConstCode.LAST_STAGE).setData(null);
        }
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
    @ApiOperation(value = "查询所有服务信息",notes = "不需要携带参数")
    public ResultEntity<List<CarService>> listCarServiceAll(){
        List<CarService> listCarServiceAll = carServiceImpl.listCarServiceAll();
        if (ObjectUtils.isEmpty(listCarServiceAll)) return ResultEntity.buildListEntity(CarService.class).setMessage("查询成功，结果为空").setCode(ConstCode.ACCESS_SUCCESS).setData(listCarServiceAll);
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
    public ResultEntity updateCarServiceInfo(@RequestBody UpdateCarServiceInfoParam updateCarServiceInfoParam){
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

    /**
     * @Author HuangZhengXing
     * @Description TODO 上传单张具体服务图片
     * @Date  2021/4/13
     * @Param [uploadCarServiceImageParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PostMapping("/uploading_car_service_image")
    @ApiOperation(value = "上传单张具体服务图片")
    public ResultEntity uploadingCarServiceImage(UploadCarServiceImageParam uploadCarServiceImageParam){
        if (ObjectUtils.isEmpty(uploadCarServiceImageParam.getCarServiceImage())){
            return ResultEntity.buildFailEntity().setMessage("图片为空").setCode(ConstCode.LAST_STAGE).setFlag(false);
        }else {
            if (uploadCarServiceImageParam.getCarServiceImage().length>0){
                MultipartFile[] files = uploadCarServiceImageParam.getCarServiceImage();
                //将文件上传到minio服务器上
                ArrayList<String> stationImageList = serviceFileUpload.upload(files);
                //返回图片地址
                String stationimg = stationImageList.get(0);
                System.out.println(stationimg);
                return ResultEntity.buildSuccessEntity(String.class).setCode(ConstCode.ACCESS_SUCCESS).setData(stationimg).setMessage("图片上传成功");
            }else {
                return ResultEntity.buildFailEntity().setMessage("图片为空").setCode(ConstCode.LAST_STAGE).setFlag(false);
            }
        }

    }

    /**
     * @Author HuangZhengXing
     * @Description TODO 上传服务详情多张图片
     * @Date  2021/4/13
     * @Param [uploadCarServiceInfoImagesParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PostMapping("/uploading_car_service_info_images")
    @ApiOperation(value = "上传服务详情多张图片")
    public ResultEntity uploadCarServiceInfoImages(UploadCarServiceInfoImagesParam uploadCarServiceInfoImagesParam){
        if (ObjectUtils.isEmpty(uploadCarServiceInfoImagesParam)){
            return ResultEntity.buildFailEntity().setMessage("图片为空").setCode(ConstCode.LAST_STAGE).setFlag(false);
        }else {
            if (uploadCarServiceInfoImagesParam.getCarServiceInfo().length>0){
                MultipartFile[] files = uploadCarServiceInfoImagesParam.getCarServiceInfo();
                //将文件上传到minio服务器上
                ArrayList<String> stationImage = serviceFileUpload.upload(files);
                JSONObject jsonObject = new JSONObject();
                for (int a = 0;a<stationImage.size();a++){
                    String time = String.valueOf(System.currentTimeMillis());
//                    jsonObject.put("service"+ UUID.randomUUID().toString()+time,stationImage.get(a));
                }
                System.out.println(jsonObject);
                //返回图片地址
                String stationimgs = JSONObject.toJSONString(jsonObject);
                System.out.println(stationimgs);
                return ResultEntity.buildSuccessEntity(String.class).setCode(ConstCode.ACCESS_SUCCESS).setData(stationimgs).setMessage("图片上传成功");
            }else {
                return ResultEntity.buildFailEntity().setMessage("图片为空").setCode(ConstCode.LAST_STAGE).setFlag(false);
            }
        }


    }
}

