package com.woniu.car.station.web.controller;


import com.woniu.car.commons.core.code.ConstCode;
import com.woniu.car.commons.core.dto.ResultEntity;
import com.woniu.car.station.model.dto.PowerplantDto;
import com.woniu.car.station.model.entity.Powerplant;
import com.woniu.car.station.model.param.DeletePowerplantParam;
import com.woniu.car.station.model.param.GetPowerplantParam;
import com.woniu.car.station.model.param.UpdatePowerplantParam;
import com.woniu.car.station.web.service.PowerplantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ObjectUtils;
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
@RequestMapping("/powerplant")
@Api(tags = "充电服务电站接口信息")//用于描述接口类的相关信息,作用于类上
public class PowerplantController {
    @Resource
    private PowerplantService powerplantService;


    /**
     * @Author HuangZhengXing
     * @Description TODO 根据电站id查询对应电站信息
     * @Date  2021/4/9
     * @Param [getPowerplantParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PostMapping("/get_one_powerplant")
    @ApiOperation(value = "根据电站id查询电站信息",notes = "传入要查询的电站id")
    @ApiImplicitParam(name = "getPowerplantParam",value = "要查询的电站id",required = true,dataType = "GetPowerplantParam")
    public ResultEntity getOnePowerplant(@RequestBody GetPowerplantParam getPowerplantParam){
        if (ObjectUtils.isEmpty(getPowerplantParam.getPowerplantId())){
            return ResultEntity.buildEntity(Powerplant.class).setMessage("输入为空，或输入错误").setFlag(false).setData(null).setCode(ConstCode.LAST_STAGE);
        }
        //复制param参数到实体类
        Powerplant powerplant = new Powerplant();
        BeanUtils.copyProperties(getPowerplantParam,powerplant);
        System.out.println(powerplant);
        PowerplantDto onePowerplant = powerplantService.getOnePowerplant(powerplant);
        if (ObjectUtils.isEmpty(onePowerplant)){
            return ResultEntity.buildSuccessEntity(PowerplantDto.class).setMessage("查询电站信息成功,结果为空或者电站不存在").setCode(ConstCode.ACCESS_SUCCESS).setData(onePowerplant);
        }
        return ResultEntity.buildSuccessEntity(PowerplantDto.class).setMessage("查询电站信息成功").setCode(ConstCode.ACCESS_SUCCESS).setData(onePowerplant);
    }
    /**
     * @Author HuangZhengXing
     * @Description 查询所有电站信息
     * @Date  2021/4/9
     * @Param []
     * @return com.woniu.car.commons.core.dto.ResultEntity<java.util.List<com.woniu.car.station.model.entity.Powerplant>>
     **/
    @GetMapping("/api/list_powerplant")
    @ApiOperation(value = "查询所有电站信息",notes = "不需要传入参数")
    public ResultEntity<List<PowerplantDto>> listPowerplant(){
        //调用查询所有电站信息的方法
        List<PowerplantDto> powerplantList = powerplantService.listPowerplantAll();
        if (ObjectUtils.isEmpty(powerplantList))return ResultEntity.buildListSuccessEntity(PowerplantDto.class).setMessage("查询所有电站信息成功,结果为空").setCode(ConstCode.ACCESS_SUCCESS).setData(powerplantList);
        return ResultEntity.buildListSuccessEntity(PowerplantDto.class).setMessage("查询所有电站信息成功").setCode(ConstCode.ACCESS_SUCCESS).setData(powerplantList);
    }
    /**
     * @Author HuangZhengXing
     * @Description TODO 根据电站id修改电站信息
     * @Date  2021/4/9
     * @Param [updatePowerplantParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @PutMapping("/api/update_powerplant")
    @ApiOperation(value = "根据电站id修改电站信息",notes = "传入要修改的电站id")
    @ApiImplicitParam(name = "updatePowerplantParam",value = "要修改的电站信息",required = true,dataType = "UpdatePowerplantParam")
    public ResultEntity updatePowerplant(@RequestBody UpdatePowerplantParam updatePowerplantParam){
        if (ObjectUtils.isEmpty(updatePowerplantParam)) return ResultEntity.buildEntity().setMessage("输入为空，或输入错误").setFlag(false).setCode(ConstCode.LAST_STAGE);
        Powerplant powerplant = new Powerplant();
        BeanUtils.copyProperties(updatePowerplantParam,powerplant);
        System.out.println(powerplant);
        boolean b = powerplantService.updatePowerplantById(powerplant);
        if (b) return ResultEntity.buildSuccessEntity().setMessage("电站信息修改成功").setCode(ConstCode.ACCESS_SUCCESS);
        return ResultEntity.buildFailEntity().setMessage("电站信息修改失败").setCode(ConstCode.LAST_STAGE);
    }
    /**
     * @Author HuangZhengXing
     * @Description TODO 根据电站id删除电站信息
     * @Date  2021/4/12
     * @Param [deletePowerplantParam]
     * @return com.woniu.car.commons.core.dto.ResultEntity
     **/
    @DeleteMapping("/api/delete_powerplant")
    @ApiOperation(value = "根据电站id删除电站信息",notes = "传入要删除的电站id")
    @ApiImplicitParam(name = "deletePowerplantParam",value = "要删除的电站id",required = true,dataType = "DeletePowerplantParam")
    public ResultEntity deletePowerplant(@RequestBody DeletePowerplantParam deletePowerplantParam){
        if (ObjectUtils.isEmpty(deletePowerplantParam.getPowerplantId())) return ResultEntity.buildEntity().setMessage("输入为空，或输入错误").setFlag(false).setCode(ConstCode.LAST_STAGE);
        Powerplant powerplant = new Powerplant();
        BeanUtils.copyProperties(deletePowerplantParam,powerplant);
        System.out.println("复制之后的实体类"+":"+powerplant);
        boolean b = powerplantService.deletePowerplantById(powerplant);
        if (b) return ResultEntity.buildSuccessEntity().setMessage("删除电站成功").setCode(ConstCode.ACCESS_SUCCESS);
        return ResultEntity.buildFailEntity().setMessage("删除电站失败").setCode(ConstCode.LAST_STAGE);
    }

}

