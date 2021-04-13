package com.woniu.car.station.web.controller;


import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.station.model.dto.StationDto;
import com.woniu.car.station.model.dto.UpdateStationDto;
import com.woniu.car.station.model.entity.Station;
import com.woniu.car.station.model.param.GetPowerplantParam;
import com.woniu.car.station.model.param.station.*;
import com.woniu.car.station.web.service.StationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/station")
@Api(tags = "充电桩相关接口")
public class StationController {
    @Resource
    private StationService stationService;


    /**
     * @Author HuangZhengXing
     * @Description TODO 新增充电桩信息
     * @Date  2021/4/12
     * @Param [addStationParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PostMapping("/api/add_station")
    @ApiOperation(value = "新增充电桩信息",notes = "传入要新增的电桩信息")
    @ApiImplicitParam(name = "addStationParam",value = "要新增的电桩信息",required = true,dataType = "AddStationParam")
    public ResultEntity addStation(AddStationParam addStationParam){
        StationDto stationDto = new StationDto();
        BeanUtils.copyProperties(addStationParam,stationDto);
        System.out.println("StationDto"+":"+stationDto);
        boolean b = stationService.addStation(stationDto);
        if (b) return ResultEntity.buildSuccessEntity().setMessage("新增电桩信息成功").setCode(ConstCode.ACCESS_SUCCESS);
        return ResultEntity.buildFailEntity().setMessage("新增电桩信息失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
    }

    /**
     * @Author HuangZhengXing
     * @Description TODO 根据充电桩id查询充电桩信息
     * @Date  2021/4/12
     * @Param [getOneStationParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PostMapping("/get_station")
    @ApiOperation(value = "根据充电桩id查询充电桩信息",notes = "传入要查询的充电桩id")
    @ApiImplicitParam(name = "getOneStationParam",value = "要查询的充电桩id",required = true,dataType = "GetOneStationParam")
    public ResultEntity getOneStation(@RequestBody GetOneStationParam getOneStationParam){
        Station station = new Station();
        BeanUtils.copyProperties(getOneStationParam,station);
        System.out.println("Station"+":"+station);
        Station oneStation = stationService.getOneStation(station);
        return ResultEntity.buildSuccessEntity(Station.class).setMessage("查询成功").setCode(ConstCode.ACCESS_SUCCESS).setData(oneStation);
    }

    /*
     * @Author HuangZhengXing
     * @Description TODO 根据电站id查询所有该电站电桩信息
     * @Date  2021/4/12
     * @Param [listStationByPowerplantParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity<java.util.List<com.woniu.car.station.model.entity.Station>>
     **/
    @PostMapping("/list_station")
    @ApiOperation(value = "根据电站id查询所有该电站电桩信息",notes = "不需要传入参数")
    public ResultEntity<List<Station>> listStation(@RequestBody ListStationByPowerplantParam listStationByPowerplantParam){
        Station station = new Station();
        BeanUtils.copyProperties(listStationByPowerplantParam,station);
        System.out.println("Station"+":"+station);
        List<Station> stations = stationService.listStationAll(station);
        return ResultEntity.buildListSuccessEntity(Station.class).setMessage("查询成功").setCode(ConstCode.ACCESS_SUCCESS).setData(stations);
    }
    /**
     * @Author HuangZhengXing
     * @Description TODO 根据充电桩id修改充电桩基本信息
     * @Date  2021/4/12
     * @Param [updateStationInfoParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PutMapping("/api/update_station_info")
    @ApiOperation(value = "根据充电桩id修改充电桩基本信息",notes = "传入要修改的充电桩id和要修改的基本信息")
    @ApiImplicitParam(name = "updateStationInfoParam",value = "要修改的充电桩id和要修改的基本信息",required = true,dataType = "UpdateStationInfoParam")
    public ResultEntity updateStationInfo(UpdateStationInfoParam updateStationInfoParam){
        UpdateStationDto updateStationDto = new UpdateStationDto();
        BeanUtils.copyProperties(updateStationInfoParam,updateStationDto);
        boolean b = stationService.updateBasicStationInfo(updateStationDto);
        if (b) return ResultEntity.buildSuccessEntity().setMessage("修改充电桩基本信息成功").setCode(ConstCode.ACCESS_SUCCESS);
        return ResultEntity.buildFailEntity().setMessage("修改充电桩基本信息失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
    }
    /**
     * @Author HuangZhengXing
     * @Description TODO 根据充电桩id修改充电桩充电状态
     * @Date  2021/4/12
     * @Param [updateStationStatusParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PutMapping("/update_station_status")
    @ApiOperation(value = "根据充电桩id修改充电桩充电状态",notes = "传入要修改充电桩id和充电桩充电状态")
    @ApiImplicitParam(name = "updateStationStatusParam",value = "要修改充电桩id和充电桩充电状态",required = true,dataType = "UpdateStationStatusParam")
    public ResultEntity updateStationStatus(@RequestBody UpdateStationStatusParam updateStationStatusParam){
        Station station = new Station();
        BeanUtils.copyProperties(updateStationStatusParam,station);
        System.out.println("Station"+":"+station);
        boolean b = stationService.updataStationStatus(station);
        if (b) return ResultEntity.buildSuccessEntity().setMessage("充电状态修改成功").setCode(ConstCode.ACCESS_SUCCESS);
        return ResultEntity.buildFailEntity().setMessage("充电状态修改失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
    }
    /**
     * @Author HuangZhengXing
     * @Description TODO 根据充电桩id修改充电桩充电类型
     * @Date  2021/4/12
     * @Param [updateStationTypeParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PutMapping("/update_station_type")
    @ApiOperation(value = "根据充电桩id修改充电桩充电类型",notes = "传入要修改充电桩id和充电桩充电类型")
    @ApiImplicitParam(name = "updateStationTypeParam",value = "要修改充电桩id和充电桩充电类型",required = true,dataType = "UpdateStationTypeParam")
    public ResultEntity updateStationType(@RequestBody UpdateStationTypeParam updateStationTypeParam){
        Station station = new Station();
        BeanUtils.copyProperties(updateStationTypeParam,station);
        System.out.println("Station"+":"+station);
        boolean b = stationService.updataStationType(station);
        if (b) return ResultEntity.buildSuccessEntity().setMessage("修改充电类型成功").setCode(ConstCode.ACCESS_SUCCESS);
        return ResultEntity.buildFailEntity().setMessage("修改充电类型失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
    }
    
    /**
     * @Author HuangZhengXing
     * @Description TODO 根据充电桩id删除充电桩
     * @Date  2021/4/12
     * @Param [deleteStationParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @DeleteMapping("/api/delete_station")
    @ApiOperation(value = "根据充电桩id删除充电桩",notes = "传入要删除的充电桩id")
    @ApiImplicitParam(name = "deleteStationParam",value = "要删除的充电桩id",required = true,dataType = "DeleteStationParam")
    public ResultEntity deleteStation(@RequestBody DeleteStationParam deleteStationParam){
        Station station = new Station();
        BeanUtils.copyProperties(deleteStationParam,station);
        System.out.println("Station"+":"+station);
        boolean b = stationService.deleteStation(station);
        if (b) return ResultEntity.buildSuccessEntity().setMessage("删除成功").setCode(ConstCode.ACCESS_SUCCESS);
        return ResultEntity.buildFailEntity().setMessage("删除失败").setCode(ConstCode.LAST_STAGE).setFlag(false);
    }
}

