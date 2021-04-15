package com.woniu.car.user.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.user.param.SelectCarseirsParam;
import com.woniu.car.user.web.domain.Carseries;
import com.woniu.car.user.web.service.CarseriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 车系表 前端控制器
 * </p>
 *
 * @author sushanhu
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/carseries")
@Api(tags = "车系信息的接口")
public class CarseriesController {
    @Resource
    private CarseriesService carseriesService;
    /*根据品牌id查询车系信息*/
    @GetMapping("/select_by_brand_id")
    @ApiOperation(value ="根据品牌ID查询车系信息的接口",notes = "<span style='color:red;'>根据品牌ID查询车系信息的接口</span>" )
    public ResultEntity<List<Carseries>> selectByCarBrandId(@RequestBody  SelectCarseirsParam selectCarseirsParam){
        //参数校验
        if (!ObjectUtils.isEmpty(selectCarseirsParam)){


        List<Carseries> carbrand_id = carseriesService.list(new QueryWrapper<Carseries>().eq("carbrand_id", selectCarseirsParam.getCarbrandId()));
    return ResultEntity.buildListEntity(Carseries.class).setCode(ConstCode.SELECTCARSERIES_SUCESS).setFlag(true)
            .setMessage("查询车系成功").setData(carbrand_id);
        }
        return  ResultEntity.buildListEntity(Carseries.class).setCode(ConstCode.PARAM_ERROR).setFlag(false).setMessage("输入参数错误");
    }


}

