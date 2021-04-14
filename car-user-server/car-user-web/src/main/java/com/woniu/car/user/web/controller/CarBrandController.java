package com.woniu.car.user.web.controller;


import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.user.web.domain.CarBrand;
import com.woniu.car.user.web.service.CarBrandService;
import io.minio.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 车品牌表 前端控制器
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/car-brand")
@Api(tags = "车品牌查询")
public class CarBrandController {
    @Resource
    private CarBrandService carBrandService;
    /*查询所有车品牌*/
    @GetMapping("/select_all_brand")
    @ApiOperation(value = "查询所有车品牌的接口",notes = "<span style='color:red;'>查询所有车品牌的接口</span>")
    @ApiResponse(code = 1344,message = "查询车品牌成功")
    public ResultEntity<List<CarBrand>> selectAllBrand(){
        List<CarBrand> list = carBrandService.list();
     return ResultEntity.buildListEntity(CarBrand.class).setCode(ConstCode.SELECTCARBRAND_SUCESS).setFlag(true)
             .setMessage("查询所有车品牌成功").setData(list);

    }

}

